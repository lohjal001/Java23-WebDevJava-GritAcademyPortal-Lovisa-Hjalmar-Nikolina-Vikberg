<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="/styles.css">

<html>
<%@ include file="fragments/header.jsp" %>

<body>
<%@ include file="fragments/navbar.jsp" %>

<div class='bg-white'>

 <table>
        <thead>
            <tr>
                <th>Course</th>
                <th>Points</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
        </div>


<c:forEach items="${UserBean.data }" var="dataPoint">
    <tr>
        <td>${dataPoint[0]}</td>
        <td>${dataPoint[1]}</td>
        <td>${dataPoint[2]}</td>
    </tr>
</c:forEach>
</body>
</table>
</html>