package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ResultInfo;
import service.ArticleService;
import service.Impl.ArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletRemoveArticle")
public class ServletRemoveArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String parameter = request.getParameter("aid");
        final int aid = Integer.parseInt(parameter);
        ArticleService service = new ArticleServiceImpl();
        boolean flag = service.removeArticleByAid(aid);

        ResultInfo info = new ResultInfo();
        info.setFlag(false);
        info.setErrorMsg("删除失败！");
        if (flag){
            info.setFlag(true);
            info.setErrorMsg("删除成功");
        }
        try {
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
