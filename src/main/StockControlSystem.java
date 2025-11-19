package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import inventory.Product;
import inventory.Stock;

public class StockControlSystem {

	List<Stock> stockList = new ArrayList<Stock>();
	public static void main(String[] args) {
		
		StockControlSystem system = new StockControlSystem();
		system.start();
	
	}
	
	
	private void start()
	{
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		StockControlSystem system = new StockControlSystem();
		
		//Criando stock e produto para teste
		stockList.add(new Stock("Main Stock",stockList)); 
		stockList.add(new Stock("Stock 2",stockList)); 
		////////////////////////////////
		printMenu(sc);
		sc.close();
	}
	
	 private void printMenu(Scanner sc)
	{
		System.out.println("-------------------------------- Stock Control System --------------------------------");
		System.out.println("Type any of the current keys below to navigate in the system");
		System.out.println();
		System.out.println("1 -  Search a product");
		System.out.println("2 -  Create a product");
		System.out.println("3 -  Delete a product");
		System.out.println("4 -  Edit a product");
		System.out.println("5 -  List all products in a Stock");
		System.out.println("6 -  Create a new Stock");
		System.out.println("7 -  List all Stocks");
		System.out.println("8 -  Edit a Stock");
		System.out.println("9 -  Delet a Stock");
		int option = Integer.parseInt(sc.nextLine());
				
		switch(option)  // TO-DO adicionar todos os cases do menu aqui
			{
			case 1: searchProduct(sc); break;
			case 2: createProduct(sc); break;
			}
	}
	 
	 
	private void menuEndPointReturn(Scanner sc)
	{
		System.out.println("\n Enter any key to go back to the main menu");
		if(sc.nextLine() != null)
		{
			printMenu(sc);
			sc.nextLine(); // clear the input buffer
		}
			
	}
	
	
	private void searchProduct(Scanner sc)
	{

		if(this.stockList.size() > 0)
		{
			Integer productId = null;
			System.out.println("Enter the product id: ");
			productId = readInt(sc);
			boolean success = false;
			for(Stock stock : stockList)
			{
				if(stock.getProductsList().size()  > 0)
				{
					for(Product product : stock.getProductsList())
					{
						if(product.getProductId().equals(productId))
						{
							System.out.println("Product found");
							System.out.println();
							System.out.println(product.toString());
							success = true;
							menuEndPointReturn(sc);
						}
					}
				}
				else
				{
					System.out.println("Dont have any product in this Stock yet");
					menuEndPointReturn(sc);
					return;
				}
				
			}
			if(!success)
			{
				System.out.println("Product not found");
				printMenu(sc);
			}
		}
		else
		{
			System.out.println("Don't have any Stock in the system!");
			menuEndPointReturn(sc);
		}

	}
	
	private void createProduct(Scanner sc)
	{
		System.out.println("Select the Stock Id: ");
		for(Stock stock : stockList)
		{
			System.out.println(stock.toString());
		}
		Integer stockId = null;
		stockId = readInt(sc);
		if(stockId < 0 || (stockId > stockList.get(stockList.size() -1).getStockId()))
		{
			System.out.println("Invalid number! Please type a valid number.");
			createProduct(sc);
		}
		System.out.println("Enter the product name");
		String productName = sc.nextLine();
		Integer productId = stockList.get(stockId).getProductsList().size();
		System.out.println("Enter the product price: ");
		Double productPrice = null;
		productPrice = readDouble(sc);
		System.out.println("Enter the product quantity: ");
		Integer productQuantity = null;
		productQuantity = readInt(sc);
		System.out.println("Enter the product lot number: ");
		Integer productLotNumber = null;
		productLotNumber = readInt(sc);
		
		stockList.get(stockId).setProduct(new Product(productName,productId,productPrice,productQuantity,new Date(),productLotNumber));
		System.out.println("Product created successfully with Id: " + productId);
		menuEndPointReturn(sc);
	}
	
	private Stock createStock(String stockName)
	{
		return new Stock(stockName,stockList);
	}
	
	private void deleteStock(Stock stock)
	{
		stockList.remove(stock);
	}
	
	private void addItemToStock(Stock stock, Product product)
	{
		stock.setProduct(product);
	}
	
	private void removeItemFromStock(Stock stock, Product product)
	{
		stock.removeProduct(product);
	}
	
	private static int readInt(Scanner sc)
	{
		 try {
		        return Integer.parseInt(sc.nextLine());
		    } catch (NumberFormatException e) {
		        System.out.println("Invalid number! Please type a valid number.");
		        return readInt(sc); 
		    }
	}
	private static Double readDouble(Scanner sc)
	{
		 try {
		        return Double.parseDouble(sc.nextLine());
		    } catch (NumberFormatException e) {
		        System.out.println("Invalid value! Please type a valid number.");
		        return readDouble(sc); 
		    }
	}
}
