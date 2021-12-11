package com.livk.jdbc;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * ShardingApp
 * </p>
 *
 * @author livk
 * @date 2021/11/2
 */
@SpringBootApplication
public class ShardingApp {

	public static void main(String[] args) {
		LivkSpring.runServlet(ShardingApp.class, args);
	}

}
