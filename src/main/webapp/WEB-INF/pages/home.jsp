<%--
  Created by IntelliJ IDEA.
  User: HAMMAX
  Date: 03.11.2015
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
  <title>DATA PAGE</title>
  <link rel="stylesheet" type="text/css" href ="<c:url value="/css/bootstrap.css"/>"/>
</head>
<body>

<div style ="margin-top: 210px;">
  <div align="center">
    <h2>Enter key to get data from resource </h2>

    <form action="send" method="post">
      <div class="form-group">
        <label for="exampleInputEmail1">Enter key Here </label>
        <input type="text" name="key" class="form-control" id="exampleInputEmail1" placeholder="KEY">
      </div>
      <label class="sr-only" for="exampleInputAmount">Write message </label>
      <div class="input-group">
        <div class="input-group-addon">Write message </div>
        <input type="text" name="echo" class="form-control" id="exampleInputAmount" placeholder="Write message here!">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>

  </div>
</div>
</body>
</html>
