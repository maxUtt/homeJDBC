<html>
<head>
    <title>Admin</title>
</head>
<body>
    <jsp:include page="../header.jsp"></jsp:include>
    <jsp:include page="adminMenu.jsp"></jsp:include>

    <form action="${pageContext.request.contextPath}/info-updating-student" method="post">
        <table border="0">
            <tr>
                <td>Input login student what you want to change</td>
                <td><input type="text" name="loginStudent" /></td>
            </tr>
            <tr>
                <td>New Login</td>
                <td><input type="text" name="newLoginStudent" /></td>
            </tr>
            <tr>
                <td>New Password</td>
                <td><input type="password" name="newPasswordStudent" /></td>
            </tr>
            <tr>
                 <td>New Name</td>
                 <td><input type="text" name="newNameStudent" /></td>
            </tr>
            <tr>
                <td>Age</td>
                <td><input type="text" name="newAgeStudent" /></td>
            </tr>
            <tr>
                <td>Role</td>
                <td>
                    <select name="role">
                       <option>STUDENT</option>
                    </select>
                <td>
            </tr>
            <tr>
                <td>Grade</td>
                <td><input type="text" name="newGrade" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>