package ac.cn.chm.test;

import java.lang.reflect.Field;

public class TestVar {
    String loginOrder = "1";
    String authOrder = "2";

    public static void main(String[] args) {
        //System.out.println(test2()&&test1()||test3());
        //System.out.println(test1()&&test2()||test3());
        System.out.println(test2()||test1()&&test3());//&&会归并到一起
        System.out.println(test2()||(test1()&&test3()));//&&会归并到一起,但是执行不会优先执行
        System.out.println((test2()||test1())&&test3());//&&会归并到一起
        //System.out.println(test1()||test2()&&test3());
        //Var var = new Var();
        //test(var);
    }
    public static boolean test1(){
        System.out.println(1);
        return false;
    }
    public static boolean test2(){
        System.out.println(2);
        return true;
    }
    public static boolean test3(){
        System.out.println(3);
        return true;
    }

    public static void test(Var var) {

        String type = "login";
        Field[] fields = var.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 对于每个属性，获取属性名
            String varName = field.getName();
            try {
                boolean access = field.isAccessible();
                if (!access)
                    field.setAccessible(true);                        //从obj中获取field变量
                Object o = field.get(var);
                System.out.println("变量： " + varName + " = " + o);
                if (!access) field.setAccessible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
