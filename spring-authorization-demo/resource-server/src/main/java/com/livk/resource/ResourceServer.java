package com.livk.resource;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * ResourceServer
 * </p>
 *
 * @author livk
 * @date 2021/10/27
 */
@SpringBootApplication
public class ResourceServer {

	public static void main(String[] args) {
		LivkSpring.run(ResourceServer.class, args);
	}

}
