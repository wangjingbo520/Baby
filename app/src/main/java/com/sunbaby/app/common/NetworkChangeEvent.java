package com.sunbaby.app.common;

/**
 * @author Wangjingbo
 * @date 2018/7/31
 * describe
 */
public class NetworkChangeEvent {
    public boolean isConnected;

    public NetworkChangeEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
