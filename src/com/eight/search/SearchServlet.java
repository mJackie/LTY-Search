package com.eight.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by eightant on 2016/12/19.
 */
@WebServlet(name = "com.eight.search.SearchServlet")
public class SearchServlet extends HttpServlet {
    private static int totalDocs = 0;
    private static final int PAGE_SIZE = 10;// 指定每一页显示10条记录
    private int pageCount = 1;// 总共的页数
    private int rowCount = 1;// 总共有多少条记录
    private int pageNow = 1;// 当前要显示的页数，默认为1
    private int rank_style = 1;	//1默认相关度排序；2按热度排序；3按时间倒序
    private int search_field = 0;	//检索方式：0全文标题检索，1内容检索，2标题检索
    private String category = "all";	//表示搜索范围 0表示全局搜索，不同分类
    private String indexPathStr;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        indexPathStr = config.getServletContext().getRealPath("/index");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        System.out.println("查询： " + query);

        // 计算查询时间
        long starTime = System.currentTimeMillis();// start time



        if (!"".equals(query) && query != null) {
            //排序方式
            String temp_rank_style = request.getParameter("rank");
            if (temp_rank_style != null) {
            		rank_style = Integer.parseInt(temp_rank_style);
            }
            
            //搜索范围
            String temp_category = request.getParameter("category");
            if (temp_category != null) {
            		category = temp_category;
            }
            
            //检索方式
            String temp_search_field = request.getParameter("field");
            if (temp_search_field != null) {
            		search_field = Integer.parseInt(temp_search_field);
            }
            
            ArrayList<Docs> docList = getSearch(query, indexPathStr, rank_style, category, search_field);
            if(docList == null) {
            		//getSearch 参数错误
                request.setAttribute("query", query);
                request.setAttribute("category", category);
                request.setAttribute("search_field", search_field);
                request.setAttribute("rank", rank_style);
            		request.getRequestDispatcher("error.jsp").forward(request, response);
            }

            //分页
            String temp_pageNow = request.getParameter("pageNow");
            if (temp_pageNow != null) {
                pageNow = Integer.parseInt(temp_pageNow);
            }
            rowCount = totalDocs;//条数
            pageCount = (rowCount - 1) / PAGE_SIZE + 1;//页数

            List<Docs> pagelist = docList.subList(PAGE_SIZE * (pageNow - 1),
                    PAGE_SIZE * pageNow < rowCount ? PAGE_SIZE * pageNow : rowCount);

            if (docList.size() != 0) {
                //System.out.println("文档长度servlet docList length:" + docList.size());
                request.setAttribute("query", query);
                request.setAttribute("docList", pagelist);
                request.setAttribute("totalDocs", totalDocs);
                long endTime = System.currentTimeMillis();// end time
                long Time = endTime - starTime;
                request.setAttribute("time", (double) Time / 1000);

                request.setAttribute("rank", rank_style);
                request.setAttribute("category", category);
                request.setAttribute("search_field", search_field);
                request.setAttribute("pageNow", pageNow);
                request.setAttribute("pageCount", pageCount);

                request.getRequestDispatcher("list.jsp").forward(request, response);
            }else {
                request.setAttribute("query", query);
                request.setAttribute("category", category);
                request.setAttribute("search_field", search_field);
                request.setAttribute("rank", rank_style);
            		request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private ArrayList<Docs> getSearch(String keyword, String indexPathStr, int rank, String category, int search_field)  {
    		//keyword 关键字
    		//indexPathStr 索引路径
    		//rank 表示排序方式 1默认相关度排序；2按热度排序；3按时间倒序
    		//category 表示搜索范围 0表示全局搜索，不同分类
    		//search_field 检索方式：0全文标题检索，1全文检索，2标题检索
    	
        ArrayList<Docs> docList = new ArrayList<Docs>();
        DirectoryReader directoryReader = null;
        try {
            //1.创建Directory
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(indexPathStr));
            //2.创建IndexReader
            directoryReader = DirectoryReader.open(directory);
            //3.创建IndexSearch
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
            
            Analyzer analyzer = new SmartChineseAnalyzer();
            TopDocs topDocs = null;	
            QueryScorer scorer = null;  //用于高亮显示
    			if(category.equals("all")){
    				
    				if(search_field==0) {
	    				//title 与 content 同时匹配
	    				QueryParser queryParser1 = new QueryParser("title", analyzer);
	        			Query query1 = queryParser1.parse(keyword);
	        			QueryParser queryParser2 = new QueryParser("content", analyzer);
	        			Query query2 = queryParser2.parse(keyword);
		    			BooleanClause bc1 = new BooleanClause(query1, Occur.SHOULD);
			        BooleanClause bc2 = new BooleanClause(query2, Occur.SHOULD);
			        BooleanQuery boolQuery = new BooleanQuery.Builder().add(bc1).add(bc2).build();

			        //开始判断排序方式
					if(rank ==1) {
	            			topDocs = indexSearcher.search(boolQuery, 500);
	            			scorer=new QueryScorer(boolQuery);
	    	            }else if(rank==2) {
	    	            		//按hotScore热度降序排列
	    	                Sort sort = new Sort(new SortField("hotScore", SortField.Type.INT, true));
	    	                topDocs = indexSearcher.search(boolQuery, 500, sort);
	    	                scorer=new QueryScorer(boolQuery);
	    	            }else if(rank==3) {
	    	            		//按时间倒序排列
	    	            		Sort sort = new Sort(new SortField("data_sort", SortField.Type.INT, true));
	    	                topDocs = indexSearcher.search(boolQuery, 500, sort);
	    	                scorer=new QueryScorer(boolQuery);
	    	            }else {
	    	            		return null;
	    	            }
    				}else if(search_field==1||search_field==2) {
    					//全文检索或标题检索
    					QueryParser queryParser = null;
    					if(search_field==1) {//全文检索
    						queryParser = new QueryParser("content", analyzer);
    					}else if(search_field==2){//标题检索
    						queryParser = new QueryParser("title", analyzer);
    					}
    	                Query query = queryParser.parse(keyword);
    					//开始判断排序方式
    					if(rank ==1) {
    	            			topDocs = indexSearcher.search(query, 500);
    	            			scorer=new QueryScorer(query);
	    	            }else if(rank==2) {
	    	            		//按hotScore热度降序排列,true代表降序
	    	                Sort sort = new Sort(new SortField("hotScore", SortField.Type.INT, true));
	    	                topDocs = indexSearcher.search(query, 500, sort);
	    	                scorer=new QueryScorer(query);
	    	            }else if(rank==3) {
	    	            		//按时间倒序排列
	    	            		Sort sort = new Sort(new SortField("data_sort", SortField.Type.INT, true));
	    	                topDocs = indexSearcher.search(query, 500, sort);
	    	                scorer=new QueryScorer(query);
	    	            }else {
	    	            		return null;
	    	            }
    				}else {
    					return null;
    				}
    			}else {
    				//子栏目搜索，垂直搜索。search_field 参数失效
            		QueryParser queryParser1 = new QueryParser("category", analyzer);
	        		Query query1 = queryParser1.parse(category);
	        		QueryParser queryParser2 = new QueryParser("content", analyzer);
	        		Query query2 = queryParser2.parse(keyword);
	            System.out.println("zilanmu");
	            BooleanClause bc1 = new BooleanClause(query1, Occur.MUST);
	            BooleanClause bc2 = new BooleanClause(query2, Occur.MUST);
	            BooleanQuery boolQuery = new BooleanQuery.Builder().add(bc1).add(bc2).build();
	            
	            if(rank ==1) {
	            		topDocs = indexSearcher.search(boolQuery, 500);
	            		scorer=new QueryScorer(boolQuery);
	            }else if(rank==2) {
	            		//按hotScore热度降序排列
	                Sort sort = new Sort(new SortField("hotScore", SortField.Type.INT, true));
	                topDocs = indexSearcher.search(boolQuery, 500, sort);
	                scorer=new QueryScorer(boolQuery);
	            }else if(rank==3) {
	            		//按时间倒序排列
	            		Sort sort = new Sort(new SortField("data_sort", SortField.Type.INT, true));
	                topDocs = indexSearcher.search(boolQuery, 500, sort);
	                scorer=new QueryScorer(boolQuery);
	            }else {
	            		return null;
	            }
    			}
	        

            totalDocs = topDocs.totalHits;
            System.out.println("查找到的文档共有：" + totalDocs);
            //6.根据TopDocs获取ScoreDoc对象
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            
            //高亮显示
            SimpleHTMLFormatter fors=new SimpleHTMLFormatter("<span style=\"color:red;\">", "</span>");
            Highlighter highlighter=new Highlighter(fors, scorer);
            
            for (int i = 0; i < topDocs.scoreDocs.length; i++) {
                //7.根据searcher和ScoreDoc对象获取具体的Document对象
                Document document = indexSearcher.doc(scoreDocs[i].doc);
                //8.根据Document对象获取需要的值
                
                /*System.out.println("[" + i + "]" + document.get("title"));
                System.out.println(document.get("docURL"));
                System.out.println("score:" + scoreDocs[i].score);
                System.out.println("hotScore----"+document.get("hotScore"));
                System.out.println("category----"+document.get("category"));
                System.out.println("data----"+document.get("data"));
                System.out.println("image----"+document.get("image"));
                */
                
                
                TokenStream tokenStream=TokenSources.getAnyTokenStream(indexSearcher.getIndexReader(), topDocs.scoreDocs[i].doc, "title", analyzer);
                Fragmenter fragment=new SimpleSpanFragmenter(scorer);
                highlighter.setTextFragmenter(fragment);
                String highlight_title = highlighter.getBestFragment(tokenStream, document.get("title"));
                
                tokenStream=TokenSources.getAnyTokenStream(indexSearcher.getIndexReader(), topDocs.scoreDocs[i].doc, "content", analyzer);
                String highlight_content = highlighter.getBestFragment(tokenStream, document.get("content"));
                
                //System.out.println(highlight_title+"-----"+highlight_content);
                
                if(highlight_title==null) {
                		highlight_title = document.get("title");
                }
                if(highlight_content==null) {
                		highlight_content = document.get("content");
                }
                Docs docs = new Docs( document.get("title"), highlight_title, document.get("docURL"),document.get("content"), highlight_content,document.get("category"), document.get("hotScore"), document.get("data"), document.get("author"),document.get("image"), totalDocs);
                docList.add(docs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (directoryReader != null) {
                try {
                    directoryReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println("文档docList" + docList);
        return docList;
    }

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}