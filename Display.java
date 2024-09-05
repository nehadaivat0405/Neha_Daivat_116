package curd.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Display extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Display() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String uname = request.getParameter("uname");
        String upass = request.getParameter("upass");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/neha", "root", "abcd");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Student1");
            ResultSet rs = pst.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("body {font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(to right, #4facfe, #00f2fe); color: #333; margin: 0; padding: 0;}");
            out.println(".container {width: 90%; margin: 30px auto; padding: 20px; background-color: #fff; border-radius: 15px; box-shadow: 0 10px 20px rgba(0,0,0,0.1);}");
            out.println("h1 {text-align: center; color: #333;}");
            out.println("table {width: 100%; border-collapse: collapse; margin: 20px 0;}");
            out.println("table, th, td {border: 1px solid #ccc;}");
            out.println("th, td {padding: 12px; text-align: center;}");
            out.println("th {background-color: #007bff; color: white;}");
            out.println("tr:nth-child(even) {background-color: #f9f9f9;}");
            out.println("tr:hover {background-color: #f1f1f1;}");
            out.println("a {display: inline-block; margin: 10px 0; padding: 10px 20px; color: white; background: linear-gradient(to right, #ff416c, #ff4b2b); text-decoration: none; border-radius: 25px;}");
            out.println("a:hover {background: linear-gradient(to right, #ff4b2b, #ff416c);}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Student Records</h1>");
            out.println("<table>");
            out.println("<tr><th>Username</th><th>Password</th><th>First Name</th><th>Last Name</th><th>Email</th></tr>");

            while (rs.next()) {
                uname = rs.getString(1);
                upass = rs.getString(2);
                fname = rs.getString(3);
                lname = rs.getString(4);
                email = rs.getString(5);

                out.println("<tr>");
                out.println("<td>" + uname + "</td>");
                out.println("<td>" + upass + "</td>");
                out.println("<td>" + fname + "</td>");
                out.println("<td>" + lname + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><a href='signup.html'>Insert Record</a><br>");
            out.println("<a href='del.html'>Delete Record</a><br>");
            out.println("<a href='up.html'>Update Record</a><br>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
