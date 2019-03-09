package ac.cn.chm.mall.util;

public class CheckUtil {

    /**
     * 校验字符串是否为空
     * @param str 要校验的字符串
     * @return 字符串为空返回true，否则返回false
     */
    public static boolean isEmpty(String str){
        if(str==null||"".equals(str)||"null".equals(str)){
            return true;
        }
        return false;
    }
}
