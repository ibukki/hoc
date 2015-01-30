<!doctype html>
<%@ page pageEncoding="UTF-8"%>
<html ng-app="hocAdmin">
<head>
<meta charset="UTF-8" />
<link rel="shortcut icon" href="ui/common/images/favicon.ico" />
<title>Prometheus - Login</title>
<jsp:include page="../../common/pages/agular_common_include.html" />
<script src="ui/common/js/agularAppInit.js"></script>
<link href="ui/user/css/login.css" rel="stylesheet" />
<script>var g_contextpath = '<%=request.getContextPath()%>';</script>
<script src="ui/user/js/controllers/loginController.js"></script>
</head>
<body ng-controller="loginController">
	<div id="wrap-all">
		<jsp:include page="header_navi.jsp" />
		<div class="single-panel">
			<div class="single-panel-header">
				<span ng-class="{'single_tab':true,'tab_sel':loginType == 'I'}" ng-click="switchLoginType('I')">
					<h3>Individual User Login</h3>
				</span>
				<span ng-class="{'single_tab':true,'tab_sel':loginType == 'E'}" ng-click="switchLoginType('E')">
					<h3>Enterprise User Login</h3>
				</span>
			</div>
			<div class="single-panel-body">
				<div class="single-panel-section">
					<form name="login_form" class="form" novalidate>
						<div class="form-group" ng-show="loginType == 'E'">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="icon icon-company"></i></span> <input class="form-control"
									type="text" placeholder="company id" name="login_company_id"
									ng-model="login_user.companyid" required="" />
							</div>
						</div>

						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="icon icon-user"></i></span>
								<input class="form-control" type="text" placeholder="user name"
									name="userId" ng-model="login_user.userid" required="" />
							</div>
						</div>

						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="icon icon-lock"></i></span>
								<input class="form-control" type="password"
									placeholder="password" name="login_password"
									ng-model="login_user.password" required="" />
							</div>
						</div>

						<div class="form-group">
							<button class="btn btn-success btn-lg btn-block" type="button"
								data-loading-text="login..." ng-click="login()">Login</button>
						</div>
					</form>
				</div>

			</div>
			<div class="single-panel-footer">
				<div class="pull-right">
					<a href="/forgot">Forgot your password?</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>