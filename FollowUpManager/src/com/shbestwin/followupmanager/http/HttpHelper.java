package com.shbestwin.followupmanager.http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class HttpHelper {
	// public static final int HTTP_POST = 0;
	// public static final int HTTP_GET = 1;
	// private static final int mTimeoutConnection = 120 * 1000;
	// private static final int mTimeoutSocket = 130 * 1000;
	//
	// /**
	// *
	// * @param serverUrl
	// * 服务器url
	// * @param remoteType
	// * HttpHelper.HTTP_POST 或 HttpHelper.HTTP_GET
	// * @param hashMap
	// * 请求参数的hashmap
	// * @return 服务器返回的string
	// * @throws Exception
	// */
	// public static String doRequestForString(Context context, String
	// serverUrl,
	// int remoteType, Map<String, String> hashMap) throws Exception {
	// int timeoutConnection = mTimeoutConnection;
	// int timeoutSocket = mTimeoutSocket;
	//
	// return doRequestForString(context, serverUrl, remoteType, hashMap,
	// timeoutConnection, timeoutSocket);
	// }
	//
	// /**
	// *
	// * @param serverUrl
	// * 服务器url
	// * @param remoteType
	// * HttpHelper.HTTP_POST 或 HttpHelper.HTTP_GET
	// * @param hashMap
	// * 请求参数的hashmap
	// * @param timeoutConnection
	// * 建立连接超时时间
	// * @param timeoutSocket
	// * 等待数据超时时间
	// * @return 服务器返回的string
	// * @throws Exception
	// */
	// public static String doRequestForString(Context context, String
	// serverUrl,
	// int remoteType, Map<String, String> hashMap, int timeoutConnection,
	// int timeoutSocket) throws Exception {
	// String result = "";
	// HttpEntity entity = doRequestForEntity(context, serverUrl, remoteType,
	// hashMap, timeoutConnection, timeoutSocket);
	// if (entity != null)
	// result = EntityUtils.toString(entity, "UTF-8");
	// return result;
	// }
	//
	// /**
	// *
	// * @param serverUrl
	// * 服务器url
	// * @param remoteType
	// * HttpHelper.HTTP_POST 或 HttpHelper.HTTP_GET
	// * @param hashMap
	// * 请求参数的hashmap
	// * @return 服务器返回的HttpEntity
	// * @throws Exception
	// */
	// public static HttpEntity doRequestForEntity(Context context,
	// String serverUrl, int remoteType, Map<String, String> hashMap)
	// throws Exception {
	// int timeoutConnection = mTimeoutConnection;
	// int timeoutSocket = mTimeoutSocket;
	// return doRequestForEntity(context, serverUrl, remoteType, hashMap,
	// timeoutConnection, timeoutSocket);
	// }
	//
	// /**
	// *
	// * @param serverUrl
	// * 服务器url
	// * @param remoteType
	// * HttpHelper.HTTP_POST 或 HttpHelper.HTTP_GET
	// * @param hashMap
	// * 请求参数的hashmap
	// * @param timeoutConnection
	// * 建立连接超时时间
	// * @param timeoutSocket
	// * 等待数据超时时间
	// * @return 服务器返回的HttpEntity
	// * @throws Exception
	// */
	// public static HttpEntity doRequestForEntity(Context context,
	// String serverUrl, int remoteType, Map<String, String> hashMap,
	// int timeoutConnection, int timeoutSocket) throws Exception {
	// HttpEntity entity = null;
	// HttpResponse httpResponse = doRequestForResponse(context, serverUrl,
	// remoteType, hashMap, timeoutConnection, timeoutSocket);
	// int status = httpResponse.getStatusLine().getStatusCode();
	// if (status != 401) {
	// entity = httpResponse.getEntity();
	// }
	// return entity;
	// }
	//
	// /**
	// *
	// * @param serverUrl
	// * 服务器url
	// * @param remoteType
	// * HttpHelper.HTTP_POST 或 HttpHelper.HTTP_GET
	// * @param hashMap
	// * 请求参数的hashmap
	// * @return 服务器返回的HttpResponse
	// * @throws Exception
	// */
	// public static HttpResponse doRequestForResponse(Context context,
	// String serverUrl, int remoteType, Map<String, String> hashMap)
	// throws Exception {
	// int timeoutConnection = mTimeoutConnection;
	// int timeoutSocket = mTimeoutSocket;
	//
	// return doRequestForResponse(context, serverUrl, remoteType, hashMap,
	// timeoutConnection, timeoutSocket);
	// }
	//
	// /**
	// *
	// * @param serverUrl
	// * 服务器url
	// * @param remoteType
	// * HttpHelper.HTTP_POST 或 HttpHelper.HTTP_GET
	// * @param hashMap
	// * 请求参数的hashmap
	// * @param timeoutConnection
	// * 建立连接超时时间
	// * @param timeoutSocket
	// * 等待数据超时时间
	// * @return 服务器返回的HttpResponse
	// * @throws Exception
	// */
	// public static HttpResponse doRequestForResponse(Context context,
	// String serverUrl, int remoteType, Map<String, String> hashMap,
	// int timeoutConnection, int timeoutSocket) throws Exception {
	// StringBuilder sb = new StringBuilder(serverUrl);
	// if (hashMap != null) {
	// for (Entry<String, String> entry : hashMap.entrySet()) {
	// if (!sb.toString().contains("?"))
	// sb.append("?" + entry.getKey() + "=" + entry.getValue());
	// else
	// sb.append("&" + entry.getKey() + "=" + entry.getValue());
	// }
	// }
	//
	// HttpUriRequest request = null;
	// if (remoteType == HTTP_POST) {
	// if (hashMap != null) {
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// for (Entry<String, String> entry : hashMap.entrySet()) {
	// params.add(new BasicNameValuePair(entry.getKey(), entry
	// .getValue()));
	// }
	// HttpPost post = new HttpPost(serverUrl);
	// post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	// request = post;
	// } else {
	// HttpPost post = new HttpPost(serverUrl);
	// request = post;
	// }
	// } else if (remoteType == HTTP_GET) {
	// if (hashMap != null) {
	// for (Entry<String, String> entry : hashMap.entrySet()) {
	// if (!serverUrl.contains("?"))
	// serverUrl += "?" + entry.getKey() + "="
	// + entry.getValue();
	// else
	// serverUrl += "&" + entry.getKey() + "="
	// + entry.getValue();
	// }
	// }
	// request = new HttpGet(serverUrl);
	// }
	// HttpParams httpParameters = new BasicHttpParams();
	// HttpConnectionParams.setConnectionTimeout(httpParameters,
	// timeoutConnection);
	// HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	// DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
	//
	// if (!isNetworkConnected(context)) {
	// String proxyHost = android.net.Proxy.getDefaultHost();
	// if (proxyHost != null) {
	// HttpHost proxy = new HttpHost(
	// android.net.Proxy.getDefaultHost(),
	// android.net.Proxy.getDefaultPort());
	// httpClient.getParams().setParameter(
	// ConnRouteParams.DEFAULT_PROXY, proxy);
	// }
	// }
	//
	// HttpResponse httpResponse = httpClient.execute(request);
	// return httpResponse;
	// }
	//
	// /**
	// * 监测网络是否存在wifi状态
	// *
	// * @param paramActivity
	// * @return
	// */
	// public static boolean isNetworkConnected(Context context) {
	// ConnectivityManager cwjManager = (ConnectivityManager) context
	// .getSystemService(Context.CONNECTIVITY_SERVICE);
	// NetworkInfo mNetworkInfo = cwjManager.getActiveNetworkInfo();
	// if (mNetworkInfo != null) {
	// return mNetworkInfo.isAvailable();
	// }
	// return false;
	// }

	private static final int DEFAULT_MAX_CONNECTIONS = 30;

	private static final int DEFAULT_SOCKET_TIMEOUT = 30 * 1000;

	private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;

	private static DefaultHttpClient sHttpClient;

	private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

	private static long totalSize = 0; // 记录传输的进度

	@SuppressWarnings("deprecation")
	public static String postVoiceByHttpClient(Context context, String url,
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

	
	 public static String PostFromWebByHttpClient(Context context, String url,
	            String username,String password) {
	        String result = null;
	        HttpClient client = getHttpClient(context);
	        url = url + "?username=" + username + "&password=" + password;
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
	                result = EntityUtils.toString(response.getEntity(),
	                        DEFAULT_PARAMS_ENCODING);
	            } else {
	                System.out.println(response.getStatusLine().getStatusCode());
	            }
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (ConnectTimeoutException e) {
	            e.printStackTrace();
	        }catch (SocketTimeoutException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	
	
	 public static String downloadAttachFile(Context context, String fileurl,
	            String savefile, Handler handler) {
	        String result=null;
	        int count = 0, lastCount = 0;
	        int divide = 1;
	        try {
	            fileurl=URLEncoder.encode(fileurl,"utf-8").replaceAll("\\+", "%20").replaceAll("%3A", ":").replaceAll("%2F", "/");
	            URL myURL = new URL(fileurl);
	            URLConnection conn = myURL.openConnection();
	            conn.connect();
	            InputStream is = conn.getInputStream();
	            int fileSize = conn.getContentLength();// 根据响应获取文件大小
	            if (fileSize <= 0)
	                throw new RuntimeException("无法获知文件大小 ");
	            if (is == null)
	                throw new RuntimeException("stream is null");
	            File file = new File(savefile);
	            if (!file.exists()) {
	                try {
	                    file.createNewFile();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            FileOutputStream fos = new FileOutputStream(file);
	            // 把数据存入路径+文件名
	            byte buf[] = new byte[1024];
	            int downLoadFileSize = 0;
	            do {
	                // 循环读取
	                int numread = is.read(buf);
	                if (numread == -1) {
	                    break;
	                }
	                fos.write(buf, 0, numread);
	                downLoadFileSize += numread;
	                count = 100 * downLoadFileSize / fileSize;
	                if (count - lastCount > divide) {
	                    lastCount = count;
	                    System.out.println("下载进度：" + count);
	                    Message message = new Message();
	                    message.what = 100;
	                    message.arg1 = count;
	                    handler.sendMessage(message);
	                }
	            } while (true);
	            is.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	
}
