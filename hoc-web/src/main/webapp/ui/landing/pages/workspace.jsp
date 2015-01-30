<!doctype html>
<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en" id="ng-app" ng-app="hocAdmin">
<link rel="shortcut icon" href="ui/common/images/favicon.ico" />
<head>
	<meta charset="UTF-8" />
	<title>Prometheus</title>
	<jsp:include page="../../common/pages/agular_common_include.html" />
	<script src="ui/common/js/agularAppInit.js"></script>
	<script src="ui/common/js/controllers/mainNaviTreeController.js"></script>
	<script src="ui/common/js/controllers/mainPageController.js"></script>
	<c:forEach var="pageUrl" items="${pageUrlList }">
		<jsp:include page="${pageUrl.path}" />
    </c:forEach>
	<link href="ui/common/css/mainLayout.css" rel="stylesheet"/>
	<link href="ui/landing/css/workspace.css" rel="stylesheet"/>
	<script>
		var g_contextpath = '<%=request.getContextPath()%>';
		var g_company_id = '${companyBean.companyId}';
	</script>
	
	
</head>
<body ng-controller="mainPageController">
	 <div class='wrap' >
	 	<header class='pageHeader'>
	 		<ng-include src="'ui/common/pages/layout_header_navi.html'">
	 		</ng-include>
	 	</header>
	 	<article class='page_content'>
	 		<div class='side_nav' ng-controller="mainNaviTreeController">
	 			<ng-include src="'ui/common/pages/layout_left_navi.html'" ng-show="mainNaviExpanded">
	 			</ng-include>
	 			<ng-include src="'ui/common/pages/layout_left_navi_sm.html'" ng-show="!mainNaviExpanded">
	 			</ng-include>
	 		</div>
	 		<div class='main_content' id="main_content" ng-style="pageContentSizeStyle">
	 			<div class="content_header">
	 				
	 			</div>
	 			<span ng-show="isViewLoading"> loading the view... </span>
	 			<div ng-show="!isViewLoading" ng-view></div>
	 		</div>
	 	</article>
	 	<footer></footer>	
	 </div>
</body>
</html>