<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="table-cont">

<h2>All Courses</h2>

    <table>
        <tr>
            <th>Id</th>
            <th>Course</th>
            <th>YHP</th>




        </tr>

            <c:forEach items="${allCourses}" var="allC">
                <tr>
                        <td>${allC[0]}</td>
                        <td>${allC[1]}</td>
                        <td>${allC[2]}</td>



                </tr>
            </c:forEach>

    </table>
</div>