package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;

public class RestHttpClientApp_Prod {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ϳ��� �ּ� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////	
		
//		RestHttpClientApp_Prod.AddProduct_Codehaus();

//		RestHttpClientApp_Prod.GetProduct_Codehaus();

//		 RestHttpClientApp_Prod.UpdateProduct_Codehaus();

// 		RestHttpClientApp_Prod.ListProduct_Codehaus();

	}
	
//================================================================//

public static void AddProduct_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		
		//����Ʈ ������� ������Ʈ 
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� : codehaus ���]
		Product product01 = new Product();
		product01.setProdName("sonic");
		product01.setManuDate("19851013");
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(product01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}	


	public static void GetProduct_Codehaus() throws Exception {
		//httpclient: http ���������� Ŭ���̾�Ʈ �߻�ȭ
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/product/json/getProduct/10127";
		//http ���������� get ������� ������Ʈ
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		//http ��������: http �������� �������� �޽��� �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
	
		System.out.println(httpResponse);
		System.out.println();
		
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		//��ǲ ��Ʈ�� ���� & ���� ������ ����
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	
		System.out.println("[Server ���� ���� DATA Ȯ��]");
		String serverData = br.readLine();
		System.out.println("getProduct: serverData :: "+serverData);
	
		//����������(���̽� ���) Ȯ�� 
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println("RestHttpClientApp - getProduct: "+jsonobj);
	
	/*	ObjectMapper objectMapper = new ObjectMapper();
		Product returnProduct = objectMapper.readValue(jsonobj.get("product").toString(), Product.class);
		System.out.println(returnProduct);*/

}
	
public static void UpdateProduct_Codehaus() throws Exception {
	// HttpClient : Http Protocol �� client �߻�ȭ 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/product/json/updateProduct";
	//post ������� ������Ʈ
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");

	//�ڵ��Ͽ콺 ���
	Product product01 = new Product();
	product01.setProdNo(10127);
	/*product01.setProdName("shibaInu");
	product01.setProdDetail("�ùٰ�");
	product01.setManuDate("20180524");
	product01.setPrice(550000);*/
	
	ObjectMapper objectMapper01 = new ObjectMapper();
	//Object ==> JSON Value �� ��ȯ
	String jsonValue = objectMapper01.writeValueAsString(product01);
	
	//������Ʈ �ٵ� �����
	HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
	httpPost.setEntity(httpEntity01);
	//http ��������: http �������� �������� �޽����� �߻�ȭ�� ��
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response Ȯ��
	System.out.println(httpResponse);
	System.out.println();

	//==> Response �� entity(DATA) Ȯ��
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream ���� & ���� ������ ����
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	//==> API Ȯ�� : Stream ��ü�� ���� ���� 
	JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
	System.out.println("RestHttpClientApp - updateProduct: "+jsonobj);

	ObjectMapper objectMapper = new ObjectMapper();
	 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
	 System.out.println(product);

}	
	

	
}