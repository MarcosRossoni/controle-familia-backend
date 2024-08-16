package com.controller.cache;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@ApplicationScoped
public class RedisConnection {

    @ConfigProperty(name = "REDIS_URL")
    String URL_REDIS_CONNECTION;

    @ConfigProperty(name = "REDIS_PASSWORD")
    String PASSWORD_REDIS_CONNECTION;

    @ConfigProperty(name = "REDIS_PORT")
    Integer PORT_REDIS_CONNECTION;

    @ConfigProperty(name = "REDIS_USER")
    String USERNAME_REDIS_CONNECTION;

    public static JedisPool jedisPool;

    @Startup
    public void initializeConnection() {
        final JedisPoolConfig config = buildJedisPoolConfig();
        if (PASSWORD_REDIS_CONNECTION.equals("123")){
            jedisPool = new JedisPool(config, URL_REDIS_CONNECTION, PORT_REDIS_CONNECTION);
        } else {
            jedisPool = new JedisPool(config, URL_REDIS_CONNECTION, PORT_REDIS_CONNECTION,
                    USERNAME_REDIS_CONNECTION, PASSWORD_REDIS_CONNECTION);
        }
    }

    private JedisPoolConfig buildJedisPoolConfig() {
        final JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(128);
        config.setMaxIdle(128);
        config.setMinIdle(16);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        config.setBlockWhenExhausted(true);
        config.setNumTestsPerEvictionRun(3);
        return config;
    }
}
