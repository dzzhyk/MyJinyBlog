package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggArticleProfile;
import domain.ResultInfo;
import service.ArticleService;
import service.Impl.ArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletFlushArticleProfileByAid")
public class ServletFlushArticleProfileByAid extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        ResultInfo info = new ResultInfo();
        info.setErrorMsg("0");
        info.setFlag(false);

        try {
            int aid = Integer.parseInt(request.getParameter("aid"));
            ArticleService service = new ArticleServiceImpl();
            JloggArticleProfile profile = service.findProfileByAid(aid);

            if (profile!=null){
                info.setData(profile);
                info.setErrorMsg("1");
                info.setFlag(true);
                ObjectMapper mapper = new ObjectMapper();
                final String s = mapper.writeValueAsString(info);
                response.setContentType("application/json");
                response.getWriter().write(s);
            }
        }catch (NumberFormatException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
