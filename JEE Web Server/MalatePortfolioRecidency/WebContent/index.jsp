<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="action.implementation.authentication.EmployeeLoginHandler"%>
<%@page import="action.implementation.authentication.EmployeeLogoutHandler"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="parts/meta.jsp" %>
<title>Malate Portfolio - Recidency System</title>
<%@ include file="parts/header.jsp" %>  
</head>
<body>
<%@ include file="parts/nav.jsp" %>  
<div class="jumbotron d-flex justify-content-center">
<form  method="post">
  <div class="form-group">
    <h1>Scan Here</h1>
  </div>
  <div class="form-group">
  <div class="fakeimg row">Fake Image Capture Area</div>
  </div>
  <button type="submit" class="btn btn-success" name="ACTION" value="<%=EmployeeLoginHandler.SCAN_LOGIN_ACTION%>">Time-In</button>
  <button type="submit" class="btn btn-primary" name="ACTION" value="<%=EmployeeLogoutHandler.SCAN_LOGOUT_ACTION%>">Time-Out</button>
</form>
</div>
<%@ include file="parts/footer.jsp" %>  
</body>
</html>