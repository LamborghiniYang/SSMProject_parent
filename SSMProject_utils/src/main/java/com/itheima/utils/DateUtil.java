package com.itheima.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: wyan
 * @Date: 2018/11/23 15:57
 * @Description:
 */
public class DateUtil {

    /**
     * 功能描述:接受日期返回字符串
     * @param: [date]
     * @return: java.lang.String
     * @auther: wyan
     * @date: 2018/11/23 15:58
     */
    public static   String formatDateToStr(Date date){

        try {
            //创建格式转哈类
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 功能描述:接受字符串转换日期返回
     * @param: [dateStr]
     * @return: java.util.Date
     * @auther: wyan
     * @date: 2018/11/23 16:50
     */
    public static Date parseStrToDate(String dateStr) {
        try {
            //创建格式转哈类
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
