<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registration Success</title>
    <style type="text/css">
        span {
            display: inline-block;
            width: 200px;
            text-align: left;
        }
    </style>
</head>
<body>
<div align="center">
    <h2>Registration Succeeded!</h2>
    <span>First Name:</span><span>${person.firstName}</span><br/>
    <span>Last Name:</span><span>${person.lastName}</span><br/>
    <span>Phone Number:</span><span>${person.phoneNumber}</span><br/>
</div>
<br>
<a href="${pageContext.request.contextPath}/persons/all_persons">Back to all persons</a>
</body>
</html>
