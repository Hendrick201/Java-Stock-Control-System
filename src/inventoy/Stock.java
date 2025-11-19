package inventoy;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	private String stockName;
	private Integer stockId;
	private List<Product> products  = new ArrayList<Product>();
	
	public Stock()
	{
		
	}
	public Stock(String stockName, List<Stock> mainStockList) {
		this.stockName = stockName;	
		setStockId(mainStockList);
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public Integer getStockId() {
		return stockId;
	}
	private void setStockId(List<Stock> mainStockList)
	{
		if(mainStockList.size() > 0)
			this.stockId = mainStockList.size();
		else
			this.stockId = 0;
		// To-do adicionar logica pro Stock identificar o proprio Id dentro da lista de Stocks
	}
	public List<Product> getProductsList() {
		return products;
	}
	public void setProduct(Product product) {
		products.add(product);
		
	}
	public void removeProduct(Product product)
	{
		
	}
	public void searchProduct(String name)
	{
		
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Id:" + stockId + " - Stock Name: " + stockName);
		return sb.toString();
	}
}
