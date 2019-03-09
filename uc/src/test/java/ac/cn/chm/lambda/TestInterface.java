package ac.cn.chm.lambda;

public interface TestInterface {
    /**
     * 不需要已实现的子类实现该方法，同时又可以调用该方法
     * @return
     */
    default String testDefault(){
        return "testDefault";
    }
    static boolean isEmpty(String str){
        if(str == null ||"".equals(str)){
            return true;
        }
        return false;
    }

}
