<%-- 
    Document   : HomePage
    Created on : 13-Nov-2020, 09:38:45
    Author     : Eoghan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to the Application</h1>
    </body>
    
   
    <form action="GeneralUserServlet" method="GET">
         <input type="hidden" name="action" value="RequestAllGeneralUser">
         <input type="submit" value="General Users" >
         <br>
         
    </form>
    
    <c:out value="${message}" />
</html>
