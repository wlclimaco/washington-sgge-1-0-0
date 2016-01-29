<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	response.setContentType("text/html; charset=utf-8");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Visão Acessoria Contábil e Empresarial</title>

    <!-- Bootstrap Core CSS -->
    <link href="startbootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="startbootstrap/css/heroic-features.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Visão Acessoria Contábil e Empresarial</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">Empresa</a>
                    </li>
                    <li>
                        <a href="#">Serviços</a>
                    </li>
					<li>
                        <a href="#">Quadro Funcionarios</a>
                    </li>
                    <li>
                        <a href="#">Contato</a>
                    </li>
					<li class="login style={margin-left: 20%}">
                        <a href="login.jsp">Login</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <!-- Jumbotron Header -->
        <header class="jumbotron hero-spacer">
            <h1>A Warm Welcome!</h1>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsa, ipsam, eligendi, in quo sunt possimus non incidunt odit vero aliquid similique quaerat nam nobis illo aspernatur vitae fugiat numquam repellat.</p>
            <p><a class="btn btn-primary btn-large">Call to action!</a>
            </p>
        </header>

        <hr>

        <!-- Title -->
        <div class="row">
            <div class="col-lg-12">
                <h3>Latest Features</h3>
            </div>
        </div>
        <!-- /.row -->

        <!-- Page Features -->
        <div class="row text-center">

            <div class="col-md-3 col-sm-6 hero-feature">
                <div class="thumbnail">
                    <img src="http://placehold.it/800x500" alt="">
                    <div class="caption">
                        <h3>Feature Label</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <p>
                            <a href="#" class="btn btn-primary">Buy Now!</a> <a href="#" class="btn btn-default">More Info</a>
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-6 hero-feature">
                <div class="thumbnail">
                    <img src="http://placehold.it/800x500" alt="">
                    <div class="caption">
                        <h3>Feature Label</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <p>
                            <a href="#" class="btn btn-primary">Buy Now!</a> <a href="#" class="btn btn-default">More Info</a>
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-6 hero-feature">
                <div class="thumbnail">
                    <img src="http://placehold.it/800x500" alt="">
                    <div class="caption">
                        <h3>Feature Label</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <p>
                            <a href="#" class="btn btn-primary">Buy Now!</a> <a href="#" class="btn btn-default">More Info</a>
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-6 hero-feature">
                <div class="thumbnail">
                    <img src="http://placehold.it/800x500" alt="">
                    <div class="caption">
                        <h3>Feature Label</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <p>
                            <a href="#" class="btn btn-primary">Buy Now!</a> <a href="#" class="btn btn-default">More Info</a>
                        </p>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
	<script src="thirdparty/jquery/jquery-1.10.2.min.js"></script>
	<script src="thirdparty/jquery/jquery.json-2.4.min.js"></script>
     <script src="thirdparty/jquery/jquery-1.10.2.min.js"></script>
		   <script src="thirdparty/jquery/jquery.address-1.6.min.js"></script>
		   <script src="thirdparty/jquery/jquery-ui-1.10.3.custom.min.js"></script>
		   <script src="thirdparty/jquery/jquery.layout-latest.js"></script>
		   <script src="thirdparty/jquery/jquery-ui-layout-resize-accordions.js"></script>
		   <script src="thirdparty/jquery/jquery.ui.autocomplete.js"></script>
		   <script src="thirdparty/jquery/custom/jquery.i18n.properties.js"></script>
		   <script src="thirdparty/jquery/custom/jquery.format-1.2.js"></script>
		   <script src="thirdparty/jquery/jquery.cookie.js"></script>
		   <script src="thirdparty/jquery/jquery.dynatree.min.js"></script>
		   <script src="thirdparty/jquery/jquery.equalheights.js"></script>
		   <script src="thirdparty/jquery/jquery.jnotify.js"></script>
		   <script src="thirdparty/jquery/jquery.blockUI.js"></script>
		   <script src="thirdparty/jquery/jquery.validate.min.js"></script>
		   <script src="thirdparty/jquery/jquery.idle-timer.js"></script>
		   <script src="thirdparty/jquery/jquery.idletimeout.js"></script>
		   <script src="thirdparty/jquery/jquery.json-2.4.min.js"></script>
		   <script src="thirdparty/jquery/jquery.event.drag-2.2.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="startbootstrap/js/bootstrap.min.js"></script>
    <script src="startbootstrap/js/init.js"></script>
     <script src="knockout/knockout.js"></script>

</body>

</html>
