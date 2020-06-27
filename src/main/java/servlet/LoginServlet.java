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

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        RequestDispatcher requestDispatcher = null;

        User query = userService.findByUsernameAndPassword(username, password);
        if (query == null) {
            //登录失败
            request.setAttribute("errorMessage", "用户名或密码错误！");
            requestDispatcher = request.getRequestDispatcher("index.jsp");
        } else {
            requestDispatcher = request.getRequestDispatcher("main.jsp");
        }

        requestDispatcher.forward(request, response);
    }
}
