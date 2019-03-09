package ac.cn.chm.lambda;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Lambda {

    public static void main(String[] args) {
        chengfabiao();
    }

    public static void chengfabiao(){
        int a = 10,b = 12;
//        (a,b) -> System.out.println(a+b);

//        for (int i = 1; i <=9; i++) {
//            for (int j = 1; j <=i ; j++) {
//               System.out.print(j+"*"+i+"="+(i+j)+"\t");
//            }
//            System.out.println();
//        }
        //Arrays.asList(1,2,3,4).forEach (e -> System.out.println(e));
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.forEach(i -> {
            list.stream().filter((j) -> j<=i).forEach(jj -> System.out.print(jj+"*"+i+"="+(jj*i)+"\t"));
            System.out.println();
        });

        list.forEach(i -> {});

        for (Integer i:  list) {
            for(int j = 1;j <= i;j++){

            }
        }
    }

    public static void test1(){
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

//        for (String s:players) {
//            System.out.println(s+";");
//        }
// 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.println(player + "; "));

// 在 Java 8 中使用双冒号操作符(double colon operator)
//        players.forEach(System.out::println);
    }
}
