import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

//Carlos Carandang
//501033552
/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {
  private Map<String, Product> products = new HashMap<String, Product>(); // String = Product Id, Product = Product
                                                                          // Object
  private ArrayList<Customer> customers = new ArrayList<Customer>();

  private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
  private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

  // For part f
  private Map<Product, Integer> product_orders = new HashMap<Product, Integer>();

  // private ArrayList<Shoes> shoeproducts = new ArrayList<Shoes>();

  // These variables are used to generate order numbers, customer id's, product
  // id's
  private int orderNumber = 500;
  private int customerId = 900;
  private int productId = 700;

  // General variable used to store an error message when something is invalid
  // (e.g. customer id does not exist)
  String errMsg = null;

  // Random number generator
  Random random = new Random();

  public ECommerceSystem() {

    //Create some customers. Notice how generateCustomerId() method is used
    customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
    customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
    customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
    customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));

    



    try {
      FileProducts("products.txt");
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }

    // FOR PART F
    for (String id : products.keySet()) {
      product_orders.put(products.get(id), 0);
    }
    /*
     * // Creating some Shoe products
     * // Note: Shoes take in parameters String name, String id, double price, int
     * // blackstock, int brownstock int size
     * products.add(new Shoes("Nike Dunks", generateProductId(), 300.0, 5, 5, 8));
     * products.add(new Shoes("Nike Waffle Ones", generateProductId(), 110.0, 100,
     * 50, 9));
     * products.add(new Shoes("Adidas Ultra Boost", generateProductId(), 240.0, 200,
     * 100, 10));
     */

  }

  private String generateOrderNumber() {
    return "" + orderNumber++;
  }

  private String generateCustomerId() {
    return "" + customerId++;
  }

  private String generateProductId() {
    return "" + productId++;
  }

  public String getErrorMessage() {
    return errMsg;
  }

  public void printAllProducts() {
    for (String id : products.keySet()) {
      products.get(id).print();
    }
  }

  // Print all products that are books. See getCategory() method in class Product
  public void printAllBooks() {
    for (String id : products.keySet()) {
      if (products.get(id).getCategory() == Product.Category.BOOKS) {
        products.get(id).print();

      }
    }
  }

  // Print all current orders
  public void printAllOrders() {
    for (ProductOrder a : orders) {
      a.print();
    }
  }

  // Print all shipped orders
  public void printAllShippedOrders() {
    for (ProductOrder p : shippedOrders) {
      p.print();
    }
  }

  // Print all customers
  public void printCustomers() {
    // Need to use Customer.java file for this
    for (Customer c : customers) {
      c.print();
    }
  }

  /*
   * Given a customer id, print all the current orders and shipped orders for them
   * (if any)
   */
  public boolean printOrderHistory(String customerId) throws RuntimeException { // for custorders method
    // Make sure customer exists - check using customerId
    // If customer does not exist, throw an error
    // see video for an appropriate error message string
    // ... code here

    // if the customer does not exist, print "Customer ____ Not Found"
    boolean exist = false;
    // Checks if the customer exists, if so, set exist to true
    for (Customer elements : customers) {
      if (elements.getId().equals(customerId)) {
        exist = true;
      }
    }
    // If exist is false, set errMsg and return false
    if (!exist) {
      throw new Unkowncust("Customer " + customerId + " Not Found");
    }

    // If the customer exists, do the following:
    // Print current orders of this customer

    System.out.println("\nCurrent Orders of Customer " + customerId);

    for (ProductOrder elements : orders) {
      // Will get the customer class from the product order, then will get the
      // customer's id
      if ((elements.getCustomer()).getId().equals(customerId)) {
        elements.print();
      }
    }

    // Print shipped orders of this customer
    System.out.println("\nShipped Orders of Customer " + customerId);
    for (ProductOrder elements : shippedOrders) {
      // Will get the customer class from the product order, then will get the
      // customer's id
      if ((elements.getCustomer()).getId().equals(customerId)) {
        elements.print();
      }
    }

    return true;

  }

  public String orderProduct(String productId, String customerId, String productOptions) { // For the orderproduct
    // First check to see if customer object with customerId exists in array list
    // customers
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Customer object

    // Checking to see if the customer id exists (taken from above)
    // If customer exists, set exist to true and put the customer object into a
    // variable, customername
    boolean exist = false;
    Customer customername = new Customer(customerId);
    for (Customer elements : customers) {
      if (elements.getId().equals(customerId)) {
        exist = true;
        customername = elements;
      }
    }
    // If exist is false, set errMsg and return null
    if (exist == false) {
      throw new Unkowncust("Customer " + customerId + " Not Found");
      // errMsg = ("Customer " + customerId + " Not Found");
      // return (null);
    }
    // Check to see if product object with productId exists in array list of
    // products
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Product object

    // Checking to see if the product id exists

    exist = false;
    Product productname = new Product();
    // If the product id given in the parameter exists in the map products,
    // put the product object into variable "productname"
    if (products.containsKey(productId)) {
      productname = products.get(productId);
    }
    // if productid does not exist in the map, throw an error
    else {
      throw new unknownprod("Product " + productId + " Not Found");
    }
    // Check if the options are valid for this product (e.g. Paperback or Hardcover
    // or EBook for Book product)
    // See class Product and class Book for the method vaidOptions()
    // If options are not valid, throw error

    // check to see if the product has a valid productoption
    if (productname.validOptions(productOptions)) {
    }
    // if the product is not a valid product throw error
    else {
      throw new Invalidproductoptions("Not a valid Option");
    }

    // Check if the product has stock available (i.e. not 0)
    // See class Product and class Book for the method getStockCount()
    // If no stock available, set errMsg string and return null

    // If there is no stock left, throw error
    if (productname.getStockCount(productOptions) <= 0) {
      throw new prodoutofstock("No Stock Available");
    }
    // errMsg = "No Stock Available";
    // return (null);

    // Create a ProductOrder, (make use of generateOrderNumber() method above)
    // reduce stock count of product by 1 (see class Product and class Book)
    // Add to orders list and return order number string

    // Creating a new order number for the new order
    String orderNumber = generateOrderNumber();
    // Adding the new order to the orders array
    orders.add(new ProductOrder(orderNumber, productname, customername, productOptions));
    productname.reduceStockCount(productOptions);

    // adds the product name to part f

    stats(productname);

    return ("Order Number: " + orderNumber);
  }

  /*
   * Create a new Customer object and add it to the list of customers
   */

  public boolean createCustomer(String name, String address) {
    // Check name parameter to make sure it is not null or ""
    // If it is not a valid name, set errMsg (see video) and return false
    // Repeat this check for address parameter

    if (name == null || name == "") {
      throw new invalidcustname("Invalid Customer Name");

      // errMsg = "Invalid Customer Name";
      // return false;
    }
    if (address == null || address == "") {
      throw new invalidcustaddress("Invalid customer address");

      // errMsg = "Invalid customer address";
      // return false;
    }
    // Create a Customer object and add to array list
    customers.add(new Customer(generateCustomerId(), name, address));
    return true;
  }

  public ProductOrder shipOrder(String orderNumber) { // for ship order
    // Check if order number exists first. If it doesn't, set errMsg to a message
    // (see video)
    // and return false
    // Retrieve the order from the orders array list, remove it, then add it to the
    // shippedOrders array list
    // return a reference to the order

    for (ProductOrder element : orders) {
      if (element.getOrderNumber().equals(orderNumber)) {
        orders.remove(element);
        shippedOrders.add(element);
        return (element);
      }
    }
    
    throw new invalidordernumber("Order " + orderNumber + " Not Found");
    // errMsg = ("Order " + orderNumber + " Not Found");
    // return (null);
  }

  /*
   * Cancel a specific order based on order number
   */
  public boolean cancelOrder(String orderNumber) throws RuntimeException {
    // Check if order number exists first. If it doesn't, set errMsg to a message
    // (see video)
    // and return false
    // Checks if the order number exists
    for (ProductOrder element : orders) {
      if (element.getOrderNumber().equals(orderNumber)) {
        orders.remove(element);
        return (true);

      }
    }
    // If not, do the following
    throw new invalidordernumber("Order " + orderNumber + " Not Found");

    // errMsg = ("Order " + orderNumber + " Not Found");
    // return (false);

  }

  // Sort products by increasing price
  public void sortByPrice() {
    ArrayList<Product> temp = new ArrayList<Product>();
    for (String id : products.keySet()) {
      temp.add(products.get(id));
    }
    Collections.sort(temp, new ProductPriceComparator());
    for (Product item : temp) {
      item.print();
    }
  }

  // Sort products alphabetically by product name
  public void sortByName() {
    ArrayList<Product> temp = new ArrayList<Product>();
    for (String id : products.keySet()) {
      temp.add(products.get(id));
    }
    Collections.sort(temp);
    for (Product item : temp) {
      item.print();
    }
  }

  // Sort products alphabetically by product name
  public void sortCustomersByName() {
    Collections.sort(customers);
    printCustomers();
  }

  // This class is used to compare the prices of the products in array list
  // products
  private class ProductPriceComparator implements Comparator<Product> {
    public int compare(Product prod1, Product prod2) {
      if (prod1.getPrice() < prod2.getPrice()) {
        return -1;
      } else if (prod1.getPrice() > prod2.getPrice()) {
        return 1;
      }
      return 0;
    }
  }

  // -------------------------ASSIGNMENT 2
  // METHODS----------------------------------
  // Do printcart first, addtocart next, remcartitem, then orderitems

  // Prints all items in customer's cart
  public void printcart(String customerId) {
    boolean exist = false;
    for (Customer custcart : customers) {
      // check if the id is the same as customerId
      if (custcart.getId().equals(customerId)) {
        Cart cart = custcart.getcart();
        exist = true;
        cart.printcart();
      }
    }
    // if there is no customer which related to customerId, throw error
    if (exist == false) {
      throw new Unkowncust("Unknown Customer ID");
    }
  }

  public void addtocart(String productId, String customerId, String productOptions) {
    boolean exist = false;
    for (Customer cust : customers) {
      // check if the id is the same as customerId
      if (cust.getId().equals(customerId)) {
        exist = true;
        // check if the product id exists
        if (products.containsKey(productId)) {
          Product desired_item = products.get(productId);
          // check if productOptions parameter is valid
          if (!desired_item.validOptions(productOptions)) {
            throw new Invalidproductoptions("Not a valid Option");
          }
          // if both are satisfied add the product to the cart
          CartItem custproduct = new CartItem(desired_item, productOptions);
          cust.add(custproduct);
        }
        // if product id does not exist, throw unknown product error
        else {
          throw new unknownprod("Unknown product ID");
        }
      }
    }

    // if customer id is not valid, throw error
    if (exist == false) {
      throw new Unkowncust("Unknown Customer ID");
    }

  }

  public void remcartitem(String productId, String customerId) {
    boolean exist = false;
    for (Customer cust : customers) {
      // check if the id is the same as customerId
      if (cust.getId().equals(customerId)) {
        exist = true;

        // check if the product id exists
        if (products.containsKey(productId)) {
          Product desired_item = products.get(productId);
          // if both are satisfied remove the product from the customer's cart
          CartItem custproduct = new CartItem(desired_item, "");
          cust.remove(custproduct);
        }
        // if product id does not exist, throw unknown product error
        else {
          throw new unknownprod("Unknown product ID");
        }
      }
    }
    // if customer id is not valid, throw error
    //
    if (exist == false) {
      throw new Unkowncust("Unknown Customer ID");
    }

  }

  public void orderitems(String customerId) {
    boolean exist = false;
    for (Customer cust : customers) {
      // check if the id is the same as customerId
      if (cust.getId().equals(customerId)) {
        exist = true;
        // if so, get the customer's shopping cart
        Cart custCart = cust.getcart();

        // Take each element from the array list items and order the product
        for (CartItem products : custCart.getshoppingcart()) {
          String the_product = products.getproduct().getId();
          orderProduct(the_product, customerId, products.getprodoptions());
        }
        custCart.getshoppingcart().clear();
      }
    }
    // throw error is customerid not real
    if (exist == false) {
      throw new Unkowncust("Unknown Customer ID");
    }

  }

  // Part D

  // The first line is the product category. The
  // 2nd line is the product name, the 3rd line is the price, the 4th line
  // contains
  // stock count information.5th line contains
  // additional product information.
  private void FileProducts(String filename) throws FileNotFoundException {
    File file = new File(filename);
    Scanner line = new Scanner(file);
    line.useDelimiter("\n");
    while (line.hasNext()) {
      String category = line.nextLine();// Category
      String name = line.nextLine();// Product Name
      String price = line.nextLine();// Price
      String stock = line.nextLine();// Stock Count
      String prodinfo = line.nextLine();// Product Information (if applicable)

      if (name.equals("Book")) {// checks to see if the item is a book (has additional product information)
        String[] stocks = stock.split(" "); // splits the stock of paperback/hardcover up
        String[] title_author = prodinfo.split(":"); // splits up information of the book details
        // Book(String name, String id, double price, int paperbackStock, int
        // hardcoverStock, String title, String author)

        // adding a book to arraylist products
        Product book = new Book(name, generateProductId(), Double.valueOf(price), Integer.valueOf(stocks[0]),
            Integer.valueOf(stocks[1]), title_author[0], title_author[1]);
        this.products.put(book.getId(), book);
      } else {// if its not a book, do the following
        // public Product(String name, String id, double price, int stock, Category
        // category)
        Product item = new Product(name, generateProductId(), Double.valueOf(price), Integer.valueOf(stock),
            Product.Category.valueOf(category));
        this.products.put(item.getId(), item);
      }
      // products.add(new Product(name, id, price, stock,
      // Product.Category.valueOf(category)));
    }
  }

  // Part F
  public void stats(Product prod) {
    // private Map<Product, Integer> product_orders = new HashMap<Product,
    // Integer>();
    // Update the order by 1
    product_orders.put(prod, this.product_orders.get(prod) + 1);
  }

  public void printstats() {
    // sort out the hashmap
    // new array list
    // print out the statistics
    ArrayList<String> formatstats = new ArrayList<String>();
    for (Product prod : product_orders.keySet()) {
      String line = ("" + product_orders.get(prod) + "-" + prod.getName() + "-" + prod.getId());
      formatstats.add(line);
      // # of items orders, name of product,
    }
    // sorting formatstats
    Collections.sort(formatstats, Collections.reverseOrder());
    for (String line : formatstats) {
      String[] temp = line.split("-");
      System.out.println("Product Name: " + temp[1] + "Product ID: "+ temp[2] + "# of times ordered: " + temp[0]);
    }
    for(Product p :product_orders.keySet()){

    }
  }
  // EXCEPTIONS:

  

  class Unkowncust extends RuntimeException {
    public Unkowncust() {
    }

    public Unkowncust(String e) {
      super(e);
    }
  }

  class unknownprod extends RuntimeException {
    public unknownprod() {
    }

    public unknownprod(String e) {
      super(e);
    }
  }

  class Invalidproductoptions extends RuntimeException {
    public Invalidproductoptions() {
    }

    public Invalidproductoptions(String e) {
      super(e);
    }
  }

  class prodoutofstock extends RuntimeException {
    public prodoutofstock() {
    }

    public prodoutofstock(String e) {
      super(e);
    }
  }

  class invalidcustname extends RuntimeException {
    public invalidcustname() {
    }

    public invalidcustname(String e) {
      super(e);
    }
  }

  class invalidcustaddress extends RuntimeException {
    public invalidcustaddress() {
    }

    public invalidcustaddress(String e) {
      super(e);
    }
  }

  class invalidordernumber extends RuntimeException {
    public invalidordernumber() {
    }

    public invalidordernumber(String e) {
      super(e);
    }
  }
}