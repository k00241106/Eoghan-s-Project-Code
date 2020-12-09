<%-- 
    Document   : DisplayAllGeneralUsers
    Created on : 15-Nov-2020, 18:42:25
    Author     : Eoghan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
  function del_confirm(msg,url)
        {
            if(confirm(msg))
            {
                window.location.href=url;
            }
            else
            {
                false;
            }

        }  
    
</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 5px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <h1>Display All General Users</h1>
        <c:out value="${message}" />
        <a href="/NavyWorksApplication/GeneralUserServlet?action=home">Home Page</a>
         <form action="GeneralUserServlet" method="GET">
         <input type="hidden" name="action" value="RequestAddGeneralUser">
         <input type="submit" value="Add General User" >
         <br>
         
         </form>
        <table>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Surname</th>
                <th>Email</th>
                
            </tr>
            <tr>
                <c:forEach var="g" items="${AllGeneralUsers}">
                <tr>
                    <td><c:out value="${g.username}" /></td>
                    <td><c:out value="${g.firstName}" /></td>
                    <td><c:out value="${g.surName}" /></td>
                    <td><c:out value="${g.email}" /></td>
                   <td>
                        <a href="/NavyWorksApplication/GeneralUserServlet?action=EditGeneralUser&generalUserID=<c:out value='${g.generalUserID}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a onclick="del_confirm('Are you Sure want to delete the User, Username <c:out value="${g.username}" />?','/NavyWorksApplication/GeneralUserServlet?action=deleteGeneralUser&generalUserID=<c:out value='${g.generalUserID}' />')" href="#">Delete</a> 
                        
                    </td>
                </tr>
            </c:forEach>   
           
        </tr>
    </table>
</body>
</html>

