package com.livk.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livk.common.redis.domain.LivkMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * ConsumerRedisListener
 * </p>
 *
 * @author livk
 * @date 2021/11/26
 */
@Slf4j
public class ConsumerRedisListener implements MessageListener {

    private final Jackson2JsonRedisSerializer<LivkMessage> serializer = new Jackson2JsonRedisSerializer<>(LivkMessage.class);

    public ConsumerRedisListener() {
        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        this.serializer.setObjectMapper(mapper);
    }

    /**
     * @param message 传递过来的信息数据
     * @param pattern 频道
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("topic:{}", new String(pattern, StandardCharsets.UTF_8));
        var value = serializer.deserialize(message.getBody());
        log.info("consumer value:{}", value);
    }
}
