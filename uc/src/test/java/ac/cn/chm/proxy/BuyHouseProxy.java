package ac.cn.chm.proxy;

public class BuyHouseProxy implements  BuyHouse {
    private BuyHouse buyHouse;

    public BuyHouseProxy(final BuyHouse buyHouse){
        this.buyHouse = buyHouse;
    }
    @Override
    public void buyHouse() {
        System.out.println("重写了，突然又看不懂是想干嘛了");
        buyHouse.buyHouse();
    }
}
