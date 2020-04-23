package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.JloggUser;
import domain.ResultInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ArticleService;
import service.Impl.ArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@WebServlet("/servletAddNewArticle")
public class ServletAddNewArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        final ResultInfo info = new ResultInfo();
        info.setErrorMsg("创建文章失败！");
        info.setFlag(false);

        String title = "default";
        String shown = "Y";
        String text = "";
        int aid = -1;
        boolean isModify = false;       // 标记创建或者修改

        // 文件上传解析器工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 创建文件上传解析器
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("utf-8");

        // 判断是否为formData类型
        if (ServletFileUpload.isMultipartContent(request)){
            // 使用解析器解析上传数据
            try {
                List<FileItem> fileItems = sfu.parseRequest(request);
                for (FileItem item : fileItems) {
                    if (item.isFormField()){
                        String temp = item.getString("utf-8");
                        if ("title".equals(item.getFieldName())){
                            title = temp.trim();
                        }else if ("shown".equals(item.getFieldName())){
                            shown = temp;
                        }else if ("text".equals(item.getFieldName())){
                            text = temp;
                        }else if ("aid".equals(item.getFieldName())){
                            // 要修改已经存在的文章
                            isModify = true;
                            aid = Integer.parseInt(temp);
                        }
                    }
                }
            } catch (FileUploadException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        ArticleService service = new ArticleServiceImpl();
        JloggUser author = (JloggUser)request.getSession().getAttribute("user");
        if (isModify){
            // 如果是修改文章
            // 修改数据库中的profile文件
            aid = service.updateArticleProfile(aid, title, author.getUsername(), shown);
        }else {
            // 向数据库中写入新文件，创建新的文章Profile
            aid = service.createArticleProfile(title, author.getUsername(), shown);
        }

        if (aid != -1){
            try {
                // 创建文件并且写入text内容，返回存放路径
                final String PATH = request.getRealPath("/") + "archives";
                FileOutputStream fout = new FileOutputStream(new File(PATH+File.separator+aid));
                fout.write(text.getBytes());
                fout.close();
                // 创建新文件命名为aid同时设置文章content
                String description = "Article Entry";
                if (text.length() < 100){
                    description = text;
                }else {
                    description = text.substring(0, 99).replaceAll("#", "");
                }

                String path = request.getContextPath()+File.separator+"archives";   // text文件存放路径
                boolean flag = false;
                if (isModify){
                    flag = service.updateArticleContent(aid, description, path);
                }else {
                    flag = service.createArticleContent(aid, description, path);
                }
                if (flag && !isModify){
                    info.setErrorMsg("文章发布成功！");
                }else {
                    info.setErrorMsg("保存草稿成功！");
                }
                info.setFlag(true);
            }catch (Exception e){
                // 删除创建的profile信息
                service.removeArticleByAid(aid);
                e.printStackTrace();
            }
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
