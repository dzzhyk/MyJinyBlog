package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggUser;
import domain.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/servletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        final String CHECKCODE_ATTR = "CHECKCODE_SERVER";
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = request.getParameterMap();
        UserService userService = new UserServiceImpl();

        // 返回信息
        ResultInfo info = new ResultInfo();

        // 检查验证码
        final HttpSession session = request.getSession();
        final String checkCode = (String) session.getAttribute(CHECKCODE_ATTR);
        String userCode = (String) request.getParameter("verifycode");
        session.removeAttribute(CHECKCODE_ATTR);
        if (checkCode==null || !checkCode.equalsIgnoreCase(userCode)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            try {
                //将info对象序列化为json
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(objectMapper.writeValueAsString(info));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        JloggUser jloggUser = new JloggUser();
        try {
            // 封装新对象
            BeanUtils.populate(jloggUser, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 调用service查询回user
        JloggUser user = null;
        user = userService.login(jloggUser);
        if (user==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }else {
            // 登录成功
            session.setAttribute("user", user);
            info.setFlag(true);
            info.setData(user);
        }

        try {
            // 返回json信息
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(info));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
