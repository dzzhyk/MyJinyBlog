package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggArticleContent;
import domain.ResultInfo;
import service.ArticleService;
import service.Impl.ArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletAddOneView")
public class ServletAddOneView extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try{
            final String parameterAid = request.getParameter("aid");
            int aid = Integer.parseInt(parameterAid);
            ArticleService service = new ArticleServiceImpl();
            service.addOneView(aid);
            JloggArticleContent content = service.findContentByAid(aid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
