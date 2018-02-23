package com.eight.search;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by eightant on 2016/12/22.
 */
public class Utils {
    public static ArrayList<Docs> getDocs(String dataPath) {
        ArrayList<Docs> list = new ArrayList<Docs>();
        File file = new File(dataPath);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            NodeList docList = doc.getElementsByTagName("item");
            Element categoryElement = (Element) doc.getElementsByTagName("category").item(0);
            Random r = new Random(10);
            for (int i = 0; i < docList.getLength(); i++) {
                Node docNode = docList.item(i);
                if (docNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) docNode;
                    Docs docs = new Docs();
                    Element titleElement = (Element) itemElement.getElementsByTagName("title").item(0);
                    docs.setTitle(titleElement.getTextContent());
                    //System.out.println("title="+titleElement.getTextContent());
                    Element linkElement = (Element) itemElement.getElementsByTagName("link").item(0);
                    docs.setDocURL(linkElement.getTextContent());
                    //设置作者
                    Element authorElement = (Element) itemElement.getElementsByTagName("author").item(0);
                    docs.setAuthor(authorElement.getTextContent());
                    //图片链接
                    Element imageElement = (Element) itemElement.getElementsByTagName("image").item(0);
                    docs.setImage(imageElement.getTextContent());
                    //System.out.println("link"+linkElement.getTextContent());
                    Element descriptionElement = (Element) itemElement.getElementsByTagName("description").item(0);
                    if (descriptionElement != null && descriptionElement.getTextContent() != null) {
                        docs.setContent(descriptionElement.getTextContent());
                        
                        //随机赋值给该doc一个热度
                        docs.setHotScore(r.nextInt(10000));
                        //System.out.println("hotScore----"+docs.getHotScore());
                        
                        //设置时间，data用于显示，data_sort用于排序
                        Element dataElement = (Element) itemElement.getElementsByTagName("pubDate").item(0);
                        //爬虫爬的日期格式有误，全部设为2000-01-01
                        int temp_data_sort = 20000101;
                        docs.setData("2000-01-01");
                        if(dataElement.getTextContent().length()==10) {
	                        docs.setData(dataElement.getTextContent());
	                        String[] temp_data = dataElement.getTextContent().split("-");
	                        if(temp_data.length==3) {
	                        		temp_data_sort = Integer.parseInt(temp_data[0])*10000+Integer.parseInt(temp_data[1])*100+Integer.parseInt(temp_data[2]);   
	                        }
	                    }
                        //System.out.println(dataElement.getTextContent()+"-------"+temp_data_sort);
                        docs.setData_sort(temp_data_sort);
                       
                        	docs.setCategory(categoryElement.getTextContent());
             
                        //System.out.println("content"+descriptionElement.getTextContent());
                        list.add(docs);
                        //System.out.println("list"+list);
                    }
       
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getFileNameUtil(String dataPath) {
        ArrayList<String> fileName = new ArrayList<>();
        File file = new File(dataPath);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    getFileNameUtil(file1.getPath());
                } else {
                    fileName.add(file1.getName());
                }
            }
        }
        return fileName;
    }
    
    
}
