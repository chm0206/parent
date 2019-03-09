package ac.cn.chm.sso.util;
//
//import ac.cn.chm.util.FormatUtil;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
//import ac.cn.chm.fj.util.CheckUtil;
//import ac.cn.chm.fj.util.FormatUtil;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;

/**
 * @Title: Redis管理中心
 * @Package com.tctx.common.redis
 * @Description: redis 操作数据库配置类
 */
//@Component("jedisCacheClient")
public class JedisCacheClient {

//    private Logger log = Logger.getLogger(JedisCacheClient.class);
//
//    @Autowired
//    private JedisPool jedisPool;
//
//
//    /**
//     *
//      * 设置key值，同时设置失效时间 秒
//      * @Title: setVExpire
//      * @param @param key 键
//      * @param @param value 值
//      * @param @param seconds 失效时间
//      * @param index 具体数据库
//      * @return void
//      * @throws
//     */
//    public <V> void setVExpire(String key, V value,int seconds,int index) {
//        String json = JSON.toJSONString(value);
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.select(index);
////            jedis.set(WebConfigConstant.PROJECT+key, json);
////            jedis.expire(WebConfigConstant.PROJECT+key, seconds);
//            if(jedis.exists(key)){
//            	jedis.set(key, json);
//            }else{
//            	jedis.getSet(key, json);
//            }
//            jedis.expire(key, seconds);
//        } catch (Exception e) {
//            log.error("setV初始化jedis异常：" + e);
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);//过期方法
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//
//    }
//    /**
//     *
//      * (存入redis数据)
//      * @Title: setV
//      * @param @param key
//      * @param @param value
//      * @param index 具体数据库
//      * @return void
//      * @throws
//     */
//    public <V> void setV(String key, V value,int index) {
//        String json = JSON.toJSONString(value);
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.select(index);
////            jedis.set(WebConfigConstant.PROJECT+key, json);
//            if(jedis.exists(key)){
//            	jedis.set(key, json);
//            }else{
//            	jedis.getSet(key, json);
//            }
//        } catch (Exception e) {
//            log.error("setV初始化jedis异常：" + e);
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//
//    }
//
//    /**
//     *
//      * getV(获取redis数据信息)
//      * @Title: getV
//      * @param @param key
//      * @param index 具体数据库 0:常用数据存储      3：session数据存储
//      * @param @return
//      * @return V
//      * @throws
//     */
//    @SuppressWarnings("unchecked")
//    public <V> V getV(String key,int index) {
//        String value = "";
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.select(index);
//            value = jedis.get(key);
//        } catch (Exception e) {
//            log.error("getV初始化jedis异常：" + e);
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//        return (V)JSONObject.parse(value);
//    }
//    /**
//     *
//      * getVString(返回json字符串)
//      * @Title: getVString
//      * @param @param key
//      * @param @param index
//      * @param @return
//      * @return String
//      * @throws
//     */
//    public String getVStr(String key,int index) {
//        String value = "";
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.select(index);
//            value = jedis.get(key);
//            value = value.substring(1, value.length()-1);
//        } catch (Exception e) {
//            log.error("getVString初始化jedis异常：" + e);
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//        return value;
//    }
//    /**
//     * 删除redis指定key
//     * @param key 要删除的键
//     * @param index redis集群位置，默认为0
//     */
//    public void delKey(String key,int index){
//        Jedis jedis = null;
//    	if(CheckUtil.isEmpty(key)){
//    		return;
//    	}
//    	try{
//    		jedis = jedisPool.getResource();
//            jedis.select(index);//获取指定库
//            jedis.del(key);
//    	} catch (Exception e) {
//            log.error("getVString初始化jedis异常：" + e);
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//    }
//
//    /**
//     *
//     * Push(存入 数据到队列中)
//     *
//     * @Title: Push
//     * @param @param key
//     * @param @param value
//     * @return void
//     * @throws
//     */
//    public <V> void Push(String key, V value) {
//        String json = JSON.toJSONString(value);
//        Jedis jedis = null;
//        try {
//            log.info("存入 数据到队列中");
//            jedis = jedisPool.getResource();
//            jedis.select(15);
//            jedis.lpush(key, json);
//        } catch (Exception e) {
//            log.error("Push初始化jedis异常：" + e);
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//    }
//    /**
//     *
//     * Push(存入 数据到队列中)
//     *
//     * @Title: PushV
//     * @param  key
//     * @param value
//     * @param dBNum
//     * @return void
//     * @throws
//     */
//    public <V> void PushV(String key, V value,int dBNum) {
//        String json = JSON.toJSONString(value);
//        Jedis jedis = null;
//        try {
//            log.info("存入 数据到队列中");
//            jedis = jedisPool.getResource();
//            jedis.select(dBNum);
//            jedis.lpush(key, json);
//        } catch (Exception e) {
//            log.error("Push初始化jedis异常：" + e);
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//    }
//
//    /**
//     *
//     * Push(存入 数据到队列中)
//     *
//     * @Title: Push
//     * @param @param key
//     * @param @param value
//     * @return void
//     * @throws
//     */
//    public <V> void PushEmail(String key, V value) {
//        String json = FormatUtil.entity2Json(value).toJSONString();//JsonUtil.entity2Json(value);
//        Jedis jedis = null;
//        try {
//            log.info("存入 数据到队列中");
//            jedis = jedisPool.getResource();
//            jedis.select(15);
//            jedis.lpush(key, json);
//        } catch (Exception e) {
//            log.error("Push初始化jedis异常：" + e);
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//    }
//
//    /**
//     *
//     * Pop(从队列中取值)
//     *
//     * @Title: Pop
//     * @param @param key
//     * @param @return
//     * @return V
//     * @throws
//     */
//    @SuppressWarnings("unchecked")
//    public <V> V Pop(String key) {
//        String value = "";
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.select(15);
//            value = jedis.rpop(key);
//        } catch (Exception e) {
//            log.error("Pop初始化jedis异常：" + e);
//            if (jedis != null) {
//                //jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//        return (V) value;
//    }
//
//
//    /**
//     *
//     * expireKey(限时存入redis服务器)
//     *
//     * @Title: expireKey
//     * @param @param key
//     * @param @param seconds
//     * @return void
//     * @throws
//     */
//    public void expireKey(String key, int seconds) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.select(3);
//            jedis.expire(key, seconds);
//        } catch (Exception e) {
//            log.error("Pop初始化jedis异常：" + e);
//            if (jedis != null) {
//                //jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } finally {
//            closeJedis(jedis);
//        }
//
//    }
//
//    /**
//     *
//     * closeJedis(释放redis资源)
//     *
//     * @Title: closeJedis
//     * @param @param jedis
//     * @return void
//     * @throws
//     */
//    public void closeJedis(Jedis jedis) {
//        try {
//            if (jedis != null) {
////                jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//        } catch (Exception e) {
//            log.error("释放资源异常：" + e);
//        }
//    }
//
//    public void setJedisPool(JedisPool jedisPool) {
//        this.jedisPool = jedisPool;
//    }
//    public boolean isExpire(String key,int index){
//    	Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.select(index);
//            if(jedis.exists(key)){
//            	Long ttl = jedis.ttl(key);
//            	if(ttl>0){
//            		return true;
//            	}
//            }else{
//            	return false;
//            }
//        } catch (Exception e) {
//            log.error("Pop初始化jedis异常：" + e);
//            if (jedis != null) {
//                //jedisPool.returnBrokenResource(jedis);
//            	jedis.close();
//            }
//            return false;
//        } finally {
//            closeJedis(jedis);
//        }
//        return false;
//    }
//    /**
//     * 判断是否不存在
//     * @param key
//     * @param index
//     * @return
//     */
//    public boolean notExpire(String key,int index){
//    	return !isExpire(key, index);
//    }

}