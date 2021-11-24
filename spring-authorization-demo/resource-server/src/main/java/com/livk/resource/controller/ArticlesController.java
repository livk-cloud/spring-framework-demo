package com.livk.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ArticlesController
 * </p>
 *
 * @author livk
 * @date 2021/10/27
 */
@RestController
public class ArticlesController {

	@GetMapping("/articles")
	public String[] getArticles() {
		return new String[] { "Article 1", "Article 2", "Article 3" };
	}

}
