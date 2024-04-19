<%@include file="header.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

	<title>Registration</title>
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet" type="text/css">
	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
</head>
<body>
	<c:if test="${registrationError != null}">
		<input type="hidden" id="notification" value="${registrationError}">
	</c:if>
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
							<spring:bind path="userName">
								<form:input path="userName" id="floatingInputUsername" placeholder="username"
									class="form-control ${status.error ? 'is-invalid' : '' }"/>
								<label for="floatingInputUsername">Username</label>
								<div class="invalid-feedback">
									<form:errors path="userName"/>
								</div>
							</spring:bind>
						</div>
						<div class="form-floating mb-3">
							<spring:bind path="email">
								<form:input path="email" id="floatingInputEmail" placeholder="email"
									class="form-control ${status.error ? 'is-invalid' : '' }"/>
								<label for="floatingInputEmail">Email address</label>
							</spring:bind>
							<div class="invalid-feedback">
								<form:errors path="email"/>
							</div>
						</div>
						<div class="form-floating mb-3">
							<spring:bind path="password">
								<form:password path="password" id="floatingInputPassword" placeholder="password"
									class="form-control ${status.error ? 'is-invalid' : '' }"/>
								<label for="floatingInputPassword">Password</label>
							</spring:bind>
							<div class="invalid-feedback">
								<form:errors path="password"/>
							</div>
						</div>
						<div class="form-floating mb-3">
							<spring:bind path="matchingPassword">
								<form:password path="matchingPassword" id="floatingInputMatchingPass" placeholder="MatchingPass"
									class="form-control ${status.error ? 'is-invalid' : '' }"/>
								<label for="floatingInputMatchingPass">Re-enter Password</label>
							</spring:bind>
							<div class="invalid-feedback">
								<form:errors path="matchingPassword"/>
							</div>
						</div>

						<div class="d-flex justify-content-center">
							<button type="submit" class="btn btn-primary btn-lg mt-4">Register</button>
						</div>
					</form:form>
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