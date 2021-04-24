package com.yzk46.book.constant;

public interface RedisCons {
    //点赞数量hkey
    public static final String LIKE_KEY = "LIKE_CACHE";
    //点赞初始值
    public static final String  LIKE_INIT = "1";
    //记录类key
    public static final String RECORD_KEY = "RECORD_CACHE_";

    public static final String SEARCH_INIT = "1";

    public static final String BROWSE_INIT = "1";
}
