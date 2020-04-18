package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggUserProfile;
import service.Impl.UserProfileServiceImpl;
import service.UserProfileService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletShowAdminProfile")
public class ServletShowAdminProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserProfileService service = new UserProfileServiceImpl();
        final JloggUserProfile profile = service.flushAdminProfile();
        final ObjectMapper mapper = new ObjectMapper();
        try{
            final String s = mapper.writeValueAsString(profile);
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
