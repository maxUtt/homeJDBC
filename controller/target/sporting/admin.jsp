<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="admin/adminMenu.jsp"></jsp:include>
    <p>Dear ${loginedUser.name} this is your page</p>
</body>
</html>
