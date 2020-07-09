/*
Creates a collection of Seller objects from a data file,
and then searching for and printing those that sell a 
product meeting the user's criteria for quantity and price.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class MarketPlace {
   public static void main(String[] args) throws FileNotFoundException{
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the name of a \"Comma Separated Values\" (CSV) file:");
      
      try{
         Scanner file = new Scanner(new File(input.nextLine()));
         Currency rand = new Currency("R","ZAR",100);
         Seller[] sellers = new Seller[Integer.parseInt(file.nextLine())];
         
         if(sellers.length>0){
            String[] info;
            for(int i = 0;i<sellers.length;i++){
               info = file.nextLine().split("\\s*,\\s*");
               
               sellers[i] = new Seller();
   
               sellers[i].ID = info[0];
               sellers[i].name = info[1];
               sellers[i].location = info[2];
               sellers[i].product = info[3];
               sellers[i].unit_price = new Money(info[4],rand);
               sellers[i].number_of_units = Integer.parseInt(info[5]);
            }
            file.close();
         
            System.out.println("Enter the name of a product:");
            String productName = input.nextLine();
      
            System.out.println("Enter the number of units required:");
            int numUnits = input.nextInt();
              
            System.out.println("Enter the maximum unit price:");
            Money maxPrice = new Money(input.next(),rand);
            
            boolean foundOutput = false;
            for(int i = 0;i<sellers.length;i++){
               if(sellers[i].product.equals(productName) && sellers[i].number_of_units >= numUnits && maxPrice.compareTo(sellers[i].unit_price)>=0){
                  foundOutput = true;
                  System.out.println(sellers[i].ID +" : "+sellers[i].name +" in " + sellers[i].location+" has "+sellers[i].number_of_units+ " " + sellers[i].product + " for "+ sellers[i].unit_price +" each.");
               }
            }
            if(!foundOutput){
               System.out.println("None found.");
            }
         }else{
            System.out.println("The file contains no seller data.");
         }
      }catch(NoSuchElementException e){
         System.out.println("The file contains no seller data.");
      }finally{
         input.close();
      }
   }
}