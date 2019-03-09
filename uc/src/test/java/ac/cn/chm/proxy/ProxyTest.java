
package ac.cn.chm.proxy;


/**
 * @Auther: dan gao
 * @Description:
 * @Date: 22:43 2018/1/9 0009
 */
public class ProxyTest {
    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHouse();
        System.out.println("123");
        BuyHouseProxy buyHouseProxy = new BuyHouseProxy(buyHouse);
        buyHouseProxy.buyHouse();
    }
}