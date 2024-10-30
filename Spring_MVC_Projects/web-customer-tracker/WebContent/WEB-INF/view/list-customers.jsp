<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link type="text/css" 
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/myResources/css/style.css" 
	/>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h3>CRM - Customer Relationship Manager</h3>
		</div>
	</div>

	<div id="container">
		<div id="content">
		
			<!-- put new button: Add Customer -->
			<button class="add-button" onclick="window.location.href='showFormForAdd'; return false;">Add Customer</button>
			<!-- add our html table -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">
				
					<!-- construct an "update" link with customer id  -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
					
					<!-- construct an "delete" link with customer id  -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
					
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}" onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>