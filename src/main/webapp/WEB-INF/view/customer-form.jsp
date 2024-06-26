<%@include file="header.jsp" %>

	<title>Customer form</title>
</head>
<body>
	<div class="container py-3">
		<h1 class="text-center mb-4">Customer Form</h1>
		
		<div class="d-flex flex-column align-items-center py-5">
			<form:form action="saveCustomer?${_csrf.parameterName}=${_csrf.token}" modelAttribute="customer" 
					method="post" style="width:350px" enctype="multipart/form-data">
				<form:hidden path="id"/>

				<div class="form-floating mb-3">
					<form:input path="firstName" class="form-control" id="firstName" placeholder="firstName"/>
					<label for="firstName">First Name</label>
				</div>
				<div class="form-floating mb-3">
					<form:input path="lastName" class="form-control" id="lastName" placeholder="lastName"/>
					<label for="lastName">Last Name</label>
				</div>
				<div class="form-floating mb-3">
					<form:input path="email" class="form-control" id="email" placeholder="email"/>
					<label for="email">Email</label>
				</div>
				
				<div class="d-flex justify-content-center">
					<input name="file" onchange="loadFile(event)" class="form-control" type="file" accept="image/*">
				</div>
				
				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-primary btn-lg mt-4 me-4" style="width:30%;">Save</button>
				</div>
			</form:form>
			
			<img id="preview-image-before-upload" style="width:440px;object-fit:cover;" class="mt-4"/>
			
		</div>
		
		<p><a href="<c:url value="/customer/list" />">Back to List</a></p>
	</div>
	
	<script src="<c:url value="/resources/js/bootstrap.bundle.min.js" />"></script>
	<script type="text/javascript">
		function loadFile(event) {
			if (event.target.files.length > 0) {
				var previewImageElement = document.getElementById('preview-image-before-upload');
				previewImageElement.src = URL.createObjectURL(event.target.files[0]);
			}
		}
	</script>
	
</body>
</html>