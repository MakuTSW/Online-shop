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
<form action="addCategory">
    <span id="information"> nazwa kategorii</span><br />
    <input type="text" name="name"><br />
    <span id="information"> opis</span><br />
    <input type="text" name="description"><br />
    <input type="submit">
</form>
</body>
</html>
