<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link type="text/css" 
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/myResources/css/style.css" 
	/>
	<link type="text/css" 
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/myResources/css/add-customer-style.css" 
	/>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h3>CRM - Customer Relationship Manager</h3>
		</div>
	</div>

	<div id="container">
		<h3>Save Customer</h3>
		
		<form:form action="saveCustomer" method="post" modelAttribute="customer">
			
			<!-- Need to associate this data with customer id -->
			<!-- Without this line we will actually lose the context or the id 
			of the original customer. This side effect will be seen while 
			updating the customer without this below line.  -->
			<form:hidden path="id" />
			
			<table>
				<tr>
					<td><label>First Name</label></td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td><label>Last Name</label></td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td><label>Email</label></td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td>
						<input type="submit" value="Save" class="save"/>
					</td>
				</tr>
			</table>
		</form:form>
		
		<div style="clear; both;"></div>
		<p>
			<a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
		</p>
	</div>
	
</body>
</html>