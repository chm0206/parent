package ac.cn.chm.other;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 说明：日期处理
 * 创建人：riseinfo.cn
 * 修改时间：2015年11月24日
 * @version
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfMonth  = new SimpleDateFormat("MM");
	private final static SimpleDateFormat sdfDay  = new SimpleDateFormat("dd");
	private final static SimpleDateFormat sdfYMD  = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfYMD1 = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfYMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	/**
	 * 获取MM格式
	 * @return
	 */
	public static String getMonth() {
		return sdfMonth.format(new Date());
	}
	/**
	 * 获取dd格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	/**
	 * 获取yyyy-MM-dd格式
	 * @return
	 */
	public static String getYMD(){
		return sdfYMD.format(new Date());
	}
	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getYMD1(){
		return sdfYMD1.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	/**
	 * 获取YYYY-MM-DD HH:mm:ss.SSS格式
	 * @return
	 */
	public static String getNow(){
		return sdfNow.format(new Date());
	}
	
	/**
	 * 获取yyyy-MM-dd HH:mm格式
	 * @return
	 */
	public static String getYMDHM() {
		return sdfYMDHM.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author fh
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}
	
	public static String getTimeStamp(){
		return String.valueOf(System.currentTimeMillis());//不使用+“”,因为会添加入常量池，而时间戳不需要使用多次
		//date.getTime();
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	 
	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            //day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            day=(beginDate.getTime()-endDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 统计两个时间之间相隔天数
     * @param beginDateStr
     * @param endDateStr
     * @param formatStr
     * @return
     */
    public static long getDaySub(String beginDateStr,String endDateStr,String formatStr){
        long day=0;
        if(formatStr == null || "".equals(formatStr)){
        	formatStr = "yyyy-MM-dd HH:mm:ss" ;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date beginDate = null;
        Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 获取当前时间前几月/天的日期
     * @param days
     * @param format
     * @return
     */
    public static String getBeferDayDate(String days,String format) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        if("m".equals(format)){
        	canlendar.add(Calendar.MONTH, -daysInt);    //得到前daysInt个月 日期
        }else{
        	canlendar.add(Calendar.DATE, -daysInt); // 日期减 如果不够减会将月变动
        }
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    
    public static void main(String[] args) {
//    	System.out.println(getDays());
//    	System.out.println(getBeferDayDate("3",ParamConst.TARGET_MONTH));
    }
    
    /**
     * 计算两个时间之间相差秒数
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long secDiff(String startTime, String endTime){
    	long totalSec = 0 ;
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	
    	try {
			totalSec = sd.parse(endTime).getTime() - sd.parse(startTime).getTime(); //毫秒
			totalSec = totalSec / 1000 ; //秒
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    	
    	return totalSec ;
    }

}
