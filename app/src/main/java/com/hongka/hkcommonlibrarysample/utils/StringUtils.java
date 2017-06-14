package com.hongka.hkcommonlibrarysample.utils;

import com.hongka.hkcommonlibrary.utils.NumberUtils;

/**
 * Created by jusung.kim@sk.com on 2017/05/23
 */

public class StringUtils {
    public static String getVideoCount(String count) {
        return "영상수 : " + NumberUtils.commaString(NumberUtils.parse(count, 0));
    }

    public static String getSubscriberCount(String count) {
        return "구독자 : " + NumberUtils.commaString(NumberUtils.parse(count, 0));
    }

    public static String getViewCount(String count) {
        return "조회수 : " + NumberUtils.commaString(NumberUtils.parse(count, 0));
    }

    public static String getSubscriberCountAndViewCount(String sCount, String vCount) {
        return "구독자 : " + NumberUtils.commaString(NumberUtils.parse(sCount, 0)) + ", " + "조회수 : " + NumberUtils.commaString(NumberUtils.parse(vCount, 0));
    }

    public String getTime(int seconds) {
        if (seconds == 0) {
            return "No Data";
        }

        int h = 0, m = 0, s = 0;
        int sec = seconds;

        if (sec > 3600) {
            h = sec / 3600;
            sec %= 3600;
        }

        if (sec > 60) {
            m = sec / 60;
            sec %= 60;
        }

        if (sec < 60) {
            s = sec;
        }

        if (h > 0) {
            return getStringNumber(h) + ":" + getStringNumber(m) + ":" + getStringNumber(s);
        }

        if (m > 0) {
            return getStringNumber(m) + ":" + getStringNumber(s);
        }

        if (s > 0) {
            return "00:" + getStringNumber(s);
        }

        return "00:00:00";
    }

    private String getStringNumber(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return "" + num;
        }
    }
}
