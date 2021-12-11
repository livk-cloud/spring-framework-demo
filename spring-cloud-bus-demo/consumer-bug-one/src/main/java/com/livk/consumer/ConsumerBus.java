package com.livk.consumer;

import com.livk.common.LivkSpring;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * ConsumerBus
 * </p>
 *
 * @author livk
 * @date 2021/11/1
 */
@Slf4j
@SpringBootApplication
public class ConsumerBus {

	public static void main(String[] args) {
		LivkSpring.runServlet(ConsumerBus.class, args);
	}

}
