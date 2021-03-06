package com.shbestwin.followupmanager.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.shbestwin.followupmanager.model.MessageItem;
import android.content.Context;

@SuppressWarnings("deprecation")
public class HttpHelper {
    private static final int DEFAULT_MAX_CONNECTIONS = 30;

    private static final int DEFAULT_SOCKET_TIMEOUT = 30 * 1000;

    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;

    private static DefaultHttpClient sHttpClient;

    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

    public static String uploadHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath) {
        String result = null;
        HttpClient client = getHttpClient(context);
        HttpPost post = new HttpPost(url);
        File file = new File(filepath);
        FileBody fileBody = new FileBody(file);
        MultipartEntity entity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE);
        @SuppressWarnings("rawtypes")
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry items = (Map.Entry) iterator.next();
            try {
                entity.addPart((String) items.getKey(), new StringBody(
                        (String) items.getValue()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            entity.addPart("Content-Type", new StringBody(
                    "multipart/form-data;charset=utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        entity.addPart("datafile", fileBody);
        post.setEntity(entity);
        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(),
                        DEFAULT_PARAMS_ENCODING);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置http头信息
     * 
     * @return
     */
    public static Map<String, String> getHeader() {
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("Accept", "application/json, application/json");
        heads.put("Content_Type", "application/json; charset=utf-8");
        heads.put("Expect", "100-continue");
        return heads;
    }

    /**
     * 
     * 创建httpClient实例
     * 
     * @param context
     * @return
     */
    private static synchronized HttpClient getHttpClient(Context context) {
        final HttpParams httpParams = new BasicHttpParams();
        httpParams.setParameter(AllClientPNames.HANDLE_REDIRECTS, false);
        // 超时设置
        /* 从连接池中取连接的超时时间 */
        ConnManagerParams.setTimeout(httpParams, 1000);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams,
                new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(httpParams,
                DEFAULT_MAX_CONNECTIONS);
        // 设置组件参数, HTTP协议的版本
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
        HttpConnectionParams.setStaleCheckingEnabled(httpParams, false);
        HttpClientParams.setRedirecting(httpParams, false);
        HttpProtocolParams.setUserAgent(httpParams, "Android client");
        /* 请求超时 */
        HttpConnectionParams.setSoTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
        /* 连接超时 */
        HttpConnectionParams.setConnectionTimeout(httpParams,
                DEFAULT_SOCKET_TIMEOUT);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setSocketBufferSize(httpParams,
                DEFAULT_SOCKET_BUFFER_SIZE);
        // 设置我们的HttpClient支持HTTP和HTTPS两种模式
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", PlainSocketFactory
                .getSocketFactory(), 443));
        // 使用线程安全的连接管理来创建HttpClient
        ClientConnectionManager manager = new ThreadSafeClientConnManager(
                httpParams, schemeRegistry);
        sHttpClient = new DefaultHttpClient(manager, httpParams);
        return sHttpClient;
    }

    public static String DowmloadHttpClient(Context context, String url,
            HashMap<String, String> hashParams, File file) {
        String result = null;
        HttpClient client = getHttpClient(context);

        if (hashParams != null) {
            for (Entry<String, String> entry : hashParams.entrySet()) {
                if (!url.contains("?"))
                    url += "?" + entry.getKey() + "=" + entry.getValue();
                else
                    url += "&" + entry.getKey() + "=" + entry.getValue();
            }
        }
        url = url.replaceAll(" ", "");
        HttpGet get = new HttpGet(url);
        // 设置头文件
        Map<String, String> headers = getHeader();
        Set<String> setHead = headers.keySet();
        Iterator<String> iteratorHead = setHead.iterator();
        while (iteratorHead.hasNext()) {
            String headName = iteratorHead.next();
            String headValue = (String) headers.get(headName);
            get.setHeader(headName, headValue);
        }
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                if(!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                // 得到网络资源并写入文件
                InputStream input = response.getEntity().getContent();
//                result=readInputStream(input);
//                if(result.contains("success")&&result.contains("msg")){
//                	return result;
//                }
                byte b[] = new byte[1024];
                int j = 0;
                while ((j = input.read(b)) != -1) {
                    fos.write(b, 0, j);
                }
                fos.flush();
                fos.close();
                
                MessageItem item=new MessageItem("true","下载成功");
                result=new Gson().toJson(item);
            }else {
                MessageItem item=new MessageItem("false","下载失败");
                result=new Gson().toJson(item);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public static String readInputStream(InputStream is) {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        try {
			while ((str = bufferedReader.readLine()) != null) {
			    buffer.append(str);
			    buffer.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return buffer.toString();
    }

    
    
    public static String GetHttpClient(Context context, String url,
            HashMap<String, String> hashParams) {
        String result = null;
        HttpClient client = getHttpClient(context);

        if (hashParams != null) {
            for (Entry<String, String> entry : hashParams.entrySet()) {
                if (!url.contains("?"))
                    url += "?" + entry.getKey() + "=" + entry.getValue();
                else
                    url += "&" + entry.getKey() + "=" + entry.getValue();
            }
        }
        System.out.println(url);
        url = url.replaceAll(" ", "");
        HttpGet get = null;
        try {
        	get= new HttpGet(url);
		} catch (Exception e) {
			MessageItem item=new MessageItem("false","登陆失败，无效的服务地址！");
            result=new Gson().toJson(item);
            return result;
		}
       
        // 设置头文件
        Map<String, String> headers = getHeader();
        Set<String> setHead = headers.keySet();
        Iterator<String> iteratorHead = setHead.iterator();
        while (iteratorHead.hasNext()) {
            String headName = iteratorHead.next();
            String headValue = (String) headers.get(headName);
            get.setHeader(headName, headValue);
        }
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
            	 result = EntityUtils.toString(response.getEntity(),
                         DEFAULT_PARAMS_ENCODING);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            MessageItem item=new MessageItem("false","登陆失败！");
            result=new Gson().toJson(item);
        } catch (ConnectTimeoutException e) {
        	MessageItem item=new MessageItem("false","登陆失败，连接超时！");
            result=new Gson().toJson(item);
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
        	MessageItem item=new MessageItem("false","登陆失败，连接超时！");
            result=new Gson().toJson(item);
            e.printStackTrace();
        } catch (IOException e) {
        	MessageItem item=new MessageItem("false","登陆失败！");
            result=new Gson().toJson(item);
            e.printStackTrace();
        }
        return result;
    }
}
