package com.livk.redis.config;

import com.livk.common.redis.domain.LivkMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * <p>
 * RedisConfig
 * </p>
 *
 * @author livk
 * @date 2021/11/26
 */
@Configuration
@ConditionalOnClass(RedisOperations.class)
public class RedisConfig {

    @Bean
    public MessageListener messageListener() {
        return new ConsumerRedisListener();
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        var container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListener(), new PatternTopic(LivkMessage.CHANNEL));
        return container;
    }
}
