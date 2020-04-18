package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggGeneral;
import domain.JloggUser;
import domain.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import service.GeneralService;
import service.Impl.GeneralServiceImpl;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/servletUpdateAccount")
public class ServletUpdateAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final Map map = request.getParameterMap();
        JloggUser user = new JloggUser();

        try {
            BeanUtils.populate(user,  map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        根据当前登录信息获取完整用户信息
        final JloggUser temp = (JloggUser) request.getSession().getAttribute("user");
        user.setUid(temp.getUid());


        UserService service = new UserServiceImpl();
        boolean flag = service.updateAccount(user);
        final ResultInfo info = new ResultInfo();
        info.setFlag(flag);
        if (!flag){
            info.setErrorMsg("update failed...");
        }else {
            info.setErrorMsg("update successful!");
        }

        try{
            final ObjectMapper mapper = new ObjectMapper();
            final String s = mapper.writeValueAsString(info);
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
