<%--
  Created by IntelliJ IDEA.
  User: eightant
  Date: 2016/12/16
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*" %>
<%@ page import="com.eight.search.Docs" %>
<%
    String query = (String) request.getAttribute("query");
    List<Docs> list = (List<Docs>) request.getAttribute("docList");
    int totalDoc = (int) request.getAttribute("totalDocs");
    double time = Double.parseDouble(request.getAttribute("time").toString());
    int pageNow = (int) request.getAttribute("pageNow");
    int pageCount = (int) request.getAttribute("pageCount");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <title>Search Result</title>
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById("query").value ="<%=query%>";
        }

    </script>
</head>
<body>
    <div class="nav">
        <div class="nav_left">
            <a href="index.jsp"><img alt="logo" src="images/LOGO.png"></a>
        </div>
        <div class="nav_right">
            <div class="nav_form">
                <form action="search" method="get">
                    <input id="query" type="text" name="query" value="<%=query%>">
                    <input type="submit" value=" 搜  索 "><br />
                </form>
            </div>
        </div>
    </div>

    <div class="docsMain">
        <h4>
            共搜到<span class="docsNum"><%=totalDoc%></span>个结果|用时<span
                class="newsnum"><%=time%></span>秒
        </h4>
        <%
            if (list.size() > 0) {
                Iterator<Docs> iter = list.iterator();
                Docs docs;
                while (iter.hasNext()) {
                    docs = iter.next();
        %>

        <div class="item">
            <h4>
                <a href="<%=docs.getDocURL()%> " target="_blank"><%=docs.getTitle()%></a>
            </h4>
            <p>
                <%=docs.getContent().length() > 300 ? docs.getContent().substring(0, 300) : docs.getContent()%>
            </p>
                <p><a href="<%=docs.getDocURL()%>" target="_blank"><%=docs.getDocURL()%></a></p>
                <p>----------------------------------------------------------</p>
        </div>
        <%
                }
            }
        %>
    </div>

    <div class="paging">
        <ul>
            <li><a href="search?query=<%=query%>&&pageNow=1">首页</a></li>

            <%
                if (pageNow !=1){
            %>
            <li><a href="search?query=<%=query%>&&pageNow=<%=pageNow - 1%>">上一页</a></li>
            <%
                }
            %>

            <%
                for (int i = 1; i <= pageCount; i++) {
            %>
            <li><a href="search?query=<%=query%>&&pageNow=<%=i%>"><%=i%></a></li>
            <%
                }
            %>
            <%
                if (pageNow != pageCount){
            %>
            <li><a href="search?query=<%=query%>&&pageNow=<%=pageNow+1%>">下一页</a></li>
            <li><a href="search?query=<%=query%>&&pageNow=<%=pageCount%>">末页</a></li>
            <%
                }
            %>
        </ul>
        <hr>
    </div>

    <div class="footerinfo">
        <p>     </p>
    </div>

</body>
</html>
