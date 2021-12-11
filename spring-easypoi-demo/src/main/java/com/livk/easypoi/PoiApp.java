package com.livk.easypoi;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * PoiApp
 * </p>
 *
 * @author livk
 * @date 2021/10/19
 */
@SpringBootApplication
public class PoiApp {

	public static void main(String[] args) {
		LivkSpring.runServlet(PoiApp.class, args);
	}

}
