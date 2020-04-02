package Dao;

import Bean.Admin;
import Bean.User;
import Utils.MyJdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImp implements AdminDao{
    @Override
    public Admin login(Admin admin) {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "SELECT * FROM admin where email=? and pwd=?";
        Admin adminRes = null;
        try {
            adminRes = queryRunner.query(sql,new BeanHandler<>(Admin.class),admin.getEmail(),admin.getPwd());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return adminRes;
    }

    @Override
    public int addAdmin(Admin admin) {
        QueryRunner runner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "INSERT INTO admin(email,nickname,pwd) VALUES (?,?,?)";
        try {
            runner.update(sql,admin.getEmail(),admin.getNickname(),admin.getPwd());
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public List<Admin> queryAllAdmins() {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "SELECT * FROM admin";
        List<Admin> adminList = null;
        try {
            adminList = queryRunner.query(sql,new BeanListHandler<>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

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
    public int deleteAdmins(String id) {
        int index = Integer.parseInt(id);
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "DELETE FROM admin WHERE id=?";
        try {
            queryRunner.update(sql,index);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public int deleteUser(String id) {
        int id1 = Integer.parseInt(id);
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "DELETE FROM user WHERE id=?";
        try{
            queryRunner.update(sql,id);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
