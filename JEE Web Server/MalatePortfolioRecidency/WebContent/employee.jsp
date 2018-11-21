<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="action.implementation.authentication.FaceScanHandler"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="parts/meta.jsp" %>
<title>Malate Portfolio - Manage Employees</title>
<%@ include file="parts/header.jsp" %>  
</head>
<body>
<%@ include file="parts/nav.jsp" %>  
<form method="post">
<input type="text" placeholder="id" name="<%=FaceScanHandler.ID_PARAMETER %>">
<button name="ACTION" value="<%=FaceScanHandler.SCAN_ACTION%>"></button>
</form>
<%@ include file="parts/footer.jsp" %>  
</body>
</html>