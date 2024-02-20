<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<link rel='stylesheet' type='text/css' href='styles.css'>

<html>

<style>
    table {
        width: 70%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: #fff;
        border-radius: 5px;
    }
</style>
<nav>

    <%@ include file="studentNavbar.jsp" %>
</nav>

<h1>"Student User Page"</h1>

 <table>
        <thead>
            <tr>
                <th>Student ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Course Name</th>
                <th>Course Points</th>
                <th>Course Description</th>
                 <th>Teacher First Name</th>
                 <th>Teacher Last Name</th>
            </tr>
        </thead>
        <tbody>

<c:forEach items="${UserBean.data}" var="dataPoint">
    <tr>
        <td>${dataPoint[0]}</td>
        <td>${dataPoint[1]}</td>
        <td>${dataPoint[2]}</td>
        <td>${dataPoint[3]}</td>
        <td>${dataPoint[4]}</td>
        <td>${dataPoint[5]}</td>
        <td>${dataPoint[6]}</td>
        <td>${dataPoint[7]}</td>
        <td>${dataPoint[8]}</td>
    </tr>
</c:forEach>
        </tbody>
 </table>
</body>
</html>