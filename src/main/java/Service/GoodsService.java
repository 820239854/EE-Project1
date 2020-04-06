package Service;

import Bean.GoodsUtil.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getGoodsByType(int typeId);

    int deleteGoods(int id);

    int addGoods(Bean.Goods goods);
}
