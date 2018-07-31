package com.sunbaby.app.event;

/**
 * @author wangjingbo
 * @date 2018/7/20
 * describe
 */
public class EventMessage {
    private String classInfo;

    public String getClassInfo() {
        return classInfo == null ? "" : classInfo;
    }

    public EventMessage(String classInfo) {
        this.classInfo = classInfo;
    }
}
