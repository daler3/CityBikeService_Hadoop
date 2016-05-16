package primo;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.*;


public class CsvReaderBike {
	
	 try {

         CsvReader products = new CsvReader("products.csv");

         products.readHeaders();

         while (products.readRecord())
         {
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
         e.printStackTrace();
     }
	

}
