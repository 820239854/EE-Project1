package Utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
public class FileUploadUtils {
    /**
     * 用来获取form表单传入的params的map，以便用Beanutils将数据封装到对象
     * @param request
     * @return 返回一个获取form表单传入的params的map
     */
    public static Map<String, Object> getFileParams(HttpServletRequest request) {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //设置文件最大值为1mb
        fileUpload.setSizeMax(1024000);
        // Parse the request
        //处理中文文件名乱码问题
        fileUpload.setHeaderEncoding("UTF-8");

        HashMap<String, Object> map = new HashMap<>();
        try {

            List<FileItem> items = fileUpload.parseRequest(request);

            for (FileItem item : items) {
                //如果不是普通表单
                if (!item.isFormField()) {
                    processFileParams(item, map, request);
                } else {//如果是普通表单
                    processFormParams(item, map);
                }
            }
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    private static void processFileParams(FileItem item, Map<String, Object> map, HttpServletRequest request) {
        String itemName = item.getName();
        //处理一个目录文件可能过多问题
        int hash = itemName.hashCode();
        String s1 = Integer.toHexString(hash);
        char[] chars = s1.toCharArray();
        String hashpath = "\\myUpload";
        for (char c : chars) {
            hashpath = hashpath + "\\" + c;
        }
        //加上UUID处理重名文件问题
        String s = UUID.randomUUID().toString();
        itemName = hashpath + s + "-" + itemName;
        String realPath = request.getServletContext().getRealPath(itemName);

        File file1 = new File(realPath);
        System.out.println(file1);

        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        //将文件传入指定路径
        try {
            item.write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put(item.getFieldName(), itemName);
    }



    private static void processFormParams(FileItem item, Map<String, Object> map) {
        String fieldName = item.getFieldName();
        String value = null;
        try {
            value = item.getString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //针对，checkbox采用拼接字符串的方式，解决hobby覆盖问题
        if (map.containsKey(fieldName)) {
            value = map.get(fieldName) + "," + value;
        }
        map.put(fieldName, value);
    }

}
