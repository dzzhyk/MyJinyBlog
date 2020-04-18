package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggGeneral;
import domain.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import service.GeneralService;
import service.Impl.GeneralServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/servletUpdateGeneral")
public class ServletUpdateGeneral extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        final Map map = request.getParameterMap();
        // 为了防止提交空字符串，打上默认值
        JloggGeneral general = new JloggGeneral();

        try {
            BeanUtils.populate(general,  map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        GeneralService service = new GeneralServiceImpl();
        boolean flag = service.updateGeneral(general);
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
            response.setContentType("application/json;charset=utf-8");
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
