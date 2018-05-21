package com.hello.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class CrawlerServiceImpl {

	
	public Connection connection;
	public String lastStockid;
	public List<String>  stockids = new ArrayList<String>();
		
	public CrawlerServiceImpl(){
	
		
	
		
		//lastStockid = "";
		
	    LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

	    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
	    java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
	    
	    
		String url = "jdbc:mysql://172.16.2.39:3306/test";
		String username = "heika_dev";
		String password = "heika_dev@qwe321";

		System.out.println("Connecting database...");

		try {
			
			       connection = (Connection) DriverManager.getConnection(url, username, password);
				    System.out.println("Database connected!");
		}
	    catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	    

	}
	
	public void closeMysql()
	{
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertMysql (JSONArray data, String stockid,String stockName,String year)
	{
		
		// step1:  insert stock id if stock id is not exist
		Statement stmt = null;
		//ResultSet rs = null;
		

		// lastStockid=stockid;
		
		
		if(!stockids.contains(stockid))	  {
			
			stockids.add(stockid);
		
		try {
		    stmt = (Statement) connection.createStatement();
		    System.out.println("insert into stock (stockid,stockName) values ('"+stockid+"','"+stockName+"'); ");
		   Boolean rs = stmt.execute("insert into stock (stockid,stockName) values ('"+stockid+"','"+stockName+"'); ");
            
		    // Now do something with the ResultSet ....
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		}
		
		// step2:  insert data

		// INSERT INTO table_name (stockid,year,date,initvalue,endvalue,marginamount,marginpercentage,low,high,dealamount,dealvalue,dealpercentage)  VALUES (value1,value2,value3,...);
		
		int jsonArrayLength = data.size();
		
		String values = "'"+stockid+"','"+stockName+"','"+year+"',";
		
		for(int i = 0 ; i<jsonArrayLength ; i++)
		{
			if(i!=jsonArrayLength-1)
			values += "'"+data.get(i).toString().replace("%", "")+"',";
			
			else
			values += "'"+data.get(i).toString().replace("%", "")+"'";
			
		}
		

		System.out.println();
		
		try {
		    stmt = (Statement) connection.createStatement();
		    //System.out.println("insert into stockHisData (stockid,stockName,year,date,initvalue,endvalue,marginamount,marginpercentage,low,high,dealamount,dealvalue,dealpercentage) values ("+values+"); ");
			Boolean rs = stmt.execute(
					"insert into stockHisData (stockid,stockName,year,date,initvalue,endvalue,marginamount,marginpercentage,low,high,dealamount,dealvalue,dealpercentage) values ("
							+ values + "); ");
            
		    // Now do something with the ResultSet ....
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
//		  if (rs != null) {
//		        try {
//		            rs.close();
//		        } catch (SQLException sqlEx) { } // ignore
//
//		        rs = null;
//		    }

	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException sqlEx) { } // ignore

	        stmt = null;
	    }		
	
		
	}
	
	public void extractInfo(String content, String stockid,String stockName, int year) 
	{
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(content);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray hq = (JSONArray) jsonObject.get("hq");
			
			if(null == hq) {System.out.println("no data get"); }
			Iterator<JSONArray> iterator = hq.iterator();
			while (iterator.hasNext()) {
				JSONArray data = iterator.next();
				insertMysql(data,stockid,stockName,String.valueOf(year));
				System.out.println(data);
			}
			
			
		} catch (ParseException e) {
			System.out.println("no data get");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
	}
	
	public String getHistoryData( String Stockid,String stockName,String start, String end, int year) throws ClientProtocolException, IOException 
	{
		
		String dataUrl = "http://q.stock.sohu.com/hisHq?code=cn_"+Stockid+"&start="+start+"&end="+end+"&stat=1&order=D&period=m&callback=historySearchHandler&rt=jsonp&r=0.2960665541393579&0.013583457760491191";
		
		System.out.println(dataUrl);
		String returnJson = Request.Get(dataUrl).execute().returnContent().asString();
		
		returnJson = returnJson.replace("historySearchHandler([", "");
		returnJson = returnJson.replace("])", "");
		//returnJson = returnJson.replace("historySearchHandler(", "");
		//System.out.println("===== start from "+start+" to "+end);
		//System.out.println(returnJson);
		
		//resolve return json data
		extractInfo(returnJson,Stockid,stockName,year);
				
		return returnJson;
				
	}
	
	
	public String getGupiaoId(URL url) throws FailingHttpStatusCodeException, IOException 
	
	{
	       final WebClient webClient = new WebClient();
	       webClient.getOptions().setThrowExceptionOnScriptError(false);
	        final HtmlPage page = webClient.getPage(url);
	       // Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	        final String pageAsXml = page.asXml();
	        
	       // System.out.println(pageAsXml);
	        //Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

	       //*[@id="BIZ_MS_pllist"]/tbody/tr/td[2]/a
	        
	        final List< HtmlTableCell> tocketId = (List<HtmlTableCell>) page.getByXPath("//*[@id=\"BIZ_MS_plstock\"]/tbody/tr/td[1]");
	        
	        final List< HtmlTableCell> tocketName = (List<HtmlTableCell>) page.getByXPath("//*[@id=\"BIZ_MS_plstock\"]/tbody/tr/td[2]");
	        
	      //*[@id="BIZ_MS_plstock"]/tbody/tr[1]/td[2]/a
	        
	        int index=0;
	        
	        for (final HtmlTableCell td : tocketId) {
	            // Here your room is available	        	
	        	//
	        
	        	//System.out.println(td.asText());
	        	//String template = "600381";
	        	
	        	System.out.println(stockids);
	        	System.out.println(td.asText());
	        	if(!stockids.contains(td.asText()))	  {
	        		
	        	int startyear= 1991;
		        int nowyear = Calendar.getInstance().get(Calendar.YEAR);
	        	for(;startyear<=nowyear;startyear++)
	        	{
	        	String startdate=String.valueOf(startyear)+"0101";
	        	String enddate=String.valueOf(startyear)+"1231";
	        	     	
	        	getHistoryData(td.asText(),tocketName.get(index).asText(),startdate,enddate,startyear);
	        	}
	        	
	        	index++;
	        	}
	        	//break;
	        }
	        
	        webClient.close();	   		        
	        return pageAsXml;		
		
	}
	
	
	public String getGroups(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException
	{
		
	final WebClient webClient = new WebClient();
	webClient.getOptions().setThrowExceptionOnScriptError(false);
	webClient.setJavaScriptTimeout(6000);
	
	        final HtmlPage page = webClient.getPage(url);
	       // Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	        final String pageAsXml = page.asXml();
	        
	        System.out.println(pageAsXml);
	        //Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
	      
	        
	      //*[@id="BIZ_MS_pllist"]/tbody/tr/td[2]/a
	      //*[@id="BIZ_MS_pllist"]/tbody/tr[1]/td[2]/a
	        final List< HtmlAnchor> links = (List<HtmlAnchor>) page.getByXPath("//*[@id=\"allSector\"]/table/tbody/tr/td[2]/a");
	        
	        System.out.println(links.size());
	        
	        //final List< HtmlAnchor> links = (List<HtmlAnchor>) page.getByXPath("//*div[@id=\"allSector\"]/table[@id=\"BIZ_MS_pllist\"]/tbody/tr/td[2]/a");
	        for (final HtmlAnchor link : links) {
	            // Here your room is available	        	
	        	//
	        	//System.out.println(HtmlAnchor.getTargetUrl(link.getHrefAttribute(), page));
	        	System.out.println(link.asText());
	        	getGupiaoId(HtmlAnchor.getTargetUrl(link.getHrefAttribute(), page));
	        	
	        	Thread.sleep(300);
	        	//break;
	        }
	   		webClient.close();
	        return pageAsXml;
	        
	}
	
	
	public static void main(String[] args)
	{
		
		//http://q.stock.sohu.com/cn/000862/lshq.shtml
		//http://q.stock.sohu.com/hisHq?code=cn_600381&start=20160804&end=20161205&stat=1&order=D&period=w&callback=historySearchHandler&rt=jsonp&r=0.2960665541393579&0.013583457760491191
		CrawlerServiceImpl grab = new CrawlerServiceImpl();
		try {
			grab.getGroups("http://q.stock.sohu.com/cn/bk.shtml");
		} catch (FailingHttpStatusCodeException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		
		grab.closeMysql();
	}
	
}
