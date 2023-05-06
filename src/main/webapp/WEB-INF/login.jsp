<%--
  Created by IntelliJ IDEA.
  User: yunho
  Date: 2023-05-04
  Time: 오후 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${param.result == 'error'}">
        <h1>로그인 에러</h1>
    </c:if>



    <form action="/login" method="post" >
        <input type="text" name="mid">
        <input type="text" name="mpw">

<%--       체크 시 LoginController에  auto라는 이름으로 체크박스로 전송 --%>
        <input type="checkbox" name="auto">
        <button type="submit"> LOGIN </button>
    </form>

</body>
</html>
