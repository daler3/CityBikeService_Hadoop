package primo;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.*;


public class CsvReaderBike {
		
	public CsvReaderBike(){
		CsvReader products;
		try {
			products = new CsvReader("Dati_Csv/201501-citibike-tripdata.csv");
			products.readHeaders();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}
	
	public void getField(){
		 try {
	
	         CsvReader products = new CsvReader("Dati_Csv/201501-citibike-tripdata.csv");
	
	         products.readHeaders();
	
	         while (products.readRecord())
	         {
	        	 //fai tutti i singoli metodi
	             String productID = products.get("ProductID");
	             String productName = products.get("ProductName");
	             String supplierID = products.get("SupplierID");
	             String categoryID = products.get("CategoryID");
	             String quantityPerUnit = products.get("QuantityPerUnit");
	             String unitPrice = products.get("UnitPrice");
	             String unitsInStock = products.get("UnitsInStock");
	             String unitsOnOrder = products.get("UnitsOnOrder");
	             String reorderLevel = products.get("ReorderLevel");
	             String discontinued = products.get("Discontinued");
	
	             // perform program logic here
	             System.out.println(productID + ":" + productName);
	         }
	
	         products.close();
	
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException i) {
	         i.printStackTrace();
	     }
	}
	

}
