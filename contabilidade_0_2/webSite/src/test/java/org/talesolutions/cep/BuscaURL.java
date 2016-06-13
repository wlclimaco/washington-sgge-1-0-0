package org.talesolutions.cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import model.TokenModel;

public class BuscaURL {


	private static final String SENHA = "devil";
	private static final String USER = "taz@qat.com";
	final String uri = "http://localhost:8080/qat-sysmgmt-controller-rest/auth/api/authenticate";
	@Test
	public void busca_por_cep_valido() {

		HttpHeaders headers = new HttpHeaders();
//		headers.set("Header", "value");
//		headers.set("Other-Header", "othervalue");
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.set("Access-Control-Allow-Origin", "*");
//		headers.set("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//		headers.set("Access-Control-Max-Age", "3600");
//		headers.set("X-Auth-Token", "taz@qat.com:1465612675629:4da0fd5742fdfbfe11092f4655ddd2b7");
		//    headers.add("username", USER);
		    headers.add("X-Auth-Token", "taz@qat.com:1465612675629:4da0fd5742fdfbfe11092f4655ddd2b7");
		//    headers.setContentType(MediaType.APPLICATION_XML);
		//    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
//		    final HttpEntity<MyXmlbeansRequestDocument> httpEntity = new HttpEntity<MyXmlbeansRequestDocument>(
//		            MyXmlbeansRequestDocument.Factory.parse(request), headers);
//		    final ResponseEntity<MyXmlbeansResponseDocument> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,MyXmlbeansResponseDocument.class);
//		    log.info(responseEntity.getBody());

		Map<String, String> params = new HashMap<String, String>();
	    params.put("username", USER);
	    params.put("password", SENHA);

		HttpEntity entity = new HttpEntity(params,headers);
		RestTemplate restTemplate = new RestTemplate();
		TokenModel response = restTemplate.getForObject(
				uri,TokenModel.class,HttpMethod.POST,params);

		System.out.println(response);

//	    Map<String, String> params = new HashMap<String, String>();
//	    params.put("id", "2");
//
//	    String a =  restTemplate.getForObject(uri, String.class, params);
//
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//	    headers.set("Authorization", "Bearer "+accessToken);
//
		URI uri = restTemplate.postForLocation("http://localhost:8080/qat-sysmgmt-controller-rest/auth/api/authenticate/username/taz@qat.com/password/devil", "", String.class);
	    HttpEntity<String> entitys = new HttpEntity<String>(headers);
	    String n = restTemplate.postForObject(uri+"&username=taz@qat.com&password=devil",HttpMethod.POST, String.class);

	    System.out.println(n);
	}

	@Test
	public void busca_por_cep_valido2() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Header", "value");
		headers.set("Other-Header", "othervalue");
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Access-Control-Allow-Origin", "*");
		headers.set("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		headers.set("Access-Control-Max-Age", "3600");
		headers.set("username", USER);
		headers.set("password", SENHA);
		Map<String, String> params = new HashMap<String, String>();
	    params.put("username", USER);
	    params.put("password", SENHA);
	//	URI uri = restTemplate.postForLocation("http://localhost:8080/qat-sysmgmt-controller-rest/auth/api/authenticate/username/taz@qat.com/password/devil", "", String.class);
	    HttpEntity<String> entitys = new HttpEntity<String>(headers);
	 //   TokenModel n = restTemplate.postForObject(uri,HttpMethod.POST, TokenModel.class);
	    HttpHeaders a = restTemplate.headForHeaders(uri+"?username=eadddd&password=tesra",HttpMethod.POST);

	    System.out.println(HttpHeaders.ACCEPT);

	}
	@Test
	public void busca_por_cep_valido3() throws IOException {

		URL url = new URL(uri);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("username", USER);
		conn.setRequestProperty("password", SENHA);

		String input = "{\"username\":100,\"password\":\"iPad 4\"}";

		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();
	}
	@Test
	public void busca_por_cep_valido4() throws Exception {
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(uri, 8080),
                new UsernamePasswordCredentials("username :"+ USER, SENHA));

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        try {
            HttpPost httpget = new HttpPost(uri);

            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
	}
	@Test
	public void busca_por_cep_valido5() throws ClientProtocolException, IOException  {
	    HttpClient client = new DefaultHttpClient();
	    HttpPost post = new HttpPost(uri);
	    List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	    pairs.add(new BasicNameValuePair("username",USER));
	    pairs.add(new BasicNameValuePair("password",SENHA));
	    post.setHeader("Content-type", "application/json");
	    post.setHeader("Accept", "application/json");
	    post.setHeader("X-Auth-Token", "taz@qat.com:1465612675629:4da0fd5742fdfbfe11092f4655ddd2b7");
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs,"UTF-8");
	    post.setEntity(entity);
	    HttpResponse response = client.execute(post);

	    System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
	}


}
