<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Handling Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1>Login</h1>
    <form action="#" th:action="@{/}" th:object="${login}" method="post">
    	<p>Login: <input type="text" th:field="*{login}" /></p>
        <p>Password: <input type="password" th:field="*{password}" /></p>
        <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
    </form>
</body>
</html>