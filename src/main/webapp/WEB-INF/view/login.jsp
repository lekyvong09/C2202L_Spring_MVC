<%@include file="header.jsp" %>
	<title>Login</title>
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet" type="text/css">
	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
</head>
<body>
	<c:if test="${param.error != null}">
		<input type="hidden" id="notification" value="Invalid username or password.">
	</c:if>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3>Log in</h3>
				</div>
				
				<div class="card-body">
					<form method="post" action="${pageContext.request.contextPath}/login">
						<div class="input-group form-group">
							<span class="input-group-text d-flex"><i class="fas fa-user mx-auto"></i></span>
							<input name="username" type="text" class="form-control" placeholder="username" />
						</div>
						<div class="input-group form-group">
							<span class="input-group-text d-flex"><i class="fas fa-key mx-auto"></i></span>
							<input name="password" type="password" class="form-control" placeholder="password" />
						</div>
						<div class="form-group">
							<button type="submit" class="btn login_btn float-end">Login</button>
						</div>
						
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</div>
				
				<div class="card-footer">
					<div class="d-flex justify-content-center links">
						Don't have an account?<a href="#">Sign up</a>
					</div>
					<div class="d-flex justify-content-center">
						<a href="#">Forgot your password</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
	
	<script type="text/javascript">
		var notification = document.getElementById("notification");
		// console.log(notification.value);
		if (notification != null && notification.value.length > 0) {
			alertify.error(notification.value);
		}
		
	</script>
</body>
</html>