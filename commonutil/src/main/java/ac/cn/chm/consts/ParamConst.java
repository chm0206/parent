package ac.cn.chm.consts;

public class ParamConst {
	/*** 每页默认记录数 20 */
	public static final String PAGE_SIZE ="20";
	/*** TOKEN_KEY */
	public static final String TOKEN_KEY = "FJ" ;
	/**
	 * TOKEN_KEY_LEN
	 * 长度 = 40 - n （16 = 40 - 24）
	 */
	public static final int TOKEN_KEY_LEN = 24 ;
	/*** 加密方式 SHA-1 */
	public static final String SHA = "SHA-1";
	

	/***param userID 字段****/
	public static final String FJ_USER_ID = "userID" ;
	
	public static final String FJ_ACC_TOKEN = "accToken";
	/**
	 * 包含子菜单
	 */
	public static final Boolean MENU_IS_CHILD = true;
	/**
	 * 不包含子菜单
	 */
	public static final Boolean MENU_NOT_CHILD = false;
	
	public static final Integer GET_USERINFO = 1;
	public static final String GET_INFO = "getInfo";
	/**
	 * 根菜单
	 */
	public static final String MENU_ROOT_ID = "0";
	/**
	 * 菜单正常状态
	 */
	public static final String MENU_STATUS_NORMAL = "0";
	/**
	 * 菜单锁定状态
	 */
	public static final String MENU_STATUS_LOCKING="L";
	//符号匹配

	/**参数间分隔符(尽量别改)*/
	public static final String DIV_PARAM_REGEX = "\\|";
	/**参数内分隔符(尽量别改)*/
	public static final String DIV_VALUE_REGEX = "\\#";
	/*** 分割符","*/
	public static final String DIV_KOMMA = ",";
	/*** 左括号*/
	public static final String DIV_LEFT_PARENTHESIS = "[";
	/*** 右括号*/
	public static final String DIV_RIGHT_PARENTHESIS = "]";
	/*** 点 */
	public static final String DIV_DOT = "\\.";
	//正则
	/*** 匹配任意一个字符*/
	public static final String REG_ALL = ".";
	/*** 匹配任意一个数字 */
	public static final String REG_NUMBER = "\\d";
	/*** 匹配任意一个非数字 */
	public static final String REG_NOT_NUMBER = "\\D";
	/*** 匹配任意一个单词符号<a-zA-Z0-9_> */
	public static final String REG_WORD = "\\w";
	/*** 匹配任意一个非单词 */
	public static final String REG_NOT_WORD = "\\W";
	/*** 匹配任意一个空格<\t\n\r\n> */
	public static final String REG_SPACE = "\\s";
	/*** 匹配任意一个非空格 */
	public static final String REG_NOT_SPACE = "\\S";
	
	public static final String REG_NUM_LETTER ="[A-Z0-9a-z]";
	//正则表达式-次数
	/**
	 * 正则知识点
	 * [] 匹配括号内任一字符
	 * () 匹配括号内的表达式,组的概念
	 */
	/*** 一次或没有 */
	public static final String REG_TIME_ZREO = "\\?";
	/*** 一次或多次 */
	public static final String REG_TIME_ONE = "\\+";
	/*** 任意次数 */
	public static final String REG_TIME_ALL = "\\*";
	/*** 条件或 *///正则没有and准备，即，一个字符不可能既是数字，又是字母
	public static final String REG_OR = "|";
	
	/*** 中国移动号码前三位*/
	public static final String REG_PHONE_CMCC="134|135|136|137|138|139|150|151|152|157|158|159|182|183|184|188|187|147|178";
	/*** 中国联通号码前三位*/
	public static final String REG_PHONE_CUCC="130|131|132|155|156|166|186|185|176";
	/*** 中国电脑号码前三位 */
	public static final String REG_PHONE_CTCC = "133|153|180|181|189|177";
	
	/*** 手机号码校验表达式 */
	public static final String REG_PHONE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9])|166)"+REG_NUMBER+"{8}$";
	/*** 手机，电话，固话校验表达式 */
	public static final String REG_ALL_PHONE = "";
	/*** 邮箱校验表达式 */
	public static final String REG_EMAIL ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	/*** 正则表达式：验证汉字*/
	public static final String REG_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
	/*** 正则表达式：验证身份证 */
    public static final String REG_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    /*** 正则表达式：验证URL */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    /*** 正则表达式：验证IP地址*/
    public static final String REG_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    /*** 正则表达式：验证密码(匹配数字字母_.) */
    public static final String REG_PASSWORD = "^[\\w\\.]{6,16}$";

    public static final String REG_CONDITION = "[\\&\\&]|[\\|\\|]]";//"[&&]|[\\|\\|]]";

    public static void main(String[] args){
    	String s = "userId&&userAccount||userPass";
		String [] ss = s.split(REG_CONDITION);
		System.out.println(ss.length);

		//String phone = "1";
		//System.out.println(phone.matches(DIV_DOT));
//		String s = "1234|abc#1234|abc";
//		String[] s1 = s.split(DIV_PARAM_REGEX);
//		String[] s2 = s.split(DIV_VALUE_REGEX);
//		System.out.println(s1);

	}
    /**
     * 获取获取json数据的链式结构是否正确
     */
    public static final String REG_JSON_LIST = "^([a-zA-Z]\\w*(\\[\\d+\\])?\\.)+([a-zA-Z]\\w*(\\[\\d+\\])?)$";
	
    //redis-过期时间(比包装类Integer好)
    /*** 设置过期时间为1个小时*/
	public static int EXPIRE_1_HOURS = 1*60*60;
	/*** 设置过期时间为30分钟*/
	public static int EXPIRE_30_MINUTE = 30*60;
	/*** 过期时间为1天*/
	public static int EXPIRE_1_DAY =1*24*60*60;
	/*** 设置过期时间为1周*/
	public static int EXPIRE_1_WEEK = 1*7*24*60*60;
	
}
