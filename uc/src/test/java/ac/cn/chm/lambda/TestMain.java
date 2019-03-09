package ac.cn.chm.lambda;

import org.springframework.context.annotation.ComponentScan;

public class TestMain {
    public static void main(String[] args) {
//        TestInterface ti = new TestInterfaceImpl();
//        System.out.println(ti.testDefault());
//        System.out.println(TestInterface.isEmpty("false"));
        test();
    }
    public static void test(){
        System.out.println((String)TestInfo.defaultValue());
        System.out.println((String)TestInfo.defaultValue("String","string"));
    }


}
