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
import java.util.List;

@WebServlet("/servletFlushArticleProfileByLimit")
public class ServletFlushArticleProfileByLimit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        // 返回信息
        final ResultInfo info = new ResultInfo();
        info.setFlag(false);
        info.setErrorMsg("0");
        try {
            String parameterCount = request.getParameter("count");
            String parameterCurrentNumber = request.getParameter("currentNumber");
            int count = Integer.parseInt(parameterCount);
            int currentCount = Integer.parseInt(parameterCurrentNumber);
            ArticleService service = new ArticleServiceImpl();
            List<JloggArticleProfile> profiles = service.flushProfileByLimit(currentCount, count);

            // 判断profiles是否为空
            if (profiles != null && profiles.size()!=0){
                // 将获得的profiles列表写入data
                info.setData(profiles);
                info.setErrorMsg("1");
                info.setFlag(true);
            }
        }catch (Exception e){
            e.printStackTrace();
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
