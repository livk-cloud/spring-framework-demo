package com.livk.redis.controller;

import com.livk.common.redis.domain.LivkMessage;
import com.livk.common.redis.supprot.LivkRedisTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * <p>
 * MessageController
 * </p>
 *
 * @author livk
 * @date 2021/11/26
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final LivkRedisTemplate livkRedisTemplate;

    @PostMapping("/redis/{id}")
    public Mono<Void> send(@PathVariable("id") Long id,
                           @RequestParam("msg") String msg,
                           @RequestBody Map<String, Object> data) {
        livkRedisTemplate.convertAndSend(LivkMessage.CHANNEL, new LivkMessage().setId(id).setMsg(msg).setData(data));
        return Mono.empty();
    }
}
