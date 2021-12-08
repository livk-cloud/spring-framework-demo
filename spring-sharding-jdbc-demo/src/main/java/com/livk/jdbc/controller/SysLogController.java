package com.livk.jdbc.controller;

import com.livk.jdbc.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * SysLogController
 * </p>
 *
 * @author livk
 * @date 2021/11/2
 */
@RestController
@RequestMapping("/sys/log")
@RequiredArgsConstructor
public class SysLogController {

	private final SysLogService sysLogService;

	@GetMapping
	public ResponseEntity<?> list(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM") Date date) {
		return ResponseEntity.ok(sysLogService.list(null));
	}

}
