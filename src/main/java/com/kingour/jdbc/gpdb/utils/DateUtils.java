package com.kingour.jdbc.gpdb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

    static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String formatDate(Date date, String... format) {
        if (null == date) {
            return "";
        }
        String pattern = "yyyy-MM-dd HH:mm";
        if (null != format && format.length > 0 && !"".equals(format[0])) {
            pattern = format[0];
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parseDate(String str, String... format) {
        if (!StringUtils.isNotEmpty(str)) {
            return null;
        }
        String pattern = "yyyy-MM-dd HH:mm";
        if (null != format && format.length > 0 && !"".equals(format[0])) {
            pattern = format[0];
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
    }
}
