package com.yzk46.book.util;

/**
 * @program: cloud
 * @description: 生成随机字符串工具类
 * @author: yzk46
 * @create: 2021-03-03 15:32
 **/
public class RandomCharUtil {
    public static final String BASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static final String LETTER_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static final String LETTER_UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String LETTER_LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";

    public static final String NUMBER_CHARS = "1234567890";

    public static final String SPECIAL_CHARS = "!@#$%^&*";

    /**
     * 生成指定长度字符串，默认不包含特殊字符
     * @param length 生成字符串长度
     * @return
     */
    public static String create(int length) {
        return create(length, false);
    }

    /**
     * 生成随机字符串
     * @param length 生成字符串长度
     * @param hasSpecial 是否包含特殊字符
     * @return
     */
    public static String create(int length, boolean hasSpecial) {
        String chars = hasSpecial ? BASE_CHARS.concat(SPECIAL_CHARS) : BASE_CHARS;
        return create(length, chars);
    }

    /**
     * 在指定的字符内生成指定长度字符串
     * @param length
     * @param string
     * @return
     */
    public static String create(int length, String string) {
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            int index = (int) Math.round(Math.random() * (len - 1));
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }
}
