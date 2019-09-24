package com.example.administrator.testz;

/**
 * Created by wrs on 2019/9/23,17:02
 * projectName: Testz
 * packageName: com.example.administrator.testz
 */
public class NettyClientEvent {
    private String status;

    private Boolean isCompassing;

    public NettyClientEvent(String status, Boolean isCompassing) {
        this.status = status;
        this.isCompassing = isCompassing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCompassing() {
        return isCompassing;
    }

    public void setCompassing(Boolean compassing) {
        isCompassing = compassing;
    }


}
