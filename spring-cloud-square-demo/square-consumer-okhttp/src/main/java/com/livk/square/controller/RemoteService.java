package com.livk.square.controller;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>
 * MemoteService
 * </p>
 *
 * @author livk
 * @date 2021/12/15
 */
@RestController
@RequiredArgsConstructor
public class RemoteService {

    private final OkHttpClient.Builder builder;

    @GetMapping("/remote/str")
    public ResponseEntity<String> remoteStr() throws IOException {
        var request = new Request.Builder().url("http://provider/hello/str").build();
        var response = builder.build().newCall(request).execute();
        var body = response.body();
        Assert.notNull(body, "body is null!");
        return ResponseEntity.ok("get remote response : " + body.string());
    }
}
