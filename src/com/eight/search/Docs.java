package com.eight.search;

/**
 * Created by eightant on 2016/12/15.
 */
public class Docs {
    private String title;
    private String content;
    private String htitle;	//关键字标红的title
    private String hcontent;	//关键字标红的content
    private String docURL;
    private String category;
    private int hotScore;	//热度评分
    private String hotScoreStr;		//热度评分,字符串
    private String data;		//时间，用于显示
    private int data_sort;	//时间，用于排序
    private String author;	//作者
    private String image;		//图片链接
    private int totalDocs;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public int getData_sort() {
        return data_sort;
    }

    public void setData_sort(int data_sort) {
        this.data_sort = data_sort;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getHTitle() {
        return htitle;
    }

    public void setHTitle(String htitle) {
        this.htitle = htitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getHContent() {
        return hcontent;
    }

    public void setHContent(String hcontent) {
        this.hcontent = hcontent;
    }

    public int getHotScore() {
        return hotScore;
    }

    public void setHotScore(int hotScore) {
        this.hotScore = hotScore;
    }
    
    public String getHotScoreStr() {
        return hotScoreStr;
    }

    public void setHotScoreStr(String hotScoreStr) {
        this.hotScoreStr = hotScoreStr;
    }
    
    public String getDocURL() {
        return docURL;
    }

    public void setDocURL(String dosURL) {
        this.docURL = dosURL;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Docs() {
        super();
    }

    public Docs(String title,String htitle,String docURL, String content, String hcontent ,String category,String hotScoreStr, String data, String author,String image, int totalDocs){
        super();
        this.title = title;
        this.content = content;
        this.htitle = htitle;
        this.hcontent = hcontent;
        this.docURL = docURL;
        this.category = category;
        this.hotScoreStr = hotScoreStr;
        this.data = data;
        this.author = author;
        this.image =image;
        this.totalDocs = totalDocs;
    }
    
    public Docs(String title, String content,String hotScoreStr, String docURL, int totalDocs){
        super();
        this.title = title;
        this.content = content;
        this.hotScoreStr = hotScoreStr;
        this.docURL = docURL;
        this.totalDocs = totalDocs;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getTotalDocs() {
        return totalDocs;
    }

    public void setTotalDocs(int totalDocs) {
        this.totalDocs = totalDocs;
    }
}
