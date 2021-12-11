package com.livk.client;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * AuthorizationClient
 * </p>
 *
 * @author livk
 * @date 2021/10/27
 */
@SpringBootApplication
public class AuthorizationClient {

	public static void main(String[] args) {
		LivkSpring.runServlet(AuthorizationClient.class, args);
	}

}
