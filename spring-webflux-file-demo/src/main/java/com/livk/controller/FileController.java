package com.livk.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * <p>
 * FileController
 * </p>
 *
 * @author livk
 * @date 2021/11/19
 */
@RestController
public class FileController {

    @PostMapping("/download")
    public Mono<Void> download(ServerHttpResponse response) {
        ClassPathResource classPathResource = new ClassPathResource("templates/device.xlsx");
        Flux<DataBuffer> dataBufferFlux = DataBufferUtils.read(classPathResource, new DefaultDataBufferFactory(), 4096);
        ZeroCopyHttpOutputMessage zeroCopyHttpOutputMessage = (ZeroCopyHttpOutputMessage) response;
        HttpHeaders headers = zeroCopyHttpOutputMessage.getHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="
                                                     + URLEncoder.encode(Objects.requireNonNull(classPathResource.getFilename()), StandardCharsets.UTF_8));
        MediaType mediaType = new MediaType("application", "vnd.ms-excel", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        return zeroCopyHttpOutputMessage.writeWith(dataBufferFlux);
    }
}
