package com.livk.r2dbc.controller;

import com.livk.r2dbc.domain.User;
import com.livk.r2dbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * <p>
 * UserController
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public HttpEntity<Flux<User>> users() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Mono<User>> user(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PostMapping
    public HttpEntity<Mono<Void>> save(@RequestBody Mono<User> userMono) {
        return ResponseEntity.ok(userMono.flatMap(user -> userRepository.save(user)
                .flatMap(u -> Objects.nonNull(u.id()) ? Mono.empty() : Mono.defer(
                        () -> Mono.error(new RuntimeException())))));
    }

    @PutMapping("/{id}")
    public HttpEntity<Mono<Void>> update(@PathVariable("id") Long id,
                                         @RequestBody Mono<User> userMono) {
        return ResponseEntity.ok(userMono.flatMap(monoUser -> userRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Id Not Found!")))
                .flatMap(user -> userRepository.save(new User(user.id(), monoUser.username(), monoUser.password())).then())));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Mono<Void>> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userRepository.deleteById(id));
    }
}
