package com.company.aiassess.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具：编号类（ER+yyyyMMdd+seq / Q+yyyyMMdd+seq / RF+yyyyMMddHHmmss）的格式化基准。
 */
public final class DateUtils {

    public static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private DateUtils() {
    }

    public static String yyyyMMdd(LocalDateTime time) {
        return time.format(YYYYMMDD);
    }

    public static String yyyyMMddHHmmss(LocalDateTime time) {
        return time.format(YYYYMMDDHHMMSS);
    }
}
