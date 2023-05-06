<%--
  Created by IntelliJ IDEA.
  User: yunho
  Date: 2023-04-29
  Time: 오후 4:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo/register" method="post">
  <div>
    <input type="text" name="title" placeholder="INSERT TITLE">
  </div>
  <div>
    <input type="date" name="dueDate">
  </div>
  <div>
    <button>RESET</button>
    <button>REGISTER</button>
  </div>
</form>
</body>
</html>
