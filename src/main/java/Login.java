package com;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/Login")
public class Login extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection;
        ResultSet resultSet;
        Statement statement;
        ServletContext application = getServletConfig().getServletContext();
        resp.setContentType("text/html");
        PrintWriter printWriter=resp.getWriter();
        String email=req.getParameter("email");
        String number=req.getParameter("number");
        try
        {

            Class.forName(application.getInitParameter("Driver"));
            connection = DriverManager.getConnection(application.getInitParameter("Url"),application.getInitParameter("Username"), application.getInitParameter("Password"));
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select number from Person_Details where email='"+email+"';");
            while (resultSet.next())
            {
                if (resultSet.getString(1).equals(number))
                {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("Welcome");
                    requestDispatcher.forward(req,resp);
                }
                else
                {
                    printWriter.println("Your input is wrong");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.html");
                    requestDispatcher.include(req,resp);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
