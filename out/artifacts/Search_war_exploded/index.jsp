<%--
  Created by IntelliJ IDEA.
  User: eightant
  Date: 2016/12/16
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <title>Search</title>
</head>
<body>
<div class="main">
    <div class="logo">
        <img alt="logo" src="images/LOGO.png">
    </div>
    <div class="searchbox">
        <div class="searchform">
            <form action="search?p=1" method="get">
                <input type="text" name="query" id="query" value="">
                <input type="submit" value=" 搜  索 ">
            </form>
        </div>
    </div>
</div>

</body>
</html>
