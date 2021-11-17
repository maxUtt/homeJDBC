<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <p>Id: ${loginedUser.id}</p>
    <p>Name: ${loginedUser.name}</p>
    <p>Role: ${loginedUser.role}</p>
    <p>Age: ${loginedUser.age}</p>
</body>
</html>
