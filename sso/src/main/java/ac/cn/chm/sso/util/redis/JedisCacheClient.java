package ac.cn.chm.sso.util.redis;

import ac.cn.chm.util.CheckUtil;
import ac.cn.chm.util.FormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @Title: Redis管理中心
 * @Package com.tctx.common.redis
 * @Description: redis 操作数据库配置类
 */
@Component("jedisCacheClient")
public class JedisCacheClient {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisCluster jedis;

    //@Autowired
    //private JedisPool jedis;


    /**
     * 设置key值，同时设置失效时间 秒
     *
     * @param @param key 键
     * @param @param value 值
     * @param @param seconds 失效时间
     * @param index  具体数据库
     * @return void
     * @throws
     * @Title: setVExpire
     */
    public <V> void setVExpire(String key, V value, int seconds, int index) {
        //String json = JSON.toJSONString(value);
        jedis.setex(key, seconds, JSON.toJSONString(value));
    }

    /**
     * (存入redis数据)
     *
     * @param @param key
     * @param @param value
     * @param index  具体数据库
     * @return void
     * @throws
     * @Title: setV
     */
    public <V> void setV(String key, V value, int index) {
        //String json = JSON.toJSONString(value);
        jedis.set(key, JSON.toJSONString(value));

    }

    /**
     * getV(获取redis数据信息)
     *
     * @param @param  key
     * @param index   具体数据库 0:常用数据存储      3：session数据存储
     * @param @return
     * @return V
     * @throws
     * @Title: getV
     */
    @SuppressWarnings("unchecked")
    public <V> V getV(String key, int index) {
        return (V) JSONObject.parse(jedis.get(key));
    }

    /**
     * getVString(返回json字符串)
     *
     * @param @param  key
     * @param @param  index
     * @param @return
     * @return String
     * @throws
     * @Title: getVString
     */
    public String getVStr(String key, int index) {
        String v = jedis.get(key);
        return v.substring(1, v.length() - 1);
    }

    /**
     * 删除redis指定key
     *
     * @param key   要删除的键
     * @param index redis集群位置，默认为0
     */
    public void delKey(String key, int index) {
        jedis.del(key);
    }

    /**
     * Push(存入 数据到队列中)
     *
     * @param @param key
     * @param @param value
     * @return void
     * @throws
     * @Title: Push
     */
    public <V> void Push(String key, V value) {
        String json = JSON.toJSONString(value);
        jedis.lpush(key, json);
    }

    /**
     * Push(存入 数据到队列中)
     *
     * @param key
     * @param value
     * @param dBNum
     * @return void
     * @throws
     * @Title: PushV
     */
    public <V> void PushV(String key, V value, int dBNum) {
        String json = JSON.toJSONString(value);
        jedis.lpush(key, json);
    }

    /**
     * Push(存入 数据到队列中)
     *
     * @param @param key
     * @param @param value
     * @return void
     * @throws
     * @Title: Push
     */
    public <V> void PushEmail(String key, V value) {
        String json = FormatUtil.entity2Json(value).toJSONString();//JsonUtil.entity2Json(value);
        jedis.lpush(key, json);
    }

    /**
     * Pop(从队列中取值)
     *
     * @param @param  key
     * @param @return
     * @return V
     * @throws
     * @Title: Pop
     */
    @SuppressWarnings("unchecked")
    public <V> V Pop(String key) {
        String value = "";
        value = jedis.rpop(key);
        return (V) value;
    }

    /**
     * 判断指定key是否存在
     *
     * @param key
     * @param index
     * @return
     */
    public boolean isExpire(String key, int index) {
        if (jedis.exists(key)) {
            Long ttl = jedis.ttl(key);
            if (ttl > 0) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * 判断是否不存在
     *
     * @param key
     * @param index
     * @return
     */
    public boolean notExpire(String key, int index) {
        return !isExpire(key, index);
    }

}