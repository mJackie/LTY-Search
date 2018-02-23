<%--
  Date: 2018/1/5
  Author: liu13
  Link: jackieliu.win
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.eight.search.Docs" %>
<%
	ArrayList<Docs> list1 = (ArrayList<Docs>) request.getAttribute("docList1");
	ArrayList<Docs> list2 = (ArrayList<Docs>) request.getAttribute("docList2");
	ArrayList<Docs> list3 = (ArrayList<Docs>) request.getAttribute("docList3");
	ArrayList<Docs> list4 = (ArrayList<Docs>) request.getAttribute("docList4");
	ArrayList<Docs> list5 = (ArrayList<Docs>) request.getAttribute("docList5");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>主页-LTY新闻搜索</title>
    <!-- load stylesheets -->
    <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="font-awesome-4.5.0/css/font-awesome.min.css">                
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/bootstrap.min.css">      

    <!-- Bootstrap style -->
    <link rel="stylesheet" href="css/hero-slider-style.css">                              
    <!-- Hero slider style (https://codyhouse.co/gem/hero-slider/) -->
    <link rel="stylesheet" href="css/magnific-popup.css">                                 
    <!-- Magnific popup style (http://dimsemenov.com/plugins/magnific-popup/) -->
    <link rel="stylesheet" href="css/templatemo-style.css">                                   
    <!-- Templatemo style -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
          <![endif]-->
    
</head>

    <body>

        <!-- Content -->
        <div class="cd-hero">

            <!-- Navigation -->     
            <div class="cd-slider-nav">
                <nav class="navbar">
                    <div class="tm-navbar-bg">
                        
                        
                        <a class="navbar-brand text-uppercase" style="color: #337ab7; font-weight: bold; font-size: 25px; font-style: italic;" href="#">LTY <span style="color: black">新闻搜索</span></a>

                        <button class="navbar-toggler hidden-lg-up" type="button" data-toggle="collapse" data-target="#tmNavbar">
                            &#9776;
                        </button>
                        <div class="collapse navbar-toggleable-md text-xs-center text-uppercase tm-navbar" id="tmNavbar">
                            <ul class="nav navbar-nav">
                                <li class="nav-item active selected">
                                    <a class="nav-link" href="index.jsp" data-no="1">搜索<span class="sr-only">(current)</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#0" data-no="2">网易</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#0" data-no="3">搜狐</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#0" data-no="4">新浪</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#0" data-no="5">腾讯</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#0" data-no="6">凤凰</a>
                                </li>
                            </ul>
                        </div>                        
                    </div>

                </nav>
            </div>
                
            

            <ul class="cd-hero-slider">  <!-- autoplay -->
                <li class="selected">
                    <div class="cd-full-width">
                        <div class="container-fluid  tm-contact-page tm-page-1" data-page-no="1">

                            <div class="cd-bg-video-wrapper" data-video="video/moving-cloud">
                                <!-- video element will be loaded using jQuery -->
                            </div> <!-- .cd-bg-video-wrapper -->
                            
                            <div class="row">
                            
                                <div class="col-xs-12">
                                     <div class="tm-flex tm-contact-container">
                                
                                            <div class="tm-bg-white-translucent text-xs-left tm-textbox tm-2-col-textbox-2 tm-textbox-padding tm-textbox-padding-contact">                 
                                             <p class="tm-text">LTY（老天爷）搜索是一款多功能新闻搜索引擎，支持关键词检索及通配符检索，具备分类检索，关键词高亮，热度排序等功能。该名字源自三位项目开发人员的首字母的简称。</p>                              
                                                
                                                <!-- contact form -->
                                                <form action="search" method="get" class="tm-contact-form">
                                                                                                            
                                                    <div class="form-group">
                                                        <input type="text" id="contact_subject query"  type="text" name="query"  class="form-control" placeholder="请输入关键字"  required/>
                                                    </div>  
                                                    
                                                    

                                                    <button type="submit" class="pull-xs-right tm-submit-btn">搜索</button> 
                                                
                                                </form>  
                                            </div>

                                           

                                        </div>

                                </div>

                            </div>

                        </div>
                    </div> <!-- .cd-full-width -->
                </li>

                <li>
                    <div class="cd-full-width">

                        <div class="container-fluid js-tm-page-content" data-page-no="2">

                            <div class="cd-bg-video-wrapper" data-video="video/red-flower">
                                <!-- video element will be loaded using jQuery -->
                            </div> <!-- .cd-bg-video-wrapper -->
                            
                            <div class="tm-img-gallery-container">

                                <div class="tm-img-gallery gallery-one">
                                <!-- Gallery One pop up connected with JS code below -->
                                    
                                    <div class="tm-img-gallery-info-container">
                                    
                                        <h2 class="tm-text-title tm-gallery-title">网易热门新闻</h2>
                                        <p class="tm-text">
                                        </p>                                    
                                    
                                    </div>
                                    
									<%
							            if (list1.size() > 0) {
							                Iterator<Docs> iter = list1.iterator();
							                Docs docs;
							                while (iter.hasNext()) {
							                    docs = iter.next();
							        %>
                                    <div class="grid-item" style="width: 320px;height: 300px;border: 10px solid #ffffff;padding:20px;background: white;color: black;margin:10px;">
                                                                                      
                                            <h3 class="tm-text-title tm-gallery-title"><span class="tm-black"><%=docs.getTitle()%></span></h3>
                                            <a href="<%=docs.getDocURL()%>"><%=docs.getContent().length() > 100 ? docs.getContent().substring(0, 100) : docs.getContent()%></a>
                                    </div>
                                    <%
							                }
							            }
							        %>
                                                             
                                </div>
                                 
                            </div> <!-- .tm-img-gallery-container -->



                    </div> <!-- .cd-full-width -->

                </li>

                <!-- Page 3 -->
                <li>
                    
                    <div class="cd-full-width">

                        <div class="container-fluid js-tm-page-content" data-page-no="3">
                            
                            <div class="cd-bg-video-wrapper" data-video="video/sunset-cloud">
                                <!-- video element will be loaded using jQuery -->
                            </div> <!-- .cd-bg-video-wrapper -->
                            
                            <div class="tm-img-gallery-container">

                                <div class="tm-img-gallery gallery-one">
                                <!-- Gallery One pop up connected with JS code below -->
                                    
                                    <div class="tm-img-gallery-info-container">
                                    
                                        <h2 class="tm-text-title tm-gallery-title">搜狐热门新闻</h2>
                                        <p class="tm-text">
                                        </p>                                    
                                    
                                    </div>

                                    <%
							            if (list2.size() > 0) {
							                Iterator<Docs> iter = list2.iterator();
							                Docs docs;
							                while (iter.hasNext()) {
							                    docs = iter.next();
							        %>
                                    <div class="grid-item" style="width: 320px;height: 300px;border: 10px solid #ffffff;padding:20px;background: white;color: black;margin:10px;">
                                                                                      
                                            <h3 class="tm-text-title tm-gallery-title"><span class="tm-black"><%=docs.getTitle()%></span></h3>
                                            <a href="<%=docs.getDocURL()%>"><%=docs.getContent().length() > 100 ? docs.getContent().substring(0, 100) : docs.getContent()%></a>
                                    </div>
                                    <%
							                }
							            }
							        %>
                                                             
                                </div>
                                 
                            </div> <!-- .tm-img-gallery-container -->

                        </div> <!-- .container-fluid -->
                                                    
                    </div> <!-- .cd-full-width -->
                    
                </li>

                <!-- Page 4 -->
                <li>
                    
                    <div class="cd-full-width">

                        <div class="container-fluid js-tm-page-content" data-page-no="4">

                            <div class="cd-bg-video-wrapper" data-video="video/night-light-blur">
                                <!-- video element will be loaded using jQuery -->
                            </div> <!-- .cd-bg-video-wrapper -->
                            
                            <div class="tm-img-gallery-container">

                                <div class="tm-img-gallery gallery-one">
                                <!-- Gallery One pop up connected with JS code below -->
                                    
                                    <div class="tm-img-gallery-info-container">
                                    
                                        <h2 class="tm-text-title tm-gallery-title">新浪热门新闻</h2>
                                        <p class="tm-text">
                                        </p>                                    
                                    
                                    </div>

                                    <%
							            if (list3.size() > 0) {
							                Iterator<Docs> iter = list3.iterator();
							                Docs docs;
							                while (iter.hasNext()) {
							                    docs = iter.next();
							        %>
                                    <div class="grid-item" style="width: 320px;height: 300px;border: 10px solid #ffffff;padding:20px;background: white;color: black;margin:10px;">
                                                                                      
                                            <h3 class="tm-text-title tm-gallery-title"><span class="tm-black"><%=docs.getTitle()%></span></h3>
                                            <a href="<%=docs.getDocURL()%>"><%=docs.getContent().length() > 100 ? docs.getContent().substring(0, 100) : docs.getContent()%></a>
                                    </div>
                                    <%
							                }
							            }
							        %>
                                                             
                                </div>
                                 
                            </div> <!-- .tm-img-gallery-container -->
                                 
                           

                        </div> <!-- .container-fluid -->
                                                    
                    </div> <!-- .cd-full-width -->
                    
                </li>

                <li>

                    <div class="cd-full-width">
                        <div class="container-fluid js-tm-page-content" data-page-no="5">
                        
                            <div class="cd-bg-video-wrapper" data-video="video/padaut-bee">
                                <!-- video element will be loaded using jQuery -->
                            </div> <!-- .cd-bg-video-wrapper -->

                            
                            <div class="tm-img-gallery-container">

                                <div class="tm-img-gallery gallery-one">
                                <!-- Gallery One pop up connected with JS code below -->
                                    
                                    <div class="tm-img-gallery-info-container">
                                    
                                        <h2 class="tm-text-title tm-gallery-title">腾讯热门新闻</h2>
                                        <p class="tm-text">
                                        </p>                                    
                                    
                                    </div>

                                    <%
							            if (list4.size() > 0) {
							                Iterator<Docs> iter = list4.iterator();
							                Docs docs;
							                while (iter.hasNext()) {
							                    docs = iter.next();
							        %>
                                    <div class="grid-item" style="width: 320px;height: 300px;border: 10px solid #ffffff;padding:20px;background: white;color: black;margin:10px;">
                                                                                      
                                            <h3 class="tm-text-title tm-gallery-title"><span class="tm-black"><%=docs.getTitle()%></span></h3>
                                            <a href="<%=docs.getDocURL()%>"><%=docs.getContent().length() > 100 ? docs.getContent().substring(0, 100) : docs.getContent()%></a>
                                    </div>
                                    <%
							                }
							            }
							        %>
                                                             
                                </div>
                                 
                            </div> <!-- .tm-img-gallery-container -->


                            

                        </div>         
                    </div>                                       

                </li>

                <li>
                    <div class="cd-full-width">

                        <div class="container-fluid js-tm-page-content" data-page-no="6">

                        <div class="cd-bg-video-wrapper" data-video="video/sunset-loop">
                            <!-- video element will be loaded using jQuery -->
                        </div> <!-- .cd-bg-video-wrapper -->
                            
                                
                                
                            <div class="tm-img-gallery-container">

                                <div class="tm-img-gallery gallery-one">
                                <!-- Gallery One pop up connected with JS code below -->
                                    
                                    <div class="tm-img-gallery-info-container">
                                    
                                        <h2 class="tm-text-title tm-gallery-title">凤凰热门新闻</h2>
                                        <p class="tm-text">
                                        </p>                                    
                                    
                                    </div>

                                    <%
							            if (list5.size() > 0) {
							                Iterator<Docs> iter = list5.iterator();
							                Docs docs;
							                while (iter.hasNext()) {
							                    docs = iter.next();
							        %>
                                    <div class="grid-item" style="width: 320px;height: 300px;border: 10px solid #ffffff;padding:20px;background: white;color: black;margin:10px;">
                                                                                      
                                            <h3 class="tm-text-title tm-gallery-title"><span class="tm-black"><%=docs.getTitle()%></span></h3>
                                            <a href="<%=docs.getDocURL()%>"><%=docs.getContent().length() > 100 ? docs.getContent().substring(0, 100) : docs.getContent()%></a>
                                    </div>
                                    <%
							                }
							            }
							        %>
                                                             
                                </div>
                                 
                            </div> <!-- .tm-img-gallery-container -->



                              

                        </div>
                        
                    </div> <!-- .cd-full-width -->
                </li>
            </ul> <!-- .cd-hero-slider -->
            
            <footer class="tm-footer">
            
                <div class="tm-social-icons-container text-xs-center">
                    
                </div>
                
                <p class="tm-copyright-text" style="float: right;">
                    
                      <i>Design & Power by</i> 
                      <a href="http://jackieliu.win" style="font-weight: bold;color: white;">刘嘉琦</a> 田双坤 姚永珍 &copy; 2018 
                    
                
                </p>

            </footer>
                    
        </div> <!-- .cd-hero -->
        

        <!-- Preloader, https://ihatetomatoes.net/create-custom-preloading-screen/ -->
        <div id="loader-wrapper">
            
            <div id="loader"></div>
            <div class="loader-section section-left"></div>
            <div class="loader-section section-right"></div>

        </div>
        
        <!-- load JS files -->
         <script src="style/jquery.min.js"></script>        <!-- jQuery (https://jquery.com/download/) -->
        <script src="js/tether.min.js"></script> <!-- Tether for Bootstrap (http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h) --> 
        <script src="js/bootstrap.min.js"></script>             <!-- Bootstrap js (v4-alpha.getbootstrap.com/) -->
        <script src="js/hero-slider-main.js"></script>          
        <script src="js/jquery.magnific-popup.min.js"></script> <!-- Magnific popup (http://dimsemenov.com/plugins/magnific-popup/) -->
        
        <script>

            function adjustHeightOfPage(pageNo) {

                var offset = 80;
                var pageContentHeight = $(".cd-hero-slider li:nth-of-type(" + pageNo + ") .js-tm-page-content").height();

                if($(window).width() >= 992) { offset = 120; }
                else if($(window).width() < 480) { offset = 40; }
               
                // Get the page height
                var totalPageHeight = 15 + $('.cd-slider-nav').height()
                                        + pageContentHeight + offset
                                        + $('.tm-footer').height();

                // Adjust layout based on page height and window height
                if(totalPageHeight > $(window).height()) 
                {
                    $('.cd-hero-slider').addClass('small-screen');
                    $('.cd-hero-slider li:nth-of-type(' + pageNo + ')').css("min-height", totalPageHeight + "px");
                }
                else 
                {
                    $('.cd-hero-slider').removeClass('small-screen');
                    $('.cd-hero-slider li:nth-of-type(' + pageNo + ')').css("min-height", "100%");
                }
            }

            /*
                Everything is loaded including images.
            */
            $(window).load(function(){

                adjustHeightOfPage(1); // Adjust page height

                

                /* Collapse menu after click 
                -----------------------------------------*/
                $('#tmNavbar a').click(function(){
                    $('#tmNavbar').collapse('hide');

                    adjustHeightOfPage($(this).data("no")); // Adjust page height       
                });

                /* Browser resized 
                -----------------------------------------*/
                $( window ).resize(function() {
                    var currentPageNo = $(".cd-hero-slider li.selected .js-tm-page-content").data("page-no");
                    
                    // wait 3 seconds
                    setTimeout(function() {
                        adjustHeightOfPage( currentPageNo );
                    }, 1000);
                    
                });
        
                // Remove preloader (https://ihatetomatoes.net/create-custom-preloading-screen/)
                $('body').addClass('loaded');
                           
            });

           
        
          

        </script>            

</body>
</html>