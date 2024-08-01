package hhplus.ecommoerce.config;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheStatistics;
import org.springframework.data.redis.cache.CacheStatisticsCollector;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {


    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//            .entryTtl(Duration.ofHours(12))
//            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//        return RedisCacheManager.builder(connectionFactory)
//            .cacheDefaults(cacheConfiguration)
//            .transactionAware()
//            .build();
//    }

    @Bean
    public RedisCacheManager loggingRedisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(12))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(cacheConfiguration)
            .transactionAware()
            .cacheWriter((RedisCacheWriter) new LoggingRedisCacheWriter(connectionFactory))
            .build();
    }

    private static class LoggingRedisCacheWriter implements RedisCacheWriter {
        private final RedisCacheWriter delegate;

        public LoggingRedisCacheWriter(RedisConnectionFactory connectionFactory) {
            this.delegate = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        }

        @Override
        public byte[] get(String name, byte[] key) {
            byte[] value = delegate.get(name, key);
            if (value == null) {
                log.info("Cache MISS - name: {}, key: {}", name, new String(key));
            } else {
                log.info("Cache HIT - name: {}, key: {}", name, new String(key));
            }
            return value;
        }

        @Override
        public CompletableFuture<byte[]> retrieve(String name, byte[] key, Duration ttl) {
            return null;
        }

        // 다른 메소드들은 delegate에 위임
        @Override
        public void put(String name, byte[] key, byte[] value, Duration ttl) {
            delegate.put(name, key, value, ttl);
        }

        @Override
        public CompletableFuture<Void> store(String name, byte[] key, byte[] value, Duration ttl) {
            return null;
        }

        @Override
        public byte[] putIfAbsent(String name, byte[] key, byte[] value, Duration ttl) {
            return delegate.putIfAbsent(name, key, value, ttl);
        }

        @Override
        public void remove(String name, byte[] key) {
            delegate.remove(name, key);
        }

        @Override
        public void clean(String name, byte[] pattern) {
            delegate.clean(name, pattern);
        }

        @Override
        public void clearStatistics(String name) {

        }

        @Override
        public RedisCacheWriter withStatisticsCollector(
            CacheStatisticsCollector cacheStatisticsCollector) {
            return null;
        }

        @Override
        public CacheStatistics getCacheStatistics(String cacheName) {
            return null;
        }
    }
}