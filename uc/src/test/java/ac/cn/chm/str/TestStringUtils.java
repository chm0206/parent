package ac.cn.chm.str;

import com.github.pagehelper.util.StringUtil;
import org.springframework.util.StringUtils;

public class TestStringUtils {
    public static void main(String[] args) {
        String s = "123";
        if(StringUtil.isNotEmpty(s)){
            System.out.println(123);
        }else{
            System.out.println(233);
        }
    }
}
