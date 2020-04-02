package Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyJdbcUtils {
    public static DataSource getDruidDataSource(){
        Properties properties = new Properties();
        ClassLoader classLoader = MyJdbcUtils.class.getClassLoader();
        URL resource = classLoader.getResource("druid.properties");
        String path = resource.getPath();
//        System.out.println(path);
        DataSource dataSource = null;
        try(FileReader fileReader = new FileReader(path)){
            properties.load(fileReader);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dataSource.toString());
        return dataSource;
    }

    public static Connection getDruidConnection() throws SQLException {
        return getDruidDataSource().getConnection();
    }
}
