package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggGeneral;
import service.GeneralService;
import service.Impl.GeneralServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/servletFlushGeneral")
public class ServletFlushGeneral extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        GeneralService service = new GeneralServiceImpl();
        JloggGeneral general = service.flushGeneral();
        if (general==null){
            general = new JloggGeneral(
                    "website title here",
                    "Blog's name here",
                    "Your description here",
                    "HTML doc Description here",
                    "HTML doc Copyright info here",
                    "beian info in footer here"
            );
        }

        // 如果设置内容为空就填上默认值
        final Class<? extends JloggGeneral> gClass = general.getClass();
        final Field[] fields = gClass.getDeclaredFields();
        for (Field f : fields){
            if ("java.lang.String".equals(f.getGenericType().getTypeName())){
                try {
                    final PropertyDescriptor pd = new PropertyDescriptor(f.getName(), gClass);
                    // 获取getter方法
                    Method mg = pd.getReadMethod();
                    // 遍历属性值，如果值为空就设置为属性值的名字
                    String str = (String) mg.invoke(general);
                    if ("".equals(str.trim())){
                        Method ms = pd.getWriteMethod();
                        ms.invoke(general, f.getName());
                    }
                } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                    e.printStackTrace();
                }
            }
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
