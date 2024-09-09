package com.demo.config;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static com.demo.utilities.FileUtility.getFormattedJson;
import static org.apache.http.impl.client.HttpClientBuilder.*;

public class HttpHelper {

    public static HttpEntity httpEntity;
    public static CloseableHttpClient client;
    public static CloseableHttpResponse response;
    public static JSONObject requestBody = new JSONObject();
    public static CookieStore cookieStore = new BasicCookieStore();

    private static String url;
    private static String responseBody;
    private static int responseCode;
    private static String responseHeaders;
    private static String responseMsg;
    private static String entity;



    public static String getUrl() {
        return url;
    }

    public static void setUrl(String host, String path) throws URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost(host)
                .setPath(path)
                .build();

        url = uri.toString();
    }

    public static String getResponseBody() {
        return responseBody;
    }

    public static void setResponseBody(String newResponseBody) {
        responseBody = newResponseBody;
    }

    public static int getResponseCode() {
        return responseCode;
    }

    public static void setResponseCode(int newResponseCode) {
        responseCode = newResponseCode;
    }

    public static String getResponseHeaders() {
        return responseHeaders;
    }

    private static void setResponseHeaders(String newResponseHeaders) {
        responseHeaders = newResponseHeaders;
    }

    public static String getResponseMsg() {
        return responseMsg;
    }

    public static void setResponseMsg(String newResponseMsg) {
        responseMsg = newResponseMsg;
    }

    public static String getEntity() {
        return entity;
    }

    public static void setEntity(String newEntity) {
        entity = newEntity;
    }



    public static void getClosableHttpClientResponseDetails(CloseableHttpResponse response) throws Exception {
        try {
            httpEntity = response.getEntity();
            String responseStringEntity = EntityUtils.toString(httpEntity, "UTF-8");
            setEntity(responseStringEntity);

            responseBody = getFormattedJson(responseStringEntity);
            setResponseBody(responseBody);

            responseCode = response.getStatusLine().getStatusCode();
            setResponseCode(responseCode);

            responseMsg  = response.getStatusLine().getReasonPhrase();
            setResponseMsg(responseMsg);

            responseHeaders = Arrays.asList(response.getAllHeaders())
                    .toString()
                    .replace(", ", "\n")
                    .replace("[", "")
                    .replace("]", "");
            setResponseHeaders(responseHeaders);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static CloseableHttpClient setHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(300000)
                .setConnectTimeout(300000)
                .setConnectionRequestTimeout(300000)
                .setRedirectsEnabled(true)
                .build();

        return  client = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .setDefaultRequestConfig(config)
                .build();
    }
}
