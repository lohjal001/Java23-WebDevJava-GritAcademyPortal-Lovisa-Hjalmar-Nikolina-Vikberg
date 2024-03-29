package servlets;

import com.sun.tools.javac.comp.Todo;
import models.MySQLConnector;
import models.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet("/userPage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserBean usersBean = req.getSession().getAttribute("userBean") != null ? (UserBean) req.getSession().getAttribute("userBean") : null;

        // UserBean usersBean = (UserBean) req.getSession().getAttribute("userBean");
        try {
            if (usersBean != null && usersBean.getUserType() == UserBean.USER_TYPE.student && usersBean.getPrivilageType() == UserBean.PRIVILAGE_TYPE.user && usersBean.getStateType() == UserBean.STATE_TYPE.confirmed) {

                //query for my student courses
                LinkedList<String[]> data = null; //sätter data lista till 0 så vi kan spara "courses" i den
                LinkedList<String[]> courses = MySQLConnector.getConnector().selectQuery("studentCourseInfo", ((UserBean) req.getSession().getAttribute("userBean")).getID());


                //TO CHECK IF DATA PRINTS OUT... heeyyy it doesnt....
                for (String[] course : courses) {
                    for (String courseInfo : course) {
                        System.out.print(courseInfo + " ");
                    }
                    System.out.println(); //  to the next line for each course
                }
                data = courses; //saving list of data from the query to "data"

                //query for classmates
                LinkedList<String[]> dataS = null;
                LinkedList<String[]> classmates = MySQLConnector.getConnector().selectQuery("showClassmates", ((UserBean) req.getSession().getAttribute("userBean")).getID());
                System.out.println(classmates);
                for (String[] students : classmates) {
                    for (String studentInfo : students) {
                        System.out.print(studentInfo + " ");
                    }
                }
                dataS = classmates;


                req.setAttribute("data", data);
                req.setAttribute("courses", courses);
                req.setAttribute("dataS", dataS);
                req.setAttribute("classmates", classmates);
                req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);

            } else if (usersBean != null &&
                    usersBean.getUserType() == UserBean.USER_TYPE.teacher &&
                    usersBean.getPrivilageType() == UserBean.PRIVILAGE_TYPE.user &&
                    usersBean.getStateType() == UserBean.STATE_TYPE.confirmed) {

                //query for which courses this teacher is registered on
                teachersCourses(usersBean, req, resp);
                //all courses query
                allCourses(usersBean, req, resp);
                //all students query
                allStudents(usersBean, req, resp);
                //query for all students registered and courses and teachers
                allStudentsCoursesTeachers(usersBean, req, resp);

                req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);


            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }

        } catch (Exception e) {
            System.out.println("something went wrong in user page servlet");
        }
    }

        //methods for retrieving the queries and setting the data to attributes

    public static void teachersCourses(UserBean usersBean, HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        LinkedList<String[]> data = null; //sätter data lista till 0 så vi kan spara "courses" i den
        LinkedList<String[]> courses = MySQLConnector.getConnector().selectQuery("teacherCourseInfo", ((UserBean) req.getSession().getAttribute("userBean")).getID());
        data = courses;
        req.setAttribute("data", data);
        req.setAttribute("courses", courses);

    }


    public static void allStudentsCoursesTeachers(UserBean usersBean, HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        LinkedList<String[]> data = null;
        LinkedList<String[]> allTables = MySQLConnector.getConnector().selectQuery("allStudentsCoursesTeachers", ((UserBean) req.getSession().getAttribute("userBean")).getID());
        data = allTables;
        req.setAttribute("data", data);
        req.setAttribute("allTables", allTables);

    }

    protected static void allCourses(UserBean usersBean, HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        LinkedList<String[]> data = null;
        LinkedList<String[]> allCourses = MySQLConnector.getConnector().selectQuery("allCourses", ((UserBean) req.getSession().getAttribute("userBean")).getID());
        data = allCourses;
        req.setAttribute("data", data);
        req.setAttribute("allCourses", allCourses);

    }


    protected static void allStudents(UserBean usersBean, HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        LinkedList<String[]> data = null;
        LinkedList<String[]> allStudents = MySQLConnector.getConnector().selectQuery("allStudents", ((UserBean) req.getSession().getAttribute("userBean")).getID());
        data = allStudents;
        req.setAttribute("data", data);
        req.setAttribute("allStudents", allStudents);

    }
}


/*
Userpage servlet should contain
- Student user connected to JSP file with student courses
- Teacher user connected to JSP file courses/students/student courses/teacher courses
- Teacher admin connected to JSP file with all tables and CRUD privilages on everything



//BUTTONS FOR TEACHER PAGE

 try {
String allSCTSubmit = req.getParameter("allSCTSubmit");
                    System.out.println(allSCTSubmit);
                    if (allSCTSubmit != null) {
//sätter data lista till 0 så vi kan spara "andra quries i den
data = null;
LinkedList<String[]> allTables = MySQLConnector.getConnector().selectQuery("allStudentsCoursesTeachers",
        ((UserBean) req.getSession().getAttribute("userBean")).getID());
data = allTables;
                        req.setAttribute("data", data);
                        req.setAttribute("allTables", allTables);
                        req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);


                    } else if (req.getParameter("allStudents") != null) {
allStudents();
                    } else if (req.getParameter("allCourses") != null) {
allCourses();
                    }

                            System.out.println("after submit button");
//usersBean.setData(allTables);

                } catch (Exception e) {
        }




        System.out.println(userBean);
        if (userBean != null && userBean.getUserType().equals(UserBean.USER_TYPE.student) && userBean.getStateType().equals((UserBean.STATE_TYPE.confirmed))) {
            LinkedList<String[]> data = null;
            LinkedList<String[]> courses = MySQLConnector.getConnector().selectQuery("studentCourseInfo", ((UserBean) req.getSession().getAttribute("UserBean")).getID());


            System.out.println("userpage servlet got here1");

            if (req.getParameter("studentSubmitButton") != null) {
                data = MySQLConnector.getConnector().selectQuery("studentCourseInfo", req.getParameter("courseId"));
                System.out.println("userpage servlet got here2");
            } else {
                data = courses;
            }

            req.setAttribute("data", data);
            req.setAttribute("courses", courses);
            req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
            req.getRequestDispatcher("JSP/fragments/student/studentUserPage.jsp").forward(req, resp);
            System.out.println("userpage servlet got here3");


            System.out.println("got here");


        } else if (userBean != null && userBean.getUserType().equals(UserBean.USER_TYPE.teacher) && userBean.getStateType().equals((UserBean.STATE_TYPE.confirmed))) {

            LinkedList<String[]> data = MySQLConnector.getConnector().selectQuery("teacherCourseInfo");
            req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);

        } else {
            req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
            System.out.println("userpage servlet something went wrong");
            // req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
        }


 */