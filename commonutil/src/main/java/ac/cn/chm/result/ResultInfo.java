package ac.cn.chm.result;

import java.util.HashMap;
import java.util.Map;

import ac.cn.chm.consts.CodeConst;
import ac.cn.chm.consts.StringConst;
import ac.cn.chm.base.PageData;
import ac.cn.chm.util.FormatUtil;
import com.alibaba.fastjson.JSONObject;

public class ResultInfo {

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private JSONObject data;

    /**
     * 获取状态码
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置状态码
     *
     * @param code 状态码的值
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 操作失败
     */
    public void error(){
        //this.code = CodeConst.CODE_FAIL;
        error(CodeConst.CODE_FAIL);
    }

    /**
     * 异常处理
     * @param code
     */
    public void error(Integer code){
        this.code = code;
        //error(CodeConst.CODE_FAIL,"");
    }
    /**
     * 出错并返回指定信息
     * @param msg
     */
    public void error (String msg){
        error(CodeConst.CODE_FAIL,msg);
    }
    /**
     * 操作失败，返回指定的错误及错误提示
     * @param code
     * @param msg
     */
    public void error(Integer code,String msg){
        this.code = code;
        this.put(msg);
    }

    /**
     * 获取返回数据
     *
     * @return 返回data
     */
    public JSONObject getData() {
        return data;
    }

    public static void main(String[] args) {
        Integer code = 400;
        // JSONObject data = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        data.put("userID", "chm");
        ResultInfo ri = new ResultInfo(code, data);
        System.out.println(ri);
    }

    /**
     * 生成默认状态码
     */
    public ResultInfo() {
        this(CodeConst.CODE_NORMAL);
    }

    /**
     * 添加状态码
     *
     * @param code
     */
    public ResultInfo(Integer code) {
        this(code, new JSONObject());
    }

    /**
     * 添加为Map格式的返回数据及默认状态码
     *
     * @param map map格式的返回数据
     */
    public ResultInfo(Map<String, Object> map) {
        this(CodeConst.CODE_NORMAL, map);
    }

    /**
     * 添加要返回的数据及默认状态码
     *
     * @param object 要返回的数据
     */
    public ResultInfo(JSONObject object) {
        this(CodeConst.CODE_NORMAL, object);
    }

    /**
     * 添加为Map格式的返回数据及状态码
     *
     * @param code 状态码
     * @param map  map格式的返回数据
     */
    public ResultInfo(Integer code, Map<String, Object> map) {
        this(code, FormatUtil.map2Json(map));// 有公共方法就要调用公共方法，就不需要改太多
    }

    /**
     * 添加状态码及返回数据
     *
     * @param code   状态码
     * @param object 要返回数据
     */
    public ResultInfo(Integer code, JSONObject object) {
        this.code = code;
        this.data = object;
    }

    /**
     * 添加状态说明及默认状态码
     *
     * @param msg 状态说明
     */
    public ResultInfo(String msg) {
        this(CodeConst.CODE_NORMAL, msg);
    }

    /**
     * 添加状态码及状态说明
     *
     * @param code 状态码
     * @param msg  状态说明
     */
    public ResultInfo(Integer code, String msg) {
        this(code);
        JSONObject object = new JSONObject();
        object.put(StringConst.RETURN_MSG_KEY, msg);
        this.data = object;
    }

    /**
     * 添加/重置返回数据
     *
     * @param data 返回数据
     */
    public void setData(JSONObject data) {
        this.data = data;
    }

    /**
     * 添加/重置返回数据
     *
     * @param map 返回数据
     */
    public void setData(Map<String, Object> map) {
        this.data = FormatUtil.map2Json(map);// JSONObject.fromObject(map);
    }

    /**
     * 追加返回数据
     *
     * @param map 要追加的数据
     */
    public void putAll(Map<String, Object> map) {
        this.data.putAll(map);
    }

    /**
     * 追加返回数据
     *
     * @param pd 要追加的数据
     */
    @SuppressWarnings("unchecked")
    public void putAll(PageData pd) {
        this.data.putAll(pd);
    }

    /**
     * 追加返回数据
     *
     * @param object 要追加的数据
     */
    public void putAll(JSONObject object) {
        this.data.putAll(object);
    }

    /**
     * 添加任意类型的返回数据
     *
     * @param key   返回数据Key值
     * @param value 返回数据
     */
    public void put(String key, Object value) {
        this.data.put(key, value);
    }

    /**
     * 添加字符串到返回数据
     *
     * @param str 返回状态说明
     */
    public void put(String str) {
        this.data.put(StringConst.RETURN_MSG_KEY, str);
    }

    @Override
    public String toString() {
        return "ResultInfo [code=" + code + ", data=" + data + "]";
    }

}
