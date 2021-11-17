<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <jsp:include page="../header.jsp"></jsp:include>
    <jsp:include page="adminMenu.jsp"></jsp:include>

    <form action="${pageContext.request.contextPath}/info-adding-student" method="post">
        <table border="0">
            <tr>
                <td>Input login student</td>
                <td><input type="text" name="loginStudent" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="passwordStudent" /></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="nameStudent" /></td>
            </tr>
            <tr>
                <td>Age</td>
                <td><input type="text" name="ageStudent" /></td>
            </tr>
            <tr>
                <td>Role</td>
                <td>
                    <select name="role">
                        <option>STUDENT</option>
                        <option>TEACHER</option>
                    </select>
                <td>
            </tr>
            <tr>
                <td>Grade</td>
                <td><input type="text" name="grade" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>