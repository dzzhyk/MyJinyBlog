package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ResultInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * 上传图片
 */
@WebServlet("/servletUploadImg")
public class ServletUploadImg extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // 返回信息
        ResultInfo info = new ResultInfo();
        info.setFlag(false);
        info.setErrorMsg("上传失败！");

        // 要设置的文件名称
        String filename = "default_"+new Date().toString();;
        String filepath = "/img";

        // 文件上传解析器工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 创建文件上传解析器
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("utf-8");
        sfu.setFileSizeMax(1024 * 1024);
        sfu.setSizeMax(1024*1024);
        // 判断enctype，检查上传内容是否为formData类型
        if (ServletFileUpload.isMultipartContent(request)){
            try {
                // 使用解析器解析上传数据
                List<FileItem> fileItems = sfu.parseRequest(request);
                for (FileItem item : fileItems) {
                    // 判断item是普通字段
                    if (item.isFormField()){
                        if ("name".equals(item.getFieldName())){
                            filename = item.getString("utf-8");
                        }else if ("path".equals(item.getFieldName())){
                            filepath = this.getServletContext().getRealPath(item.getString("utf-8"));
                        }
                    }
                }
                for (FileItem item : fileItems) {
                    // 判断item是上传文件
                    if (!item.isFormField()){
                        // 是上传的文件
                        // 处理空名文件
                        if ("".equals(item.getName()) || "".equals(item.getName().trim())){
                            continue;
                        }
                        // 输入输出流创建文件
                        InputStream fis = item.getInputStream();
                        FileOutputStream fos = new FileOutputStream(filepath + "/" + filename);
                        byte[] bytes = new byte[1024];
                        int len = -1;
                        while ((len = fis.read(bytes))!=-1){
                            fos.write(bytes, 0, len);
                        }
                        fos.close();
                        fis.close();
                    }
                }
                info.setFlag(true);
                info.setErrorMsg("上传成功！");
            } catch (FileUploadException | IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
