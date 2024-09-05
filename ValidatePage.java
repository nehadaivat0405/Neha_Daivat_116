package curd.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

/**
 * Servlet implementation class ValidatePage
 */
@WebServlet("/ValidatePage")
public class ValidatePage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ValidatePage() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String uname = request.getParameter("uname");
        String upass = request.getParameter("upass");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/neha", "root", "abcd");
            
            // Query to check if the username and password match a record in the database
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Student1 WHERE uname = ? AND upass = ?");
            pst.setString(1, uname);
            pst.setString(2, upass);
            
            ResultSet rs = pst.executeQuery();

            if (rs.next()) { // If a record is found, credentials are correct
                response.sendRedirect("succesfull.html");
            } else { // If no record is found, credentials are incorrect
                response.sendRedirect("error.html");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}
