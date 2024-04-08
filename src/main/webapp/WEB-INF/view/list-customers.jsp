<%@ page import="com.ray.crm.constant.SortCustomerColumn" %>

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
		
		<!-- TODO: delete customer, then CUstomer sorting -->
		
		<hr class="mx-auto" style="width:50%;"/>
		
		<div class="table-responsive py-3">
			<table class="table table-striped">
				<thead>
					<c:url var="sortLinkFirstName" value="/customer/list">
						<c:param name="sort" value="<%=String.valueOf(SortCustomerColumn.FIRST_NAME) %>" />
					</c:url>
					<c:url var="sortLinkLastName" value="/customer/list">
						<c:param name="sort" value="<%=String.valueOf(SortCustomerColumn.LAST_NAME) %>" />
					</c:url>
					<c:url var="sortLinkEmail" value="/customer/list">
						<c:param name="sort" value="<%=String.valueOf(SortCustomerColumn.EMAIL) %>" />
					</c:url>
				
					<tr>
						<th><a href="${sortLinkFirstName}">First Name</a></th>
						<th><a href="${sortLinkLastName}">Last Name</a></th>
						<th><a href="${sortLinkEmail}">Email</a></th>
						<th>Action</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${customers}" var="customer">
						
						<c:url value="/customer/load" var="updateLink">
							<c:param name="customerId" value="${customer.id}"/>
						</c:url>
						<c:url value="/customer/delete" var="deleteLink">
							<c:param name="customerId" value="${customer.id}"/>
						</c:url>
						
						<tr>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
							<td>${customer.email}</td>
							<td>
								<a href="${updateLink}">Update</a>
								 | 
							 	<a href="${deleteLink}"
							 		onclick="if (!confirm('Are you sure you want to delete?')) return false;"
						 		>Delete</a>
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