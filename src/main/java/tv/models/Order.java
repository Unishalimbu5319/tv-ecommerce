package tv.models;

import java.sql.Date;

public class Order {
	private int id;
	private String email;
	private Date orderDate;
    private String shippingInformation;
    private String orderStatus;
    private String paymentMethod;
    private String ccName;
    private String ccExpiration;
    private String ccNumber;
    private String ccCVV;
    private double amount;
    
    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getOrderDate() {
		return orderDate;
	}
    public Order(String email, Date orderDate, String shippingInformation, String orderStatus, String paymentMethod,
			String ccName, String ccExpiration, String ccNumber, String ccCVV, double amount) {
		this.orderDate = orderDate;
		this.shippingInformation = shippingInformation;
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.ccName = ccName;
		this.ccExpiration = ccExpiration;
		this.ccNumber = ccNumber;
		this.ccCVV = ccCVV;
		this.email = email;
		this.amount = amount;
	}

	public Order(int id, String email, Date orderDate, String shippingInformation, String orderStatus, String paymentMethod,
			String ccName, String ccExpiration, String ccNumber, String ccCVV, double amount) {
		this.id = id;
		this.orderDate = orderDate;
		this.shippingInformation = shippingInformation;
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.ccName = ccName;
		this.ccExpiration = ccExpiration;
		this.ccNumber = ccNumber;
		this.ccCVV = ccCVV;
		this.email = email;
		this.amount = amount;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcCVV() {
		return ccCVV;
	}

	public void setCcCVV(String ccCVV) {
		this.ccCVV = ccCVV;
	}

	public Order() {
    }
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShippingInformation() {
        return shippingInformation;
    }

    public void setShippingInformation(String shippingInformation) {
        this.shippingInformation = shippingInformation;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", shippingInformation='" + shippingInformation + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
