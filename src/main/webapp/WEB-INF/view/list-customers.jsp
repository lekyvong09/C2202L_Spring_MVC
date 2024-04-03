<%@include file="header.jsp" %>

	<title>List customer</title>
</head>
<body>
	<div class="container py-3">
		<h1 class="text-center mb-4">Customer Relationship Manager</h1>
		
		<div class="d-flex justify-content-between align-items-center py-5">
			<input class="btn btn-primary" type="button" value="Add customer" style="width:30%;"
				onclick="window.location.href='new'; return false;">
		
			<form action="search" method="get">
				<div class="form-group row">
					<div class="col-6">
						<input name="theSearchName" class="form-control mr-2" id="search" type="search" placeholder="Search"/>
					</div>
					<div class="col-6">
						<button type="submit" class="btn btn-success">Search</button>
					</div>
				</div>
			</form>
		</div>
		
		
		
		<hr class="mx-auto" style="width:50%;"/>
		
		<div class="table-responsive py-3">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${customers}" var="customer">
						
						<c:url value="/customer/load" var="updateLink">
							<c:param name="customerId" value="${customer.id}"/>
						</c:url>
						
						<tr>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
							<td>${customer.email}</td>
							<td>
								<a href="${updateLink}">Update</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			
			</table>
		</div>
	</div>
	
	<script src="<c:url value="/resources/js/bootstrap.bundle.min.js" />"></script>
</body>
</html>