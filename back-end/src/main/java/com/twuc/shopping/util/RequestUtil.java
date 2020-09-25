package com.twuc.shopping.util;

public class RequestUtil {

    public static int[] processPagination(Integer pageSize, Integer pageIndex) {

        int pageSizeInt, pageIndexInt;

        if (pageSize == null)
            pageSizeInt = 10;
        else
            pageSizeInt = pageSize;

        if (pageIndex == null)
            pageIndexInt = 1;
        else
            pageIndexInt = pageIndex;

        if (pageSizeInt < 1 || pageIndexInt < 1)
            throw new IllegalArgumentException("invalid param");

        return new int[] { pageSizeInt, pageIndexInt - 1 };
    }

    private RequestUtil() {}

}
