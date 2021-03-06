<!doctype html>
<%@ page pageEncoding="UTF-8"%>
<html ng-app="hocAdmin">
<head>
<meta charset="UTF-8" />
<link rel="shortcut icon" href="ui/common/images/favicon.ico" />
<title>Prometheus - Register</title>
<jsp:include page="../../common/pages/agular_common_include.html" />
<script src="ui/common/js/agularAppInit.js"></script>
<link href="ui/user/css/register.css" rel="stylesheet" />
<script>var g_contextpath = '<%=request.getContextPath()%>';
</script>
<script src="ui/user/js/controllers/registerController.js"></script>
</head>
<body ng-controller="registerController">
	<jsp:include page="header_navi.jsp" />
	<section class="reg-container">
		<div class="reg-content">
			<header>Sign Up</header>
			<div class="info"><label>or you can <a href="applyCompany.htm">apply for a company account</a></label></div>
			<form name="registerForm">
				<div class="basic-info">
					<header>Basic Info</header>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="text" placeholder="user name"
								name="username" ng-model="register.username"
								required="" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="email" placeholder="email"
								name="email" ng-model="register.email"
								required="" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="password" placeholder="password"
								name="password" ng-model="register.password"
								required="" />
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="password" placeholder="repeat password"
								name="repeatPassword" ng-model="register.repeatPassword"
								required="" />
						</div>
					</div>
				</div>
				<div class="register-info">
					
				</div>
				<div class="addtional-info">
					<header>Additional Info</header>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="text" placeholder="first name"
								name="firstName" ng-model="register.firstName"
								/>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="text" placeholder="last name"
								name="lastName" ng-model="register.lastName"
								/>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="text" placeholder="industry"
								name="industry" ng-model="register.industry"
								/>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="text" placeholder="company name"
								name="company" ng-model="register.company"
								/>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="text" placeholder="title"
								name="jobTitle" ng-model="register.jobTitle"
								/>
						</div>
					</div>
				</div>
				<div class="checkbox">
						<label><input class="checkbox" type="checkbox" name="agreement" ng-model="register.agreement">
							I agree with the <a href="#">terms and conditions</a></label>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" ng-click="registerForm.$valid ? registerNewUser() : ''">Register</button>
				</div>
			</form>
		</div>
	</section>
	<footer>
		
	</footer>
</body>
</html>