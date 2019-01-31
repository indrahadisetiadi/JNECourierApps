package com.example.ging.jnecourierapps.Url;

public class BaseUrl {
//    private String Url = "http://10.2.172.221/JNECourierOptimazion/public/api/";
//    private String Url = "http://10.1.9.240:8000/api/";
    private String Url = "http://172.20.10.3:8000/";
//    private String Url = "http://10.1.8.158:1001/api/";
//private String Url = "http://127.0.0.1:8000/api/";
//    private String Url = "http://127.0.0.1:8000/api/";
    public String getUrl(){
        return this.Url+"api/";
    }

    public String getImagesUrl(){
        return this.Url;
    }

}
