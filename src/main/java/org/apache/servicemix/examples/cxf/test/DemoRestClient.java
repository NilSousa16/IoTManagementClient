package org.apache.servicemix.examples.cxf.test;

import java.net.URLEncoder;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.google.gson.Gson;

public class DemoRestClient {

	public void run() {
		// DemoRestClient restClient = new DemoRestClient();
		try {
			this.addBookTwo();
			// restClient.getBook("Naked Sun");
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	// public BookVO getBook(String bookName) throws Exception {
	public void getBook(String bookName) throws Exception {

		String output = null;
		try {
			String url = "http://192.168.0.131:8181/cxf/bookservice/getbook/";

			url = url + URLEncoder.encode(bookName, "UTF-8");

			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(url);
			client.executeMethod(mPost);
			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/x-www-form-urlencoded");
			mtHeader.setName("accept");
			mtHeader.setValue("application/xml");
			mPost.addRequestHeader(mtHeader);
			client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString();
			mPost.releaseConnection();
			System.out.println("out : " + output);
		} catch (Exception e) {
			throw new Exception("Exception in retriving group page info : " + e);
		}
		// return null;
	}

	public void addBook() throws Exception {

		String output = null;
		try {
			String url = "http://192.168.0.131:8181/cxf/bookservice/addbook";
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(url);

			mPost.addParameter("name", "Naked Sun");
			mPost.addParameter("author", "Issac Asimov");
			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/x-www-form-urlencoded");
			mtHeader.setName("accept");
			// mtHeader.setValue("application/xml");
			mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);
			client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString();
			mPost.releaseConnection();
			System.out.println("output : " + output);
		} catch (Exception e) {
			throw new Exception("Exception in adding bucket : " + e);
		}
	}

	public void addBookTwo() throws Exception {

		String output = null;
		try {
			String url = "http://192.168.0.131:8181/cxf/bookservice/addgateway";
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(url);

			BookVO bookVO = new BookVO();

			bookVO.setAuthor("Djorkeff Rodrigues");
			bookVO.setBookName("POO");

			Gson gson = new Gson();

			String jsonInString = gson.toJson(bookVO);

			System.out.println(">>>>>>>>>>>>>>>" + jsonInString);

			mPost.setRequestEntity(new StringRequestEntity(jsonInString, null, null));

			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/x-www-form-urlencoded");
			mtHeader.setName("accept");
			// mtHeader.setValue("application/xml");
			mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);
			client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString();
			mPost.releaseConnection();
			System.out.println("output : " + output);
		} catch (Exception e) {
			throw new Exception("Exception in adding bucket : " + e);
		}
	}


}
