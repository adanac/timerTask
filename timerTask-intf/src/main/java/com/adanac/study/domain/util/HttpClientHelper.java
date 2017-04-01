package com.adanac.study.domain.util;


import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/6/22
 * Time: 10:11
 * Description:
 */
public class HttpClientHelper {
//    private static String cookie="__jda=122270672.2049109131.1465892351.1466501073.1466558829.24;__jdv=122270672|direct|-|none|-;__jdu=2049109131; _tp=YkEuLzLEGKxHCRz8CZe5nO14CyfBW3m1zbuICFKj2Io%3D;_pst=%E5%A4%AAOUT%E4%BA%86;TrackID=1hOpRkB81LjuG5X8Q77YhMZYhNiWoalp7GNnVIp-ArkymZHh6LFprx_o-moi1wq_K-mgPGiCKfwOrAFEhlx-3FTNwVqyexDk_j8iFhTAelEUpinId=F7rzbqjB9hiuWrF0ITmsfQ;thor=911F6AE04A9AF78116457ED5FD112FFC46ACC4BFA652C90C1455D8A880DBF17" +
//            "CF1904DEDF3D62616FE4808408EFFF36C5AA88D3D9F78D3D7A7E57266D0C3B38D937543442ED00B79ECEBBE4269250B81127DFC255A1ADCE30B64E267778325A0EF128AF0B30820D2A0AAB50D31D929D91E65037B234D41B674B4FECFE391E25B" +
//            ";pin=%E5%A4%AAOUT%E4%BA%86;user-key=d14a6465-9626-449e-af6e-f3801b90cd65;cn=8;ipLocation=%u5317%u4EAC" +
//            ";areaId=1; mt_xid=V2_52007VwATV1VYU1IfSxlsAWcGFAZdDQVGT05MCRliBBVaQQgFXkxVTlkHNQpBW1wMUglIeRpdBWAfE1VBWFRLH08SWAVsARdiX2hQahxIH1QAYjMSVFVQ" +
//            ";unick=%E8%BF%99%E4%B8%AA%E4%BA%8B%E7%A5%9E%E9%A9%AC; ipLoc-djd=1-2810-51081-0;_distM=19780646820;" +
//            "erp1.jd.com=6B31A1731BF54670E59F2D176386D8ABD2639ABF46D6FED2395241EBA94E1832865C460D76B2D2C2807B4796241260783C75B3B1EFBC13A601321F2666B085DBAED631B83139A88EC265AD7F1386AA20" +
//            ";__jdc=122270672;3AB9D23F7A4B3C9B=DC7732D4253A2CD7B5F8EA178EED971176C2B28360CD0152E01FC9619CB3C7ADDBEC7D01582E8F2FD02A3447788F6EF5" +
//            ";_gamecoin_account_=DQAWHTKMLPJRUPXKBP32MGDDLBEASUW3WAG4VIVWCDC53MCBEWYQ";

     private static String cookie = "__jda=122270672.2049109131.1465892351.1466558829.1466573768.25; __jdv=122270672|direct|-|none|-; __jdu" +
            "=2049109131; _tp=JPuXXc7DqM9eSpS4XIbYIQ%3D%3D; _pst=bjdiqi; TrackID=1nRd4egB10vU4Z0jbDwnxzG9mOTtbtymQmAv9spFoF3jYy9quhCdSoIxdvGx6yVN0lwIl7KYReKm4RZQoFlK2_-WzBeudrTCWgIyEEBN3ecY" +
            "; pinId=0_GFPm8nMPk; thor=44F975B80D97400BEA29BE7F71D97C3A24DDA4A77C4EAAF81474AA70DFB562C33955AD6C19" +
            "92766CF2A0F343F47AA5B5936591B5E8B8F88B45AA9DF46E33D44BCA99F168B93E1CEA6849FDA8200B348CD48AAAEF3540D3EF93D2F2A8124B2043F524F5063352A5A8DD8773748F0AB2E8FA90F5375283240F3B0E1E680600E5E1" +
            "; pin=bjdiqi; user-key=d14a6465-9626-449e-af6e-f3801b90cd65; cn=8; ipLocation=%u5317%u4EAC; areaId=1" +
            "; mt_xid=V2_52007VwATV1VYU1IfSxlsAWcGFAZdDQVGT05MCRliBBVaQQgFXkxVTlkHNQpBW1wMUglIeRpdBWAfE1VBWFRLH08SWAVsARdiX2hQahxIH1QAYjMSVFVQ" +
            "; ipLoc-djd=1-2810-51081-0; _distM=19780646820; erp1.jd.com=6B31A1731BF54670E59F2D176386D8ABD2639ABF" +
            "46D6FED2395241EBA94E1832865C460D76B2D2C2807B4796241260783C75B3B1EFBC13A601321F2666B085DBAED631B83139A88EC265AD7F1386AA20" +
            "; __jdc=122270672; _gamecoin_account_=DQAWHTKMLPJRUPXKBP32MGDDLBEASUW3WAG4VIVWCDC53MCBEWYQ; __jdb=122270672" +
            ".3.2049109131|25.1466573768; _jrda=1; _jrdb=1466574557357; 3AB9D23F7A4B3C9B=DC7732D4253A2CD7B5F8EA178EED971176C2B28360CD0152E01FC9619CB3C7ADDBEC7D01582E8F2FD02A3447788F6EF5" +
            "; lighting=AF06F048278A9DE91E4400E22D26736AE322B6C92ABDF80F92499AA42F9AB8BF4DA84C959A0332264911FA26B" +
            "806677493AD99BA9E0A0C378531FFE716222BA2CA8AEFFCE617972D2BD2876F224583969FED2CCC5B1345154F40876A24E6B4326DF22BD17FCFBBC6A711D288AF72707C6C1B224BBF554A17BE33829E9566C0BC" +
            "; ceshi3.com=-NdvIQHcFn5bS96Q9ZsrjrwFGIDAK56sK8gEUKgMUPg";

    public  void get(String url){
        DefaultHttpClient client = getHttpClient();
        HttpGet get = new HttpGet(url);
        cookie = cookie.replaceAll("\\s","");
        get.addHeader(new BasicHeader("Cookie",cookie));
        HttpResponse response = null;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            Header header = entity.getContentEncoding();
            if (header != null) {
                HeaderElement[] codecs = header.getElements();
                for (int i = 0; i < codecs.length; i++)
                {
                    if (codecs[i].getName().equalsIgnoreCase("gzip"))
                    {
                        response.setEntity(new GzipDecompressingEntity(entity));
                    }
                }
            }
            String responseContent = EntityUtils.toString(response.getEntity());
            System.out.println("state ="+response.getStatusLine().getStatusCode());
            System.out.println("response="+responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DefaultHttpClient getHttpClient(){
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
        cm.setMaxTotal(2);
        cm.setDefaultMaxPerRoute(2);
        HttpHost googleResearch = new HttpHost("research.google.com", 80);
        HttpHost wikipediaEn = new HttpHost("en.wikipedia.org", 80);
        cm.setMaxPerRoute(new HttpRoute(googleResearch), 30);
        cm.setMaxPerRoute(new HttpRoute(wikipediaEn), 50);
        DefaultHttpClient client = new DefaultHttpClient(cm);
        setHttpClient(client);
        return client;
    }

    public void  setHttpClient(DefaultHttpClient client){
        Integer socketTimeout = 10000;
        Integer connectionTimeout = 10000;
        final int retryTime = 3;
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, socketTimeout);
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
        client.getParams().setParameter(CoreConnectionPNames.TCP_NODELAY, false);
        client.getParams().setParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 1024 * 1024);
        //client.setCookieStore(getDefaultCookieStore());
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler()
        {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context)
            {
                if (executionCount >= retryTime)
                {
                    // Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof InterruptedIOException)
                {
                    // Timeout
                    return false;
                }
                if (exception instanceof UnknownHostException)
                {
                    // Unknown host
                    return false;
                }
                if (exception instanceof ConnectException)
                {
                    // Connection refused
                    return false;
                }
                if (exception instanceof SSLException)
                {
                    // SSL handshake exception
                    return false;
                }
                HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent)
                {
                    // Retry if the request is considered idempotent
                    return true;
                }
                return false;
            }

        };
        client.setHttpRequestRetryHandler(myRetryHandler);
    }

    public static void requestPost(String url,List<NameValuePair> params) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        cookie = cookie.replaceAll("\\s","");

        HttpPost httppost = new HttpPost(url);
       // httppost.setEntity(new UrlEncodedFormEntity(params));
        httppost.addHeader(new BasicHeader("Cookie",cookie));
        httppost.addHeader("Referer","http://huan.jd.com/");
        CloseableHttpResponse response = httpclient.execute(httppost);
        LogHelper.roomAllLog.info(response.toString());

        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(entity, "utf-8");
        LogHelper.roomAllLog.info(jsonStr);

        httppost.releaseConnection();
    }

    public static void main(String[] args){
        String url = "http://huan.jd.com/json/sign/signIn.action";
        try {
            requestPost(url,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
