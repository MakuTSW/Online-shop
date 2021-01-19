<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
    <title>Shop</title>
    <meta charset="utf-8">
    <style>
        body
        {
            background-color: darkolivegreen;
            font-size: 50px;
        }
        span
        {
            font-weight: bold;
        }
        input
        {
            background-color: greenyellow;
            font-size: 30px;
        }
        #information
        {
            font-size: 30px;
        }
    </style>
</head>
<body>
<form action="addEmployee">
    <span id="information"> login</span><br />
    <input type="text" name="login"><br />
    <span id="information"> hasło</span><br />
    <input type="text" name="password"><br />
    <span id="information"> email</span><br />
    <input type="text" name="email"><br />
    <span id="information"> imie</span><br />
    <input type="text" name="firstName"><br />
    <span id="information"> naziwsko</span><br />
    <input type="text" name="lastName"><br />
    <span id="information"> pensja</span><br />
    <input type="text" name="salary"><br />
    <input type="submit">
</form>
</body>
</html>
