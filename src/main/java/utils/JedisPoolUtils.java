package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * JedisPool工具类
 * 加载配置文件，配置连接池的参数
 * 提供获取连接和方法
 */
public class JedisPoolUtils {

    private static JedisPool pool;

    static {
        final Properties properties = new Properties();
        final InputStream resourceAsStream =
                JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // JedisPoolConfig对象
        final JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        pool = new JedisPool(config, properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));
    }

    // 获取Jedis
    public static Jedis getResource(){
        return pool.getResource();
    }

    public static void close(Jedis jedis){
        jedis.close();
    }
}
