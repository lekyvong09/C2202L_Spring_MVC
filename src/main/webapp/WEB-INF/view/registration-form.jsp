<%@include file="header.jsp" %>
	<title>Registration</title>
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet" type="text/css">
	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3>Registration Form</h3>
				</div>
				
				<div class="card-body">
					<form:form method="post" 
							action="${pageContext.request.contextPath}/register/processRegistrationForm"
							modelAttribute="registeredUser"
							style="width:350px;">
							
						<div class="form-floating mb-3">
							<form:input path="userName" class="form-control" id="floatingInputUsername" placeholder="username"/>
							<label for="floatingInputUsername">Username</label>
						</div>
						<div class="form-floating mb-3">
							<form:input path="email" type="email" class="form-control" id="floatingInputEmail" placeholder="email"/>
							<label for="floatingInputEmail">Email address</label>
						</div>
						<div class="form-floating mb-3">
							<form:password path="password" class="form-control" id="floatingInputPassword" placeholder="password"/>
							<label for="floatingInputPassword">Password</label>
						</div>
						<div class="form-floating mb-3">
							<form:password path="matchingPassword" class="form-control" id="floatingInputMatchingPass" placeholder="MatchingPass"/>
							<label for="floatingInputMatchingPass">Re-enter Password</label>
						</div>

						<div class="d-flex justify-content-center">
							<button type="submit" class="btn btn-primary btn-lg mt-4">Register</button>
						</div>
					</form:form>
				</div>
				
				
			</div>
		</div>
	</div>
	
	
</body>
</html>