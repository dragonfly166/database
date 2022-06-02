package com.database.util;

import java.util.Set;

/**
 * @author sunlongfei
 */
public class DataSourceUtil {

    private static Set<Object> dataSourceKeys;

    public final static ThreadLocal<String> KEY = ThreadLocal.withInitial(() -> "primary");

    public static void setDataSourceKeys(Set<Object> dataSourceKeys) {
        DataSourceUtil.dataSourceKeys = dataSourceKeys;
    }


    public static void setDataSource(String key) throws Exception {
        if (!dataSourceKeys.contains(key)) {
            throw new Exception("datasource key error");
        }
        KEY.set(key);
    }

    public static String getDataSourceKey() {
        return KEY.get();
    }

    public static void clearDataSourceKey() {
        KEY.remove();
    }
}
