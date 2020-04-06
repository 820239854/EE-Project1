package Service;

import Bean.GoodsUtil.Goods;
import Bean.Type;
import Dao.TypeDao;
import Dao.TypeDaoImpl;
import java.util.List;

public class TypeServiceImpl implements TypeService {
    private TypeDao typeDao = new TypeDaoImpl();

    @Override
    public List<Type> getType() {
        return typeDao.getType();
    }

    @Override
    public int addType(Type newType) {
        return typeDao.addType(newType);
    }

    @Override
    public int deleteType(int typeId) {
        int status = 0;
        GoodsService goodsService = new GoodsServiceImpl();
        status += typeDao.deleteType(typeId);
        List<Goods> goodsList = goodsService.getGoodsByType(typeId);
        for (Goods tmp : goodsList) {
            status += goodsService.deleteGoods(tmp.getId());
        }
        if (status > 0) {
            return 500;
        }
        return 0;
    }
}
