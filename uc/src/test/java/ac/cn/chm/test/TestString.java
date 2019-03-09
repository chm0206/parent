package ac.cn.chm.test;

public class TestString {
    public static void main(String[] args){
        BB b = new BB("1");
        AA a =new AA();
        System.out.println(a.getA());
        System.out.println(b.getB());
        a.setA(b.getB());
        System.out.println(a.getA());
    }
}