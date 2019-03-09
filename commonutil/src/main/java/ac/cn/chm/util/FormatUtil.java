package ac.cn.chm.util;

import ac.cn.chm.consts.ParamConst;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 格式转换类
 *
 * @author chm
 * @data 2018/7/14 16:06
 */
public class FormatUtil {


    public static void main(String[] args) {
//        String s = "123,1234";
//        List<Long> l = str2List(s, Long.class);W
//        for (Long ll : l) {
//            System.out.println(ll);
//        }
        int i = 10;
        long l = a2b(i, Long.class);
        System.out.println(l);
    }

    /**
     * 将str转换为map格式
     *
     * @param str 字符串数据
     * @return 字符串为空返回null, 否则返回map数据
     */
    public static Map str2Map(String str) {
        if (CheckUtil.isEmpty(str)) {
            return null;
        }
        return json2Map(str2Json(str));
    }

    /**
     * 将str转换为json格式
     *
     * @param str 字符串数据
     * @return 字符串为空返回null, 否则返回json数据
     */
    public static JSONObject str2Json(String str) {
        if (CheckUtil.isEmpty(str)) {
            return null;
        }
        return JSONObject.parseObject(str);
    }


    /**
     * 将字符串转化为List<T>
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> str2List(String str, Class<T> clazz) {
        if (CheckUtil.isEmpty(str)) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        String[] strs = str.split(ParamConst.DIV_KOMMA);
        for (int i = 0; i < strs.length; i++) {
            list.add(str2T(strs[i], clazz));
        }
        return list;
    }

    /**
     * 将字符串转换为List<Long>
     *
     * @param str
     * @return
     */
    public static List<Long> str2LongList(String str) {
        if (CheckUtil.isEmpty(str)) {
            return null;
        }
        return str2List(str, Long.class);
    }

    /**
     * Map to Bean
     *
     * @param map
     * @param clazz
     * @return
     * @author chm
     */
    @Deprecated
    public static <T> T map2Bean(Map map, Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * map转换为json格式
     *
     * @param map map格式数据
     * @return map不为空返回json数据， 否则返回null
     */
    public static JSONObject map2Json(Map map) {
        if (map == null) {
            return null;
        }
        return new JSONObject(map);//JSONObject.parseObject(map);
    }

    /**
     * 将map转换为对象
     *
     * @param map
     * @return
     */
    public static <V> V map2Entity(Map map) {
        if (map == null) {
            return null;
        }
        return json2Entity(map2Json(map));
    }


    /**
     * 将实体对象转换为json对象
     *
     * @param value
     * @return
     */
    public static <V> JSONObject entity2Json(V value) {
        if (value == null) {
            return null;
        }
        JSONObject object = (JSONObject) JSONObject.toJSON(value);
        return object;
    }

    /**
     * 将json对象转换为map格式，并返回map数据
     *
     * @param json 要转换为map格式的json数据
     * @return json数据不为空的话，返回map格式数据，否则返回null;
     */
    public static Map json2Map(JSONObject json) {
        if (json == null) {
            return null;
        }
        return json;
    }

    /**
     * 将json转换为对象
     *
     * @param json
     * @return
     */
    public static <V> V json2Entity(JSONObject json) {
        if (json == null) {
            return null;
        }
        //Object object = json.toJavaObject(json, clazz)
        return str2Entity(json.toJSONString());//(V)JSONObject.parse(json.toJSONString());
    }


    /**
     * 将数组转换为List
     *
     * @param obj
     * @param <T>
     * @return
     */
    //@Deprecated
    public static <T> List<T> array2List(T[] obj) {
        if (CheckUtil.isEmpty(obj)) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < obj.length; i++) {
            list.add(obj[i]);
        }
        return list;
    }


    public static <T> T str2T(String str, Class<T> clazz) {
        //如果是基础类型，就返回基础数据的数据
        //想用枚举，结果搞不定，下次研究下
        switch (clazz.getSimpleName()) {
            case "Byte":
                return (T) Byte.valueOf(str);
            case "Short":
                return (T) Short.valueOf(str);
            case "Integer":
                return (T) Integer.valueOf(str);
            case "Long":
                return (T) Long.valueOf(str);
            case "Float":
                return (T) Float.valueOf(str);
            case "Double":
                return (T) Double.valueOf(str);
//            case "Character":
//                return (T) Character.valueOf(str);
            case "Boolean":
                return (T) Boolean.valueOf(str);
            default:
                return (T) str;
        }
    }

    /**
     * 将a类型的数据转化为b类型的数据
     *
     * @param a
     * @param clazz
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> B a2b(A a, Class<B> clazz) {
        return str2T(String.valueOf(a), clazz);
    }


    /**
     * 将字符串转换为对象
     *
     * @param str
     * @return
     */
    public static <V> V str2Entity(String str) {
        if (CheckUtil.isEmpty(str)) {
            return null;
        }
        return (V) JSONObject.parse(str);
    }

    /**
     * 按照参数format的格式，日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    @Deprecated
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串(可以使用dateUtil工具类)
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    @Deprecated
    public static String date2Str(Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }
}
