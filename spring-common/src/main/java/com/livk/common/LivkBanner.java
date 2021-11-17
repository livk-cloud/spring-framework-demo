package com.livk.common;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>
 * LivkBanner
 * </p>
 *
 * @author livk
 * @date 2021/8/30
 */
public class LivkBanner implements Banner {

    private LivkBanner() {
    }

    private static final String[] banner = {
            " ██       ██          ██         ██████   ██                       ██\n"
            + "░██      ░░          ░██        ██░░░░██ ░██                      ░██\n"
            + "░██       ██ ██    ██░██  ██   ██    ░░  ░██  ██████  ██   ██     ░██\n"
            + "░██      ░██░██   ░██░██ ██   ░██        ░██ ██░░░░██░██  ░██  ██████\n"
            + "░██      ░██░░██ ░██ ░████    ░██        ░██░██   ░██░██  ░██ ██░░░██\n"
            + "░██      ░██ ░░████  ░██░██   ░░██    ██ ░██░██   ░██░██  ░██░██  ░██\n"
            + "░████████░██  ░░██   ░██░░██   ░░██████  ███░░██████ ░░██████░░██████\n"
            + "░░░░░░░░ ░░    ░░    ░░  ░░     ░░░░░░  ░░░  ░░░░░░   ░░░░░░  ░░░░░░ \n"};

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        for (var line : banner) {
            out.println(line);
        }
        var version = SpringBootVersion.getVersion();
        Format format = Format.create(out, 70);
        format.accept("Spring Boot Version:" + version);
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.accept("Current time：" + dateFormat.format(new Date()));
        format.accept("Current JDK Version：" + System.getProperty("java.version"));
        format.accept("Operating System：" + System.getProperty("os.name"));
        out.flush();
    }

    public static LivkBanner create() {
        return new LivkBanner();
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Format implements Function<String, String>, Consumer<String> {

        private final int n;

        private final PrintStream out;

        private final char ch;

        @Override
        public String apply(String str) {
            int length = str.length();
            if (length >= n) {
                return str;
            }
            int index = (n - length) >> 1;
            str = StringUtils.leftPad(str, length + index, ch);
            return StringUtils.rightPad(str, n, ch);
        }

        @Override
        public void accept(String s) {
            out.println(this.apply(s));
        }

        public static Format create(PrintStream out, int n) {
            return new Format(n, out,'*');
        }
    }
}
