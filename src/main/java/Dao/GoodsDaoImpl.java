package Dao;

import Bean.GoodsUtil.Goods;
import Utils.MyJdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao{
    @Override
    public List<Goods> getGoodsByType(int typeId) {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        List<Goods> res = null;
        try {
            res = queryRunner.query("SELECT * FROM goods WHERE typeId=?",
                    new BeanListHandler<>(Goods.class),typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int addGoods(Bean.Goods goods) {
        QueryRunner queryRunner = new QueryRunner(MyJdbcUtils.getDruidDataSource());
        String sql = "INSERT INTO goods (`name`,`typeId`,`img`,`desc`) values(?,?,?,?)";
        long id = 0L;
        try {
            id = queryRunner.insert(sql,
                    new ScalarHandler<Long>(),
                    goods.getName(), goods.getTypeId(),
                    goods.getImg(),  goods.getDesc());
            goods.setId((int) id);
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }
        return 0;
    }
}