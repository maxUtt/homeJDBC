<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <jsp:include page="../header.jsp"></jsp:include>
    <jsp:include page="adminMenu.jsp"></jsp:include>

    <form action="${pageContext.request.contextPath}/info-delete-student" method="post">
        <table border="0">
            <tr>
                <td>Input login student who you want delete</td>
                <td><input type="text" name="loginStudent" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>