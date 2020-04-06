package Dao;

import Bean.Type;
import Utils.MyJdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {
    @Override
    public List<Type> getType() {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "SELECT * FROM type";
        List<Type> query = null;
        try {
            query = queryRunner.query(sql,new BeanListHandler<>(Type.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int addType(Type newType) {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "Insert into type values(?,?)";
        Type query = null;
        try {
            queryRunner.insert(sql,new BeanHandler<>(Type.class),newType.getId(),newType.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
        return 0;
    }

    @Override
    public int deleteType(int typeId) {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "DELETE FROM type WHERE id=?";
        try {
            queryRunner.execute(sql,typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
