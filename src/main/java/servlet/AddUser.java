package servlet;

import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 添加新用户Servlet
 */

@WebServlet(name = "addUser", urlPatterns = "/addUser")
public class AddUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String addUsername = request.getParameter("addUsername");
        String addPassword = request.getParameter("addPassword");
        String addPasswordConfirm = request.getParameter("addPasswordConfirm");

        User user = new User();
        user.setUsername(addUsername);
        user.setPassword(addPassword);
        UserService userService = new UserServiceImpl();
        userService.save(user);


        writer.print("<div style=\"text-align: center\">添加成功！<br>\n" +
                "    <a href='userManager.jsp'>返回</a>\n" +
                "</div>");
    }
}
