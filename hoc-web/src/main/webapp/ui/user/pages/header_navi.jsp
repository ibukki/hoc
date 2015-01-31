<%@ page pageEncoding="UTF-8"%>
<style>
	.navbar-brand img{
		width:20px;
		height:20px;
	}
	
	.navbar-default{
		margin-bottom:0px;
	}
</style>
<header>
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
	          </ul>
	        </li>
	        <li><a href="register.htm">Register</a></li>
	      </ul>
	  </div><!-- /.navbar-collapse -->
	</nav>
</header>