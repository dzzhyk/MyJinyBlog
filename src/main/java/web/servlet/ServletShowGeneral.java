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

/**
 * General信息回显
 */
@WebServlet("/servletShowGeneral")
public class ServletShowGeneral extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        GeneralService service = new GeneralServiceImpl();
        final JloggGeneral general = service.flushGeneral();
        final ObjectMapper mapper = new ObjectMapper();
        try{
            final String s = mapper.writeValueAsString(general);
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
