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
<form action="addProduct">
    <span id="information"> nazwa produktu</span><br />
    <input type="text" name="product"><br />
    <span id="information"> nazwa kategorii</span><br />
    <input type="text" name="category"><br />
    <span id="information"> nazwa dostawcy</span><br />
    <input type="text" name="supplier"><br />
    <span id="information"> ustaw cenę</span><br />
    <input type="text" name="unitPrice"><br />
    <span id="information"> podaj ilość produktów</span><br />
    <input type="text" name="unitAmount"><br />
    <input type="submit">
</form>
</body>
</html>
