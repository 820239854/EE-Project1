package Utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpUtils {
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        OutputStream outputStream = new ByteArrayOutputStream();
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,length);
        }
        return outputStream.toString();
    }
}
