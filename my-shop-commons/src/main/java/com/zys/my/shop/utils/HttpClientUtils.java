package com.zys.my.shop.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @program: my-shop
 * @description: HttpClient工具类
 * @author: Leo
 * @create: 2019-06-14 21:05
 **/
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";

    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_USERAGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3704.400 QQBrowser/10.4.3587.400";

    /**
    * @Description: Get请求
    * @return: java.lang.String Json字符串
    * @Author: Leo
    */
    public static String doGet(String url){
        return createRequest(GET,url,null);
    }
    /**
    * @Description: Post请求
    * @Param: [url, params] 请求地址， 请求参数（可选）
    * @return: java.lang.String
    * @Author: Leo
    */
    public static String doPost(String url, BasicNameValuePair...params){
        return createRequest(POST,url,null,params);
    }
    /**
    * @Description: Post请求 （带Cookie）
    * @Param: [url, cookie, params] 请求地址，Cookie, 请求参数（可选）
    * @return: java.lang.String
    * @Author: Leo
    */
    public static String doPost(String url,String cookie, BasicNameValuePair...params){
        return createRequest(POST,url,cookie,params);
    }
    /**
    * @Description: 创建请求
    * @Param: [requestMethod, url, cookie, params] 请求方法, 请求地址, cookie, 请求参数（仅限post请求有效）
    * @return: java.lang.String
    * @Author: Leo
    */
    private static String createRequest(String requestMethod, String url,String cookie, BasicNameValuePair...params){
        CloseableHttpClient client = HttpClients.createDefault();
        String result = null;
        try {
            HttpGet httpGet = null;
            HttpPost httpPost = null;
            //响应
            CloseableHttpResponse httpResponse = null;
            //get 请求
            if (GET.equals(requestMethod)){
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("User-Agent",REQUEST_HEADER_USERAGENT);
                if (cookie != null) httpGet.setHeader("Cookie",cookie);
                //得到响应
                httpResponse = client.execute(httpGet);
            }
            //post 请求
            else if (POST.equals(requestMethod)){
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("User-Agent",REQUEST_HEADER_USERAGENT);
                if (cookie != null) httpPost.setHeader("Cookie",cookie);
                //传入了参数
                if (params != null && params.length > 0){
                    //设置请求参数
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params),"UTF-8"));
                }
                //得到响应
                httpResponse = client.execute(httpPost);

            }
            //获取响应内容
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }
}