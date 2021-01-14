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
            font-size:35px;
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
${product}
<form action="addOrder">
    <span id="information"> Podaj ilość</span><br />
    <input type="text" name="unitAmount"><br />
    <input type="submit">
</form>
</body>
</html>
