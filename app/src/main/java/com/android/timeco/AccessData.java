package com.android.timeco;

public class AccessData {

    /**
     * Singleton
     */
    private static AccessData accessdata;

    /**
     * Basic Builder
     */
    private AccessData (){}

    public static AccessData get() {
        if (accessdata == null) {
            accessdata = new AccessData();
        }
        return accessdata;
    }
}
