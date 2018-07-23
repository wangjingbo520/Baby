package com.sunbaby.app.bean;

/**
 * @author 王静波
 * @date 2018/7/23
 * describe
 */
public class UploadFile {

    /**
     * filePath : /data/auditInfos/fc4bbb1703144e73_12312312.jpg
     * fielName : 12312312.jpg
     */

    private String filePath;
    private String fielName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFielName() {
        return fielName;
    }

    public void setFielName(String fielName) {
        this.fielName = fielName;
    }
}