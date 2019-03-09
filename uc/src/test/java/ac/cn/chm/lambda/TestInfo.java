package ac.cn.chm.lambda;

public class TestInfo<T> {

    public static <T> T defaultValue(){
        return null;
    }

    public static <T> T defaultValue(T value, T defaultValue){
        return ( value != null ) ? value : defaultValue;
    }
}
