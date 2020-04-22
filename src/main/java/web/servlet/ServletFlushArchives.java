package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggTimeBar;
import domain.ResultInfo;
import service.ArticleService;
import service.Impl.ArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servletFlushArchives")
public class ServletFlushArchives extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        final ResultInfo info = new ResultInfo();
        info.setFlag(false);
        info.setErrorMsg("0");

        try {
            ArticleService service = new ArticleServiceImpl();
            List<JloggTimeBar> list = service.flushArchives();
            if (list!=null && list.size()!=0){
                info.setData(list);
                info.setErrorMsg("1");
                info.setFlag(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(info);
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
