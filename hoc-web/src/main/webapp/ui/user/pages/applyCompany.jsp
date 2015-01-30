<!doctype html>
<%@ page pageEncoding="UTF-8"%>
<html ng-app="hocAdmin">
<head>
<meta charset="UTF-8" />
<link rel="shortcut icon" href="ui/common/images/favicon.ico" />
<title>Prometheus - Apply a company account</title>
<jsp:include page="../../common/pages/agular_common_include.html" />
<script src="ui/common/js/agularAppInit.js"></script>
<link href="ui/user/css/company_apply.css" rel="stylesheet" />
<script>var g_contextpath = '<%=request.getContextPath()%>';</script>
<script src="ui/user/js/controllers/applyCompanyController.js"></script>
</head>
<body ng-controller="applyCompanyController">
	<jsp:include page="header_navi.jsp" />
	
	<div class="ac-container">
		<div class="ac-content">
			<header>Apply for a new company account</header>
			<form name="acForm">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="icon"></i></span>
						<input class="form-control" type="text" placeholder="company id"
							name="companyId" ng-pattern="/[a-zA-Z]+/" ng-model="applyData.companyId"
							required="" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="icon"></i></span>
						<input class="form-control" type="text" placeholder="company name"
							name="companyName" ng-model="applyData.companyName"
							required="" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="icon"></i></span>
						<input class="form-control" type="number" placeholder="seat"
							name="seat" ng-model="applyData.seat"
							required="" />
						<span class="input-group-addon" data-placement="right" data-type="info" data-container="body" data-trigger="focus" bs-tooltip="seatTooltip" type="text"><i class="infoIcon glyphicon glyphicon-info-sign" ></i></span>
					</div>
				</div>
				<div class="superuser-info">
					<header>Create Super User Account</header>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="text" placeholder="super user name"
								name="superUser" ng-model="applyData.superUser"
								required="" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="password" placeholder="super user password"
								name="superPass" ng-model="applyData.superPass"
								required="" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="icon"></i></span>
							<input class="form-control" type="password" placeholder="repeat super user password"
								name="superPassRepeat" ng-model="applyData.superPassRepeat"
								required="" />
						</div>
					</div>
					<div class="checkbox">
							<label><input class="checkbox" type="checkbox" name="agreement" ng-model="applyData.agreement">
								I agree with the <a href="#">terms and conditions</a></label>
					</div>
					<div class="form-group">
						<button class="btn btn-primary" ng-click="acForm.$valid ? applyCompany() : ''">Apply</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>