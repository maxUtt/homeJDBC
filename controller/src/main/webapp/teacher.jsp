<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <p>Dear ${loginedUser.name} your salary in this month is ${loginedUser.salary}</p>
</body>
</html>
