package Dao;

import Bean.Admin;
<<<<<<< HEAD
import Bean.AdminPwd;
import Bean.GoodsUtil.Goods;
import Bean.Message;
=======
import Bean.PWD;
>>>>>>> origin/master
import Bean.User;
import Utils.MyJdbcUtils;
import com.alibaba.druid.util.StringUtils;
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
    public int changePwd(PWD pwd) throws SQLException {
        if (StringUtils.isEmpty(pwd.getAdminToken()) ||
            StringUtils.isEmpty(pwd.getConfirmPwd()) ||
            StringUtils.isEmpty(pwd.getOldPwd())     ||
            StringUtils.isEmpty(pwd.getNewPwd())){
            return 100;
        }
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String querySelect = "SELECT pwd FROM admin WHERE email = '" + pwd.getAdminToken() + "'";
        try {
            Admin admin = queryRunner.query(querySelect,new BeanHandler<>(Admin.class));
            if (!pwd.getOldPwd().equals(admin.getPwd())){
                return 101;
            }
            if (pwd.getOldPwd().equals(pwd.getNewPwd())){
                return 102;
            }
            if (!pwd.getNewPwd().equals(pwd.getConfirmPwd())){
                return 103;
            }
            String queryUpdate = "UPDATE admin SET pwd='" + pwd.getConfirmPwd()
                          + "' WHERE email='" + pwd.getAdminToken()
                          + "'";
            int i = queryRunner.update(queryUpdate);
            if (i == 1){
                return 0;
            }else {
                return 104;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 104;
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
    public int changePwd(AdminPwd pwd) {
        if (StringUtils.isEmpty(pwd.getAdminToken()) || StringUtils.isEmpty(pwd.getOldPwd()) ||
                StringUtils.isEmpty(pwd.getNewPwd()) || StringUtils.isEmpty(pwd.getConfirmPwd())) {
            return 100;
        }

        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "select pwd from admin where email = '" + pwd.getAdminToken() + "'";
        System.out.println(sql);
        try {
            Admin admin = queryRunner.query(sql, new BeanHandler<>(Admin.class));
            if (!pwd.getOldPwd().equals(admin.getPwd())) {
                //原密码输入不正确
                return 101;
            }

            if (pwd.getOldPwd().equals(pwd.getNewPwd())) {
                //原密码与新密码一致
                return 102;
            }

            if (!pwd.getNewPwd().equals(pwd.getConfirmPwd())) {
                //两次密码输入不同
                return 103;
            }

            //输入正确
            String sql2 = "UPDATE admin SET pwd='" + pwd.getConfirmPwd() + "' WHERE email='" + pwd.getAdminToken() + "'";
            int i = queryRunner.update(sql2);
            if (i == 1) {
                return 0;
            } else {
                return 104;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //异常
            return 104;
        }

    }

    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "select * from admin where 2=2 ";
        if (!StringUtils.isEmpty(admin.getNickname())) {
            sql = sql + "and nickname like '" + admin.getNickname() + "%'";
        }

        if (!StringUtils.isEmpty(admin.getEmail())) {
            sql = sql + "and email like '" + admin.getEmail() + "%'";
        }

        List<Admin> adminList = null;
        try {
            adminList = queryRunner.query(sql, new BeanListHandler<>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }



    @Override
    public List<Message> getNoReplyMsg() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "SELECT * FROM message where state=1";
        List<Message> messageList = null;
        try {
            messageList = queryRunner.query(sql,new BeanListHandler<>(Message.class));
            for (Message m : messageList) {
                User user = queryRunner.query("select name from goods where id='" + m.getUserId()
                        + "'", new BeanHandler<>(User.class));
                Goods query = queryRunner.query("SELCET name from goods where id='" + m.getGoodsId()
                        + "'", new BeanHandler<>(Goods.class));
                m.setUser(user);
                m.setGoods(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
