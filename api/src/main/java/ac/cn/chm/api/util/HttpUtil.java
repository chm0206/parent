package ac.cn.chm.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.StatusLine;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *@author riseinfo.cn
 *@date 2014-7-21
 *
 */
public class HttpUtil {  
    
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
//
//    /**
//     * get请求
//     * @return
//     */
//    public static String doGet(String url) {
//        try {
//           // HttpClient client = new DefaultHttpClient();
//            HttpClient httpclient = HttpClients.createDefault();
//
//            //发送get请求
//            HttpGet request = new HttpGet(url);
//            HttpResponse response = httpclient.execute(request);
//
//            /**请求发送成功，并得到响应**/
//            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                /**读取服务器返回过来的json字符串数据**/
//                String strResult = EntityUtils.toString(response.getEntity());
//
//                return strResult;
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * post请求(用于key-value格式的参数)
//     * @param url
//     * @param params
//     * @return
//     */
//    public static String doPost(String url, Map params){
//
//        BufferedReader in = null;
//        try {
//            // 定义HttpClient
//            HttpClient client = HttpClients.createDefault();
//            // 实例化HTTP方法
//            HttpPost request = new HttpPost();
//            request.setURI(new URI(url));
//
//            //设置参数
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
//                String name = (String) iter.next();
//                String value = String.valueOf(params.get(name));
//                nvps.add(new BasicNameValuePair(name, value));
//
//                //System.out.println(name +"-"+value);
//            }
//            request.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
//
//            HttpResponse response = client.execute(request);
//            int code = response.getStatusLine().getStatusCode();
//            if(code == 200){    //请求成功
//                in = new BufferedReader(new InputStreamReader(response.getEntity()
//                        .getContent(),"UTF-8"));
//                StringBuffer sb = new StringBuffer("");
//                String line = "";
//                String NL = System.getProperty("line.separator");
//                while ((line = in.readLine()) != null) {
//                    sb.append(line + NL);
//                }
//
//                in.close();
//
//                return sb.toString();
//            }
//            else{   //
//                System.out.println("状态码：" + code);
//                return null;
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//
//            return null;
//        }
//    }
//
//    /**
//     * post请求（用于请求json格式的参数）
//     * @param url
//     * @param params
//     * @return
//     */
//    public static String doPost(String url, String params) throws Exception {
//
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url);// 创建httpPost
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-Type", "application/json");
//        String charSet = "UTF-8";
//        StringEntity entity = new StringEntity(params, charSet);
//        httpPost.setEntity(entity);
//        CloseableHttpResponse response = null;
//
//        try {
//
//            response = httpclient.execute(httpPost);
//            StatusLine status = response.getStatusLine();
//            int state = status.getStatusCode();
//            if (state == HttpStatus.SC_OK) {
//                HttpEntity responseEntity = response.getEntity();
//                String jsonString = EntityUtils.toString(responseEntity);
//                return jsonString;
//            }
//            else{
//                 logger.error("请求返回:"+state+"("+url+")");
//            }
//        }
//        finally {
//            if (response != null) {
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//
//    public static String doPost(String url) throws Exception {
//
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        url = url.replace("{", "%7B") ;
//        url = url.replace("}", "%7D") ;
//        url = url.replace("\"", "%22") ;
//        HttpPost httpPost = new HttpPost(url);// 创建httpPost
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-Type", "application/json");
//       // String charSet = "UTF-8";
//        //StringEntity entity = new StringEntity(params, charSet);
//      //  httpPost.setEntity(entity);
//        CloseableHttpResponse response = null;
//
//        try {
//
//            response = httpclient.execute(httpPost);
//            StatusLine status = response.getStatusLine();
//            int state = status.getStatusCode();
//            if (state == HttpStatus.SC_OK) {
//                HttpEntity responseEntity = response.getEntity();
//                String jsonString = EntityUtils.toString(responseEntity);
//                return jsonString;
//            }
//            else{
//                 logger.error("请求返回:"+state+"("+url+")");
//            }
//        }
//        finally {
//            if (response != null) {
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    public String getTaskCreateJsonReq(JSONObject json){
//
//        StringBuffer req = new StringBuffer();
//        try {
//            String encode = URLEncoder.encode(json.toString(), "UTF-8");
//            req.append(encode);
//        } catch (UnsupportedEncodingException e){
//            req.append("");
//            logger.error("TaskReqUtils.getTaskCreateReq [JSONObject]: " + json.toString());
//        }
//
//        logger.info("TaskReqUtils.getTaskCreateJsonReq [JSONObject]: " + json.toString());
//
//        return req.toString();
//}
//
//    public static String getTaskCreateJsonReq(String json){
//    	 String encode = "" ;
//       // StringBuffer req = new StringBuffer();
//        try {
//            encode = URLEncoder.encode(json, "UTF-8");
//           // req.append(encode);
//        } catch (UnsupportedEncodingException e){
//           // req.append("");
//            logger.error("TaskReqUtils.getTaskCreateReq [JSONObject]: " + json.toString());
//        }
//
//        logger.info("TaskReqUtils.getTaskCreateJsonReq [JSONObject]: " + json.toString());
//
//        return encode;
//}
//
//    public static void main(String[] args){
//    	String url1 = "http://192.168.1.227:8085/riseplus/ucallBack?status=%7BstatusCode:'1011'%7D&result=%7Bmethod:'authUserLogin',accToken:'AE257C210D2EEA50',refreshToken:'70BE8BFBAB814605A4E4A81C0398B560',userID:'33'%7D" ;
//    	String urlstr1 = "http://192.168.1.227:8085/riseplus/ucallBack?status={statusCode:'1011'}&result={method:'authUserLogin',accToken:'AE257C210D2EEA50',refreshToken:'70BE8BFBAB814605A4E4A81C0398B560',userID:'33'}" ;
//    	String url = "http://www.baidu.com" ;
//    	String url2 = "http://192.168.1.227:8085/riseplus/ucallBack?" ;
//    	String parm = "status={statusCode:'1011'}&result={method:'authUserLogin',accToken:'AE257C210D2EEA50',refreshToken:'70BE8BFBAB814605A4E4A81C0398B560',userID:'33'}" ;
//    	String urlxx = "http://192.168.1.227:8080/UserCenter/ucapi?param=%7BisMenu:'0',callBackUrl:'',userAcc:'123',userPwd:'123456',userCode:'1111'%7D&public=%7Bmethod:'accLogin',aid:'100101',sysToken:'1BD35D95C0806C89',format:'json'%7D" ;
//
//    	String x = "{}" ;
//    	String p = getTaskCreateJsonReq(parm) ;
//    	System.out.println(getTaskCreateJsonReq(x));
//    	System.out.println(getTaskCreateJsonReq(url1));
//    	//String result = HttpUtil.doGet(url2 + p) ;
//    	String urlx = url2 + p ;
//
//    	try {
//    		System.out.println("rsssss111");
//			String rs = HttpUtil.doPost(urlxx) ;
//			System.out.println("rsssss");
//			System.out.println("rsssss" + rs);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	//System.exit(0) ;
//    	//System.out.println(result);
//
//    }
}  