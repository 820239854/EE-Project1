package Dao;

import Bean.GoodsUtil.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> getGoodsByType(int typeId);

    int addGoods(Bean.Goods goods);
}
