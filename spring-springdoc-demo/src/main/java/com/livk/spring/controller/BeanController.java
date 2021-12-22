package com.livk.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>
 * BeanController
 * </p>
 *
 * @author livk
 * @date 2021/11/17
 */
@RestController
@RequestMapping("bean")
public class BeanController {

	@GetMapping("str")
	public String str() {
		return "string";
	}

	@GetMapping("list")
	public List<Integer> list() {
		return List.of(1, 2, 3, 4, 5, 6);
	}

	@GetMapping("map")
	public Map<Object, Object> map() {
		return Map.of("a", 1, "b", 2, "c", 3);
	}

}
