<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<html lang="pl">
<head>
    <title>Shop</title>
    <meta charset="utf-8">
    <style>
        body
        {
            background-color: darkolivegreen;
            font-size: 50px;
        }
        #lewo
        {
            float: left;
            width: 50%;
        }
        #prawo
        {
            float: left;
            width: 50%;
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
        #komunikat
        {
            font-size: 25px;
            color: darkred;
        }
        #information
        {
            font-size: 30px;
        }
    </style>
</head>
<body>
<div id="lewo">
    <form action="login">
        <span id="komunikat">${resultLog}<br /></span>
        <span>Zaloguj się</span><br />
        <span id="information">Login:</span><br />
        <input type="text" name="login" /><br />
        <span id="information">Hasło:</span><br />
        <input type="password" name="password" /><br />
        <input type="submit" value="Zaloguj się" />
    </form>
</div>
<div id="prawo">
    <form action="add">
        <span id="komunikat">${resultReg}<br /></span>
        <span >Rejestracja</span><br />
        <span id="information">Login:</span><br />
        <input type="text" name="login" /><br />
        <span id="information">Hasło:</span><br />
        <input type="password" name="password" /><br />
        <span id="information">Email:</span><br />
        <input type="text" name="email" /><br />
        <input type="submit" value="Zarejestruj się" />
    </form>
</div>
<div style="clear:both"></div>
</body>
</html>