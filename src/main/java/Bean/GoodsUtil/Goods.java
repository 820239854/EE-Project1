package Bean.GoodsUtil;

import java.util.List;

public class Goods {
    private  int id;
    private String name;
    private int typeId;
    private String img;
    private String desc;
    private Double price;
    private int stockNum;
    private List<GoodsSpec> specList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public List<GoodsSpec> getSpecList() {
        return specList;
    }

    public void setSpecList(List<GoodsSpec> specList) {
        this.specList = specList;
    }
}
