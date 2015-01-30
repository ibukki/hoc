<!doctype html>
<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<link rel="shortcut icon" href="ui/common/images/favicon.ico" />
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>Prometheus</title>
	<jsp:include page="../../common/pages/agular_common_include.html" />
	<link href="ui/landing/css/landing_index.css" rel="stylesheet"/>
</head>
<body>
	<header class="doc-header">
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">
		      	<img alt="Brand" src="ui/common/images/favicon.ico">Prometheus
		      </a>
		    </div>
		    <ul class="nav navbar-nav navbar-right">
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Login <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="login.htm">Individual User Login</a></li>
		            <li><a href="login.htm">Enterprise User Login</a></li>
		            <li class="divider"></li>
		          </ul>
		        </li>
		        <li><a href="register.htm">Register</a></li>
		      </ul>
		  </div><!-- /.navbar-collapse -->
		</nav>
	</header>
	<section class="doc-container">
		<div class="bg-img"></div>
		<div class="container-fluid">
		  <div class="row">
		    <div class="col-md-8"></div>
  			<div class="col-md-4"></div>
		  </div>
		</div>
	</section>
	<footer class="doc-footer">
		@Copyright by bubuwork.inc @2015
	</footer>
</body>
	
</html>