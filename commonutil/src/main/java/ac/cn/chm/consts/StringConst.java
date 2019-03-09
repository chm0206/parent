package ac.cn.chm.consts;

public class StringConst {
    /**
     * 返回说明key值
     */
    public static String RETURN_MSG_KEY = "msg";

    //用户信息常量
    /**
     * 用户密码
     */
    public static String USER_PASS = "userPass";
    /**
     * 用户ID
     */
    public static String USER_ID = "userID";
    /**
     * 归属ID
     */
    public static String OWNER_ID = "ownerID";
    /**
     * 用户账号
     */
    public static String USER_ACCOUNT = "userAccount";
    /**
     * 用户登录终端类型
     */
    public static String SSO_TERMAINAL = "term";
    /**
     * 电脑终端
     */
    public static String SSO_TERMAINAL_COMPUTER = "C";

    public static String SSO_TERMAINAL_PHONE = "P";

    //redis保存信息
    /**
     * 用户信息常量(accToken+userInfo)
     */
    public static String REDIS_USER_INFO = "userInfo";
    /**
     * accToken,根据当前时间生成随机值
     */
    public static String REDIS_ACC_TOKEN = "accToken";
    /**
     * accToken保存的值，userID及ownerID
     */
    public static String REDIS_ACC_TOKEN_VALUE = "accTokenValue";
    /**
     * 终端键，保存userID+终端码
     */
    public static String REDIS_TREMINAL = "terminal";
    /**
     * 终端值，保存accToken及消息推送地址
     */
    public static String REDIS_TREMINAL_VALUE = "terminalValue";
    /**
     * 刷新token
     */
    public static String REDIS_REFRESH_TOKEN = "refreshToken";

    //暂未想好分类
    /**
     * 跳转到指定链接
     */
    public static String REDIRECT_URL = "redirectUrl";

    /**
     * 用户已删除状态
     */
    public static String STATUS_USER_DEL = "D";

}
