<%--
  Created by IntelliJ IDEA.
  User: Szymon
  Date: 6/29/2020
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>Insert Data</title>
</head>
<body>

<form action="./ShowCandidates" method="get">
  <p>Głosowanie</p>
  <input type="submit" value="Wybierz"/>
</form>
<form action="./ShowResult" method="get">
  <p>Wyniki:</p>
  <input type="submit" value="Wybierz"/>
</form>
<form action="./Reset" method="get">
  <p>Reset:</p>
  <input type="submit" value="Wybierz"/>
</form>



</body>
</html>
