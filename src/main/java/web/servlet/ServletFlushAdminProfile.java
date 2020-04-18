package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggUserProfile;
import service.Impl.UserProfileServiceImpl;
import service.UserProfileService;

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

/**
 * @author dzzhyk
 */
@WebServlet("/servletFlushAdminProfile")
public class ServletFlushAdminProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserProfileService service = new UserProfileServiceImpl();
        // 加载管理员的信息
        JloggUserProfile profile = service.flushAdminProfile();

        // 如果查询不到用户信息或不是管理用户
        if (profile==null || !"admin".equals(profile.getUid())){
            profile = new JloggUserProfile("admin","selfdes","email","github","csdn");
        }

        // 如果设置内容为空就填上默认值
        final Class<? extends JloggUserProfile> gClass = profile.getClass();
        final Field[] fields = gClass.getDeclaredFields();
        for (Field f : fields){
            if ("java.lang.String".equals(f.getGenericType().getTypeName())){
                try {
                    final PropertyDescriptor pd = new PropertyDescriptor(f.getName(), gClass);
                    // 获取getter方法
                    Method mg = pd.getReadMethod();
                    // 遍历属性值，如果值为空就设置为属性值的名字
                    String str = (String) mg.invoke(profile);
                    if ("".equals(str.trim())){
                        Method ms = pd.getWriteMethod();
                        ms.invoke(profile, f.getName());
                    }
                } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                    e.printStackTrace();
                }
            }
        }

        final ObjectMapper mapper = new ObjectMapper();
        try {
            final String s = mapper.writeValueAsString(profile);
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
