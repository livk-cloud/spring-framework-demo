package com.livk.square.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * <p>
 * LivkController
 * </p>
 *
 * @author livk
 * @date 2021/12/15
 */
@RestController
@RequiredArgsConstructor
public class LivkController {

    private final DiscoveryClient discoveryClient;

    private final Random random = new Random();

    @GetMapping("/hello/str")
    public ResponseEntity<String> helloStr() {
        return ResponseEntity.ok(provider() + "-->" + dateStr());
    }

//    @GetMapping("/hello/obj")
//

    private String provider() {
        var instances = discoveryClient.getInstances("provider");
        var serviceInstance = instances.get(random.nextInt(instances.size()));
        return String.format("serviceId:[%s], host:[%s],port:[%d]",
                serviceInstance.getServiceId(),
                serviceInstance.getHost(),
                serviceInstance.getPort());
    }

    private String dateStr() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
    }
}
