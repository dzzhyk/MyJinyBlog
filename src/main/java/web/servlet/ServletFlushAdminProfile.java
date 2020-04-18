package web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggUserProfile;
import service.Impl.UserProfileServiceImpl;
import service.UserProfileService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dzzhyk
 */
@WebServlet("/servletFlushAdminProfile")
public class ServletFlushAdminProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserProfileService service = new UserProfileServiceImpl();
        // 加载管理员的信息
        JloggUserProfile profile = service.flushAdminProfile();

        // 如果查询不到用户信息或不是管理用户
        if (profile==null || !"admin".equals(profile.getUid())){
            profile = new JloggUserProfile("admin","selfdes","email","github","csdn");
        }

        final ObjectMapper mapper = new ObjectMapper();
        try {
            final String s = mapper.writeValueAsString(profile);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
