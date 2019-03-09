package ac.cn.chm.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ac.cn.chm.consts.ParamConst;

public class CheckUtil {
    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        return object == null ? true : false;
    }

    /**
     * 判断对象是否非空
     *
     * @param object
     * @return
     */
    public static boolean notEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 判断对象是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() <= 0;
    }

    /**
     * 判断对象是否非空
     *
     * @param list
     * @return
     */
    public static boolean notEmpty(List<?> list) {
        return !isEmpty(list);
    }

    public static <T> boolean isEmpty(T[] list) {
        return list == null || list.length <= 0 ? true : false;
    }

    public static <T> boolean notEmpty(T[] list) {
        return isEmpty(list);
    }

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param str
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }

    /**
     * 检测字符串是否不为空(null,"","null")
     *
     * @param str
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String str) {
        return str != null && !"".equals(str) && !"null".equals(str);
    }

    /**
     * 检测字符串数组是否有为空的
     *
     * @param str 字符串数组
     * @return 有为空的数组返回true，都不为空返回false
     */
    public static boolean isEmptyOr(String... str) {
        for (String string : str) {
            if (isEmpty(string))
                return true;
        }
        return false;
    }

    /**
     * 检测字符串数组是否全部为空
     *
     * @param str
     * @return 有不为空的数组则返回false, 都为空返回true
     */
    public static boolean isEmptyAnd(String... str) {
        for (String string : str) {
            if (notEmpty(string))
                return false;
        }
        return true;
    }

    /**
     * 检测字符串数组是否有不为空的
     *
     * @param str 字符串数组
     * @return 有不为空的数组返回true，都为空返回false
     */
    public static boolean notEmptyOr(String... str) {
        for (String string : str) {
            if (notEmpty(string))//有一个不为空，返回true
                return true;
        }
        return false;//都为空，返回false
    }

    /**
     * 检测字符串数组是否全部不为空
     *
     * @param str
     * @return 有为空的数组则返回false, 都不为空返回true
     */
    public static boolean notEmptyAnd(String... str) {
        for (String string : str) {
            if (isEmpty(string))//有一个为空，返回flase
                return false;
        }
        return true;//都不为空，返回true
    }

    public static boolean notEmptys(String strs) {
        return false;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern
                    .compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 校验json格式是否正确
     *
     * @param str
     * @return
     */
    public static boolean checkJSONFormat(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches(ParamConst.REG_JSON_LIST);
    }
}
