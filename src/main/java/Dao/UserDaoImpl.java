package Dao;

import Bean.User;
import Utils.MyJdbcUtils;
import com.alibaba.druid.util.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public List<User> queryAllUsers(){
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "SELECT * FROM user";
        List<User> userList = null;
        try {
            userList = queryRunner.query(sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int deleteUser(String id) {
        int requestId = Integer.parseInt(id);
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "DELETE FROM user WHERE id=?";
        try{
            queryRunner.update(sql,requestId);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }


    @Override
    public List<User> searchUser(String word) {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "select * from user where 2=2 ";
        if (!StringUtils.isEmpty(word)) {
            sql = sql + "and nickname like '" + word + "%'";
        }
        System.out.println(sql);

        List<User> userList = null;
        try {
            userList = queryRunner.query(sql, new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User queryUserById(Integer userId) {
        return null;
    }

    @Override
    public User queryUserNameById(Integer userId) {
        return null;
    }
}
