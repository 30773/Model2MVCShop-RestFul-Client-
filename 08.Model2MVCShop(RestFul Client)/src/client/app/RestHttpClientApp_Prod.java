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
		// 하나씩 주석 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////	
		
//		RestHttpClientApp_Prod.AddProduct_Codehaus();

//		RestHttpClientApp_Prod.GetProduct_Codehaus();

//		 RestHttpClientApp_Prod.UpdateProduct_Codehaus();

// 		RestHttpClientApp_Prod.ListProduct_Codehaus();

	}
	
//================================================================//

public static void AddProduct_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		
		//포스트 방식으로 리퀘스트 
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 : codehaus 사용]
		Product product01 = new Product();
		product01.setProdName("sonic");
		product01.setManuDate("19851013");
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(product01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}	


	public static void GetProduct_Codehaus() throws Exception {
		//httpclient: http 프로토콜의 클라이언트 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/product/json/getProduct/10127";
		//http 프로토콜의 get 방식으로 리퀘스트
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		//http 리스폰스: http 프로토콜 리스폰스 메시지 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
	
		System.out.println(httpResponse);
		System.out.println();
		
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		//인풋 스트림 생성 & 버퍼 리더로 포장
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	
		System.out.println("[Server 에서 받은 DATA 확인]");
		String serverData = br.readLine();
		System.out.println("getProduct: serverData :: "+serverData);
	
		//서버데이터(제이슨 밸류) 확인 
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println("RestHttpClientApp - getProduct: "+jsonobj);
	
	/*	ObjectMapper objectMapper = new ObjectMapper();
		Product returnProduct = objectMapper.readValue(jsonobj.get("product").toString(), Product.class);
		System.out.println(returnProduct);*/

}
	
public static void UpdateProduct_Codehaus() throws Exception {
	// HttpClient : Http Protocol 의 client 추상화 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/product/json/updateProduct";
	//post 방식으로 리퀘스트
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");

	//코드하우스 사용
	Product product01 = new Product();
	product01.setProdNo(10127);
	/*product01.setProdName("shibaInu");
	product01.setProdDetail("시바견");
	product01.setManuDate("20180524");
	product01.setPrice(550000);*/
	
	ObjectMapper objectMapper01 = new ObjectMapper();
	//Object ==> JSON Value 로 변환
	String jsonValue = objectMapper01.writeValueAsString(product01);
	
	//리퀘스트 바디 만들기
	HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
	httpPost.setEntity(httpEntity01);
	//http 리스폰스: http 프로토콜 리스폰스 메시지를 추상화한 빈
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response 확인
	System.out.println(httpResponse);
	System.out.println();

	//==> Response 중 entity(DATA) 확인
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream 생성 & 버퍼 리더로 포장
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	//==> API 확인 : Stream 객체를 직접 전달 
	JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
	System.out.println("RestHttpClientApp - updateProduct: "+jsonobj);

	ObjectMapper objectMapper = new ObjectMapper();
	 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
	 System.out.println(product);

}	
	

	
}