package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggGeneral;
import domain.JloggUser;
import domain.JloggUserProfile;
import domain.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import service.GeneralService;
import service.Impl.GeneralServiceImpl;
import service.Impl.UserProfileServiceImpl;
import service.UserProfileService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/servletUpdateAdminProfile")
public class ServletUpdateAdminProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final Map map = request.getParameterMap();
        // 为了防止提交空字符串，打上默认值
        JloggUserProfile profile = new JloggUserProfile();

        try {
            BeanUtils.populate(profile,  map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 获取当前用户的uid
        final JloggUser user = (JloggUser) request.getSession().getAttribute("user");
        profile.setUid(user.getUid());

        UserProfileService service = new UserProfileServiceImpl();
        boolean flag = service.updateProfile(profile);
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
