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
<form action="addSupplier">
    <span id="information"> nazwa dostawcy</span><br />
    <input type="text" name="name"><br />
    <span id="information"> kraj</span><br />
    <input type="text" name="country"><br />
    <span id="information"> miasto</span><br />
    <input type="text" name="city"><br />
    <span id="information"> numer telefonu</span><br />
    <input type="text" name="phone"><br />
    <span id="information"> email</span><br />
    <input type="text" name="email"><br />
    <input type="submit">
</form>
</body>
</html>
