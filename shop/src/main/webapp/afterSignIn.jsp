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
        table
        {
            width:100%;
        }
        th
        {
            border-color: brown;
            font-size: 35px;
        }
        td
        {
            font-size: 30px;
            text-align: center;
        }
        .buttonintable
        {
            size: 40px;
            margin:auto;
            display:flex;
        }
        table, th, td {
            border: 3px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
        }
    </style>

</head>
<body>
<span>${resultLog} </span>
${addProduct}
<span style="font-size: 35px; color: darkred">${orderMessage}</span>
${result}

</body>
</html>
