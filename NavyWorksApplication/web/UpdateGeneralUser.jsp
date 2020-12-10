<%-- 
    Document   : UpdateGeneralUser
    Created on : 19-Nov-2020, 12:01:54
    Author     : Eoghan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update GeneralUser</h1>
        <form action="GeneralUserServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="saveGeneralUser">
            
            <input type="hidden" name="generalUserID" value=<c:out value='${GeneralUser.generalUserID}' />>
            
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value=<c:out value='${GeneralUser.username}' />><br>
            
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="<c:out value='${GeneralUser.firstName}' />"><br>

            <label for="surName">Surname:</label>
            <input type="text" id="surName" name="surName" value=<c:out value='${GeneralUser.surName}' />><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<c:out value='${GeneralUser.email}' />"><br>
            
            <label for="password">Password:</label>
            <input type="text" id="password" name="password" value="<c:out value='${GeneralUser.password}' />"><br>

            <input type="submit" value="Submit">

        </form>
    </body>
</html>
