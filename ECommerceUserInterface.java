import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
//Carlos Carandang
//501033552
// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface {
	public static void main(String[] args) {
		// Create the system
		
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine()) {

			
			String action = scanner.nextLine();
			try {
			if (action == null || action.equals("")) {
				System.out.print("\n>");
				continue;
			} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS")) // List all products for sale
			{
				amazon.printAllProducts();
			} else if (action.equalsIgnoreCase("BOOKS")) // List all books for sale
			{
				amazon.printAllBooks();
			} else if (action.equalsIgnoreCase("CUSTS")) // List all registered customers
			{
				amazon.printCustomers();
			} else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();
			} else if (action.equalsIgnoreCase("SHIPPED")) // List all orders that have been shipped
			{
				amazon.printAllShippedOrders();
			} else if (action.equalsIgnoreCase("NEWCUST")) // Create a new registered customer
			{
				String name = "";
				String address = "";

				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();

				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();

				boolean success = amazon.createCustomer(name, address);
				if (!success) {
					System.out.println(amazon.getErrorMessage());
				}
			} else if (action.equalsIgnoreCase("SHIP")) // ship an order to a customer
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// Get order number from scanner
				if (scanner.hasNextLine()) {
					orderNumber = scanner.nextLine();
				}

				// Ship order to customer (see ECommerceSystem for the correct method to use
				// calls the shipOrder method
				if (amazon.shipOrder(orderNumber) == null) {
					System.out.println(amazon.getErrorMessage());
				} else {
					amazon.shipOrder(orderNumber);
				}

			} else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this
																// customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine()) {
					customerId = scanner.nextLine();
				}
				// If the customer Id is wrong, spit out an error message
				if (!amazon.printOrderHistory(customerId)) {
					System.out.println(amazon.getErrorMessage());
				}

			} else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
				// Get product Id from scanner
				if (scanner.hasNextLine()) {
					productId = scanner.nextLine();
				}
				System.out.print("\nCustomer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine()) {
					customerId = scanner.nextLine();
				}
				// Order the product. Check for valid orderNumber string return and for error
				// message set in ECommerceSystem
				String success = amazon.orderProduct(productId, customerId, "");
				if (success == null) {
					System.out.println(amazon.getErrorMessage());
				}
				// Print Order Number string returned from method in ECommerceSystem
				else {
					System.out.println(success);
				}
			} else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format
																// (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine()) {
					productId = scanner.nextLine();
				}
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine()) {
					customerId = scanner.nextLine();
				}
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book format and store in options string
				if (scanner.hasNextLine()) {
					options = scanner.nextLine();
					// check if options is a valid format
				}
				// Order product. Check for error mesage set in ECommerceSystem
				String success = amazon.orderProduct(productId, customerId, options);
				if (success == null) {
					System.out.println(amazon.getErrorMessage());
				}
				// Print order number string if order number is not null
				else {
					System.out.println(success);
				}

			} else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color
			{
				String productId = "";
				String customerId = "";
				String options = "";
				;

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine()) {
					productId = scanner.nextLine();
				}
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine()) {
					customerId = scanner.nextLine();
				}
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size
				if (scanner.hasNextLine()) {
					options = scanner.nextLine();
				}
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color
				if (scanner.hasNextLine()) {
					options = options + " " + scanner.nextLine();
					// System.out.println(options);
				}
				// order shoes
				String success = amazon.orderProduct(productId, customerId, options);
				if (success == null) {
					System.out.println(amazon.getErrorMessage());
				} else {
					System.out.println(success);
				}
			}

			// -----------------------ASSIGNMENT 2
			// COMMANDS--------------------------------------------
			else if (action.equalsIgnoreCase("ADDTOCART")) { // Adds a product to the customer’s cart
				String productId = "";
				String customerId = "";
				String productOptions = "";
				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine()) {
					productId = scanner.nextLine();
				}
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine()) {
					customerId = scanner.nextLine();
				}
				// need to check the product options, check the category, and apply the correct
				// product options
				System.out.print("Product Options (If valid): ");
				// get product id
				if (scanner.hasNextLine()) {
					productOptions = scanner.nextLine();
				}
				amazon.addtocart(productId, customerId, productOptions);
			}

			else if (action.equalsIgnoreCase("REMCARTITEM")) {// Removes a product from the customer’s cart
				String productId = "";
				String customerId = "";
				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine()) {
					productId = scanner.nextLine();
				}
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine()) {
					customerId = scanner.nextLine();
				}

				amazon.remcartitem(productId, customerId);

			} else if (action.equalsIgnoreCase("PRINTCART")) { // Prints all the products in the cart
				String customerId = "";
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine()) {
					customerId = scanner.nextLine();
				}

				amazon.printcart(customerId);
			} else if (action.equalsIgnoreCase("ORDERITEMS")) { // Prints all the products in the cart
				String customerId = "";
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine()) {
					customerId = scanner.nextLine();
				}
				// Need to make a method ORDERITEMS
				amazon.orderitems(customerId);
			} else if (action.equalsIgnoreCase("PRINTSTATS")) { // Prints all the products in the cart
				//do something 
				amazon.printstats();
			}
			// -------------------------------END OF A2
			// METHODS--------------------------------------------
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				if (scanner.hasNextLine()) {
					orderNumber = scanner.nextLine();
				}
				// cancel order. Check for error
				// If there is no order, send error message)
				if (amazon.cancelOrder(orderNumber) == false) {
					System.out.println(amazon.getErrorMessage());
				} else {
					amazon.cancelOrder(orderNumber);
					System.out.println(("Order " + orderNumber + " has been cancelled."));
				}
			} else if (action.equalsIgnoreCase("SORTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice();
			} else if (action.equalsIgnoreCase("SORTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName();
			} else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			System.out.print("\n>");
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
	}	
}
