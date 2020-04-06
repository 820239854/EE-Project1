package Service;

import Bean.GoodsUtil.Goods;
import Dao.GoodsDao;
import Dao.GoodsDaoImpl;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();
    private SpecService specService = new SpecServiceImpl();

    @Override
    public List<Goods> getGoodsByType(int typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    @Override
    public int deleteGoods(int id) {
        return 0;
    }

    @Override
    public int addGoods(Bean.Goods goods) {
        return goodsDao.addGoods(goods);
    }
}
