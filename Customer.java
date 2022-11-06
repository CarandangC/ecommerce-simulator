import java.util.ArrayList;

//Carlos Carandang
//501033552
/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address. 
 *  A unique id is generated when when a new customer is created. 
 *  
 *  Implement the Comparable interface and compare two customers based on name
 */
public class Customer implements Comparable<Customer> {
	private String id;
	private String name;
	private String shippingAddress;
	private Cart shoppingCart;

	public Customer(String id) {
		this.id = id;
		this.name = "";
		this.shippingAddress = "";
		this.shoppingCart = new Cart();
	}

	public Customer(String id, String name, String address) {
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		this.shoppingCart = new Cart();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public void print() {
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}

	public boolean equals(Object other) {
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}

	public int compareTo(Customer other) {
		Customer otherCust = (Customer) other;
		if (this.name.compareTo(other.name) > 0) {
			return 1;
		} else if (this.name.compareTo(other.name) == 0) {
			return 0;
		}
		return (-1);
	}

	// ---------------------ASSIGNMENT 2 METHODS---------------------
	public Cart getcart() {
		return (this.shoppingCart);
	}

	public void add(CartItem item) {
		this.shoppingCart.addtocart(item);
	}

	public void remove(CartItem item) {
		this.shoppingCart.removefromcart(item);
	}
}
