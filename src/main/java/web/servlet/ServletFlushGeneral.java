package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggGeneral;
import service.GeneralService;
import service.Impl.GeneralServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletFlushGeneral")
public class ServletFlushGeneral extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        GeneralService service = new GeneralServiceImpl();
        JloggGeneral general = service.flushGeneral();

        if (general==null){
            general = new JloggGeneral("title", "name", "description",
                    "htmlDescription", "htmlCopyright", "beian");
        }

        final ObjectMapper mapper = new ObjectMapper();
        try {
            final String s = mapper.writeValueAsString(general);
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
