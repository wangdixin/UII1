package com.logistics.bean;

/**
 * Created by paopao on 2020/8/31.
 */

public class LoginBean {

    /**
     * code : 1
     * msg : Login successful
     * time : 1598960310
     * data : {"id":1,"username":"admin","token":"b3c7950c-a2ec-4b3b-808a-8112f75fb634"}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * username : admin
         * token : b3c7950c-a2ec-4b3b-808a-8112f75fb634
         */

        private int id;
        private String username;
        private String token;

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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
