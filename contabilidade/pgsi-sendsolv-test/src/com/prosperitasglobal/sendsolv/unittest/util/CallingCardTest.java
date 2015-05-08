package com.prosperitasglobal.sendsolv.unittest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/unittest-datasource-txn-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/sendsolv-dac-context.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
@ActiveProfiles("sqlserver")
public class CallingCardTest extends AbstractJUnit4SpringContextTests
{
	@Test
	public void testCallingCard()
	{
		StringBuilder requestUrl = new StringBuilder("http://api.pinprofit.com/pin/refill.php/xml");
		StringBuffer result = new StringBuffer();

		HttpClient client = HttpClientBuilder.create().build();

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("id_pin", "8435958"));
		urlParameters.add(new BasicNameValuePair("amount", "-1"));
		urlParameters.add(new BasicNameValuePair("l_email", "randy_parsons@qat.com"));
		urlParameters.add(new BasicNameValuePair("l_password", "QATpgsi$"));

		String querystring = URLEncodedUtils.format(urlParameters, "utf-8");

		try
		{

			requestUrl.append("?");
			requestUrl.append(querystring);
			HttpPost post = new HttpPost(requestUrl.toString());

			System.out.println("*********** executing request " + post.getRequestLine());

			HttpResponse response = client.execute(post);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null)
			{
				result.append(line);
			}
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(result.toString());
	}
}
