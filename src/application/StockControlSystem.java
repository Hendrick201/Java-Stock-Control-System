package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entities.Product;
import entities.Stock;
import services.InputValidator;

public class StockControlSystem {

	List<Stock> stockList = new ArrayList<Stock>();

	public static void main(String[] args) {

		StockControlSystem system = new StockControlSystem();
		system.start();
	}

	private void start() {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		stockList = loadStockList(System.getProperty("user.dir"));
		while (true) {
			printMenu(sc);
		}
	}

	private void saveStockList(String path) {
		String finalPath = path + "\\inventory.json";
		Gson gson = new Gson();
		stockList.removeIf(Objects::isNull);
		String stockJson = gson.toJson(stockList);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(finalPath))) {
			bw.write(stockJson);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<Stock> loadStockList(String path) {
		String finalPath = path + "\\inventory.json";
		Gson gson = new Gson();
		try (BufferedReader br = new BufferedReader(new FileReader(finalPath))) {
			Type listType = new TypeToken<List<Stock>>() {
			}.getType();
			List<Stock> loadedList = gson.fromJson(br, listType);
			return (loadedList != null) ? loadedList : new ArrayList<Stock>();
		} catch (IOException e) {
			try {
				File file = new File(finalPath);
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return new ArrayList<Stock>();
		}

	}

	private void printMenu(Scanner sc) {
		System.out.println("-------------------------------- Stock Control System --------------------------------");
		System.out.println("Type any of the current keys below to navigate in the system");
		System.out.println();
		// TO-DO add a product
		// Out product
		System.out.println("1 -  Search a product");
		System.out.println("2 -  Create a product");
		System.out.println("3 -  Delete a product");
		System.out.println("4 -  Edit a product");
		System.out.println("5 -  List all products in a Stock");
		System.out.println("6 -  Create a new Stock");
		System.out.println("7 -  List all Stocks");
		System.out.println("8 -  Edit a Stock");
		System.out.println("9 -  Delet a Stock");
		int option = InputValidator.readInt(sc);

		switch (option) 
		{
		case 1:
			searchProduct(sc);
			break;
		case 2:
			createProduct(sc);
			break;
		case 3:
			deleteProduct(sc);
		case 4:
			editProduct(sc);
			break;
		case 5:
			listStockProducts(sc);
			break;
		case 6:
			stockList.add(createStock(sc));
			break;
		case 7:
			listStocks(sc);
			break;

		case 8:
			editStock(sc);
			break;
		case 9:
			deleteStock(sc);
			break;
		}
		saveStockList(System.getProperty("user.dir"));
	}

	private void deleteStock(Scanner sc) {
		Stock stock = selectStock(sc);
		stockList.remove(stock);
		System.out.println("Stock deleted successfully!");
		menuEndPointReturn(sc);
	}

	/// List - Stocks
	private void listStocks(Scanner sc) {
		stockList.stream().map(s -> s.toString()).forEach(System.out::println);
		menuEndPointReturn(sc);
	}

	/// List - Products
	private void listStockProducts(Scanner sc) {
		Stock stock = selectStock(sc);
		stock.getProductsList().stream().map(p -> p.toString()).forEach(System.out::println);
		menuEndPointReturn(sc);
	}

	/// Edit - Stock
	private void editStock(Scanner sc) {
		Stock stock = selectStock(sc);
		System.out.println("Enter the field number you wish to edit: \n 0 = NAME ");
		Integer choice = InputValidator.readInt(sc);
		switch (choice) {
		case 0:
			System.out.println("Enter the new Stock name");
			String newName = sc.nextLine();
			stock.setStockName(newName);
			System.out.println("Stock name changed successfully!");
			menuEndPointReturn(sc);
			break;
		}
	}

	/// Edit - Product
	private void editProduct(Scanner sc) {
		Product product = selectProduct(sc);
		System.out.println("Enter the field number you wish to edit: \n 0 = NAME \n 1 = PRICE \n 2 = QUANTITY");
		Integer choice = InputValidator.readInt(sc);
		switch (choice) {
		case 0:
			System.out.println("Enter the New Product Name");
			String productName = sc.nextLine();
			product.setProductName(productName);
			break;
		case 1:
			System.out.println("Enter the New Product Price");
			Double productPrice = InputValidator.readDouble(sc);
			product.setProductPrice(productPrice);
			break;
		case 2:
			System.out.println("Enter the New Product Quantity");
			Integer productQuantity = InputValidator.readInt(sc);
			product.setProductQuantity(productQuantity);
			break;
		}
		menuEndPointReturn(sc);
	}

	/// Delete - Product
	private void deleteProduct(Scanner sc) {
		if (stockList != null) {
			Stock stock = selectStock(sc);
			Product product = selectProduct(sc);
			if (stock.getProductsList().contains(product)) {
				stock.removeProduct(product);
				System.out.println("Product removed successfully!");
				menuEndPointReturn(sc);
			} else {
				System.out.println("Product not found");
				menuEndPointReturn(sc);
			}
		} else {
			System.out.println("Don't have any Stock in the system!");
			menuEndPointReturn(sc);
		}
	}

	/// Return - Menu
	private void menuEndPointReturn(Scanner sc) {
		System.out.println("\nPress ENTER to go back to the main menu");
		sc.nextLine();

	}

	/// Search - Product
	private void searchProduct(Scanner sc) {
		Product product = selectProduct(sc);
		if (product != null) {
			System.out.println("Product found");
			System.out.println();
			System.out.println(product.toString());
			menuEndPointReturn(sc);
		}
	}

	/// Select - Stock
	private Stock selectStock(Scanner sc) {
		if (stockList != null) {

			System.out.println("Select the Stock Id: ");
			for (Stock stock : stockList) {
				System.out.println(stock.toString());
			}
			Integer stockId = InputValidator.readInt(sc);
			if (stockId < 0 || (stockId > stockList.get(stockList.size() - 1).getStockId())) {
				System.out.println("Invalid Stock number! Please type a valid number.");
				return selectStock(sc);
			}
			return stockList.get(stockId);

		} else {
			System.out.println("Don't have any Stock in the system!");
			menuEndPointReturn(sc);
			return null;
		}
	}

	/// Select - Product
	private Product selectProduct(Scanner sc) {
		if (this.stockList.size() > 0) {
			Stock stock = selectStock(sc);

			if (stock.getProductsList().size() > 0) {
				System.out.println("Enter the product id: ");
				Integer productId = InputValidator.readInt(sc);
				for (Product product : stock.getProductsList()) {
					while (!product.getProductId().equals(productId)) {
						System.out.println("This product Id dont exists!\n");
						System.out.println("Enter the product id: ");
						productId = InputValidator.readInt(sc);
					}
					return product;
				}
			} else {
				System.out.println("Dont have any product in this Stock yet");
				menuEndPointReturn(sc);
				return null;

			}
		} else {
			System.out.println("Don't have any Stock in the system!");
			menuEndPointReturn(sc);
			return null;
		}
		return null;
	}

	/// Create - Product
	private void createProduct(Scanner sc) {
		if (stockList != null) {

			Stock stock = selectStock(sc);
			System.out.println("Enter the product name");
			String productName = sc.nextLine();
			Integer productId = stock.getProductsList().size(); // TO-DO adicionar uma forma de não repetir os ID
			System.out.println("Enter the product price: ");
			Double productPrice = InputValidator.readDouble(sc);
			while (productPrice <= 0) {
				System.out.println("Please, enter a value bigger than 0!");
				productPrice = InputValidator.readDouble(sc);
			}
			System.out.println("Enter the product quantity: ");
			Integer productQuantity = InputValidator.readInt(sc);
			while (productQuantity <= 0) {
				System.out.println("Please, enter a value bigger than 0!");
				productQuantity = InputValidator.readInt(sc);
			}
			System.out.println("Enter the product lot number: ");
			Integer productLotNumber = InputValidator.readInt(sc);
			while (productLotNumber <= 0) {
				System.out.println("Please, enter a value bigger than 0!");
				productLotNumber = InputValidator.readInt(sc);
			}
			stock.setProduct(
					new Product(productName, productId, productPrice, productQuantity, new Date(), productLotNumber));
			System.out.println("Product created successfully with Id: " + productId);
			menuEndPointReturn(sc);

		} else {
			System.out.println("Don't have any Stock in the system!");
			menuEndPointReturn(sc);
		}
	}

	/// Create - Stock
	private Stock createStock(Scanner sc) {
		System.out.println("Enter the Stock name:");
		String stockName = sc.nextLine();
		if (stockName.equals("")) {
			System.out.println("The Stock name cannot be null!");
			createStock(sc);
			return null;
		} else {
			Stock createdStock = new Stock(stockName, stockList);
			System.out.println("Stock created successfully! \n" + createdStock.toString());
			menuEndPointReturn(sc);
			return createdStock;
		}

	}

	private void deleteStock(Stock stock) {
		stockList.remove(stock);
	}

	private void addItemToStock(Stock stock, Product product) {
		stock.setProduct(product);
	}

	private void removeItemFromStock(Stock stock, Product product) {
		stock.removeProduct(product);
	}

}
