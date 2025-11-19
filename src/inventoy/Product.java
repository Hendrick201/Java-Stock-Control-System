package inventoy;

import java.util.Date;

public class Product {
	private String productName;
	private Integer productId;
	private Double productPrice;
	private Integer productQuantity;
	private Date productAddedDate;
	private Date productLastOutDate;
	private Integer productLotNumber;
	
	public Product(String productName, Integer productId, Double productPrice, Integer productQuantity,
			Date productAddedDate, Integer productLotNumber) {
		
		this.productName = productName;
		this.productId = productId;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.productAddedDate = productAddedDate;
		this.productLotNumber = productLotNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Date getProductAddedDate() {
		return productAddedDate;
	}

	public void setProductAddedDate(Date productAddedDate) {
		this.productAddedDate = productAddedDate;
	}

	public Date getProductLastOutDate() {
		return productLastOutDate;
	}

	public void setProductLastOutDate(Date productLastOutDate) {
		this.productLastOutDate = productLastOutDate;
	}

	public Integer getProductLotNumber() {
		return productLotNumber;
	}

	public void setProductLotNumber(Integer productLotNumber) {
		this.productLotNumber = productLotNumber;
	}
	public Double getProductTotalAmount()
	{
		return this.productPrice * this.productQuantity;
	}
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Product name: " + getProductName());
		sb.append("\n");
		sb.append("Product Id: " + getProductId());
		sb.append("\n");
		sb.append("Product price: " + getProductPrice());
		sb.append("\n");
		sb.append("Product quantity: " + getProductQuantity());
		sb.append("\n");
		sb.append("Product total amount in stock: " + getProductTotalAmount());
		sb.append("\n");
		sb.append("Product last added date: " + getProductAddedDate());
		sb.append("\n");
		sb.append("Product last out date: " + getProductLastOutDate());
		sb.append("\n");
		sb.append("Product lot number: " + getProductLotNumber());
		sb.append("\n");
		return sb.toString();
	}
}
