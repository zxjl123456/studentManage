package servlet;

import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 修改用户密码
 */
@WebServlet(name = "modifyUserPassword", urlPatterns = "/modifyUserPassword")
public class ModifyUserPassword extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String inputUsername = request.getParameter("inputUsername");
        String inputPassword = request.getParameter("inputPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordConfirm = request.getParameter("newPasswordConfirm");
        RequestDispatcher requestDispatcher = null;
        User user = new User();
        user.setUsername(inputUsername);
        user.setPassword(newPassword);
        UserService userService = new UserServiceImpl();
        User temp = userService.findByUsernameAndPassword(inputUsername, inputPassword);
        if (temp == null) {
            writer.print("<div style=\"text-align: center\">修改失败，原用户名密码错误！<br>\n" +
                    "    <a href='userManager.jsp'>返回</a>\n" +
                    "</div>");
        } else {
            userService.save(user);
            requestDispatcher = request.getRequestDispatcher("userManager.jsp");
            requestDispatcher.forward(request, response);
        }


    }
}
