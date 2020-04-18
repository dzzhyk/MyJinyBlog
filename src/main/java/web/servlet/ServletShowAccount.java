package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggUser;
import domain.JloggUserProfile;
import service.Impl.UserProfileServiceImpl;
import service.Impl.UserServiceImpl;
import service.UserProfileService;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Account页面回显
 */
@WebServlet("/servletShowAccount")
public class ServletShowAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserService service = new UserServiceImpl();

        final JloggUser temp = (JloggUser) request.getSession().getAttribute("user");
        // 根据当前登录信息获取完整用户信息
        JloggUser user = service.login(temp);
        final ObjectMapper mapper = new ObjectMapper();
        try{
            final String s = mapper.writeValueAsString(user);
            response.setContentType("application/json");
            response.getWriter().write(s);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
