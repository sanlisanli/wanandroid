package me.mikasa.wanandroid.bean;

import java.util.List;

/**
 * Created by mikasa on 2018/12/28.
 */
public class LoginBean {
    /**
     * errorCode : 0
     * errorMsg : null
     * data : {"id":1729,"username":"mikasa","password":"123456","icon":null,"type":0,"collectIds":[]}
     */
    private int errorCode;
    private Object errorMsg;
    private DataBean data;
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
    public static class DataBean{
        /**
         * id : 1729
         * username : mikasa
         * password : 123456
         * icon : null
         * type : 0
         * collectIds : []
         */
        private int id;//int
        private String username;
        private String password;
        private Object icon;//Object
        private int type;
        private List<?> collectIds;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<?> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<?> collectIds) {
            this.collectIds = collectIds;
        }
    }
}
