package com.eight.search;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String indexPathStr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //创建索引
        indexPathStr = config.getServletContext().getRealPath("/index");
        System.out.println("indexPathStr=" + indexPathStr);
        String dataPath = config.getServletContext().getRealPath("/data");
        System.out.println("dataPath=" + dataPath);
        Index.index(dataPath, indexPathStr);	 //每次部署服务，每次都生成一次新的索引
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 ArrayList<Docs> docList1 = getSearch(indexPathStr, "网易");
		 ArrayList<Docs> docList2 = getSearch(indexPathStr, "搜狐");
		 ArrayList<Docs> docList3 = getSearch(indexPathStr, "新浪");
		 ArrayList<Docs> docList4 = getSearch(indexPathStr, "腾讯");
		 ArrayList<Docs> docList5 = getSearch(indexPathStr, "凤凰");
		 
         if(docList1==null && docList2==null && docList3==null && docList4==null && docList5==null) {
         	request.getRequestDispatcher("error.jsp").forward(request, response);
         }
         
        request.setAttribute("docList1", docList1);
        request.setAttribute("docList2", docList2);
        request.setAttribute("docList3", docList3);
        request.setAttribute("docList4", docList4);
        request.setAttribute("docList5", docList5);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    private ArrayList<Docs> getSearch(String indexPathStr,  String category)  {
    		ArrayList<Docs> docList = new ArrayList<Docs>();
        DirectoryReader directoryReader = null;
        Analyzer analyzer = new SmartChineseAnalyzer();
        TopDocs topDocs = null;
        try {
	        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(indexPathStr));
	        directoryReader = DirectoryReader.open(directory);
	        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
	    		QueryParser queryParser = new QueryParser("category", analyzer);
	    		Query query = queryParser.parse(category);
	    		Sort sort = new Sort(new SortField("hotScore", SortField.Type.INT, true));
	    		topDocs = indexSearcher.search(query, 20, sort);
	    		

	        int totalDocs = topDocs.totalHits;
	        System.out.println("查找到的文档共有：" + totalDocs);
	        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

	        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
                //7.根据searcher和ScoreDoc对象获取具体的Document对象
                Document document = indexSearcher.doc(scoreDocs[i].doc);
                //8.根据Document对象获取需要的值
                
                System.out.println("[" + i + "]" + document.get("title"));
                System.out.println("content----"+document.get("content"));
                System.out.println(document.get("docURL"));
                System.out.println("score:" + scoreDocs[i].score);
                System.out.println("hotScore----"+document.get("hotScore"));
                System.out.println("category----"+document.get("category"));
                System.out.println("data----"+document.get("data"));
                System.out.println("image----"+document.get("image"));
                
              
                Docs docs = new Docs( document.get("title"), document.get("content"), document.get("hotScore"),document.get("docURL"), totalDocs);
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

}
