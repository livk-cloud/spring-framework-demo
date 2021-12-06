package com.livk.provider.biz.controller;

import com.livk.provider.api.domain.Users;
import com.livk.provider.biz.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * UsersController
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final UserMapper userMapper;

    @GetMapping
    public List<Users> users() {
        return userMapper.selectList(null);
    }

    @PostMapping
    public Boolean save(@RequestBody Users users) {
        return userMapper.insert(users) != 0;
    }
}
