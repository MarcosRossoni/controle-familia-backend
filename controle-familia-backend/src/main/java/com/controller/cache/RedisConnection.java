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

    public static JedisPool jedisPool;

    @Startup
    public void initializeConnection() {
        final JedisPoolConfig config = buildJedisPoolConfig();
        jedisPool = new JedisPool(config, URL_REDIS_CONNECTION, 6379);
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
