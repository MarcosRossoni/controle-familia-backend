package com.controller.cache;

import com.endpoint.provider.ObjectMapperProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.Auth;
import jakarta.inject.Singleton;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Singleton
public class SessionCache {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapperProvider().getContext();

    public static void gravarSessao(Auth auth) {

        try (Jedis jedis = RedisConnection.jedisPool.getResource()) {

            jedis.setex(auth.getAccessToken(),
                    ChronoUnit.SECONDS.between(
                            LocalDateTime.now(),
                            auth.getDtExpiracao()),
                    OBJECT_MAPPER.writeValueAsString(auth));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Auth carregarSessao(String accessToken) {
        try (Jedis jedis = RedisConnection.jedisPool.getResource()) {
            final String content = jedis.get(accessToken);
            return content == null ? null : OBJECT_MAPPER.readValue(content, Auth.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removerSessao(String accessToken) {
        try (Jedis jedis = RedisConnection.jedisPool.getResource()){
            jedis.del(accessToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
