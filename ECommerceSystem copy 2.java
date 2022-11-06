import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
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
  private ArrayList<Product> products = new ArrayList<Product>();
  private ArrayList<Customer> customers = new ArrayList<Customer>();

  private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
  private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

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

  public ECommerceSystem(){
    // NOTE: do not modify or add to these objects!! - the TAs will use for testing
    // If you do the class Shoes bonus, you may add shoe products

    // Create some products. Notice how generateProductId() method is used
    // products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
    // products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
    // products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney"));
    // products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
    // products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
    // products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
    // products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast"));
    // products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive"));
    // products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney"));
    // products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));

    // Create some customers. Notice how generateCustomerId() method is used
    customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    
    try {FileProducts("products.txt");}
    catch (FileNotFoundException e){
      System.out.println(e.getMessage());
    }
    // Creating some Shoe products
    // Note: Shoes take in parameters String name, String id, double price, int
    // blackstock, int brownstock int size
    products.add(new Shoes("Nike Dunks", generateProductId(), 300.0, 5, 5, 8));
    products.add(new Shoes("Nike Waffle Ones", generateProductId(), 110.0, 100,
    50, 9));
    products.add(new Shoes("Adidas Ultra Boost", generateProductId(), 240.0, 200,
    100, 10));
    /*
    try{products = FileProducts("products.txt");}
    catch(IOException e){
      System.out.println(e.getMessage());
    }
    catch(FileNotFoundException e){
      System.out.println(e.getMessage());

    }
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
    for (Product p : products)
      p.print();
  }

  // Print all products that are books. See getCategory() method in class Product
  public void printAllBooks() {
    for (Product b : products) {
      if (b.getCategory() == Product.Category.BOOKS) {
        b.print();

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
    // If the product id given in the parameter exists in the array list products,
    // set exist to true and put the product object into variable "productname"
    for (Product compare : products) {
      if (compare.getId().equals(productId)) {
        exist = true;
        productname = compare;
      }
    }

    // If exist = false, then the productId of the object does not exist, therefore
    // set errg msg and return null
    if (exist == false) {
      throw new unknownprod("Product " + productId + " Not Found");
      // errMsg = ("Product " + productId + " Not Found");
      // return (null);
    }
    // Check if the options are valid for this product (e.g. Paperback or Hardcover
    // or EBook for Book product)
    // See class Product and class Book for the method vaidOptions()
    // If options are not valid, throw error

    // First look for the product with the given productid,
    for (Product b : products) {
      if (b.getId().equals(productId)) {
        // check to see if the product is a valid product
        if (b.validOptions(productOptions)) {
        }
        // if the product is not a valid product throw error
        else {
          throw new Invalidproductoptions("Not a valid Option");
          // errMsg = "Not a valid Option";
          // return (null);
        }
      }
    }

    // Check if the product has stock available (i.e. not 0)
    // See class Product and class Book for the method getStockCount()
    // If no stock available, set errMsg string and return null

    // If there is no stock left, throw error
    if (productname.getStockCount(productOptions) <= 0) {
      throw new prodoutofstock("No Stock Available");

      // errMsg = "No Stock Available";
      // return (null);
    }

    // Create a ProductOrder, (make use of generateOrderNumber() method above)
    // reduce stock count of product by 1 (see class Product and class Book)
    // Add to orders list and return order number string

    // Creating a new order number for the new order
    String orderNumber = generateOrderNumber();
    // Adding the new order to the orders array
    orders.add(new ProductOrder(orderNumber, productname, customername, productOptions));
    productname.reduceStockCount(productOptions);
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
    for (ProductOrder element : orders)
      if (element.getOrderNumber().equals(orderNumber)) {
        orders.remove(element);
        shippedOrders.add(element);
        return (element);
      }
    // Need to return a reference to the order
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
        return true;
      }
    }
    // If not, do the following
    throw new invalidordernumber("Order " + orderNumber + " Not Found");

    // errMsg = ("Order " + orderNumber + " Not Found");
    // return (false);

  }

  // Sort products by increasing price
  public void sortByPrice() {
    Collections.sort(products, new ProductPriceComparator());
  }

  // Sort products alphabetically by product name
  public void sortByName() {
    Collections.sort(products);
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

    for (Customer custcart : customers) {
      // check if the id is the same as customerId
      if (custcart.getId().equals(customerId)) {
        Cart cart = custcart.getcart();
        cart.printcart();
      }
    }
  }

  public void addtocart(String productId, String customerId, String productOptions) {
    for (Customer cust : customers) {
      // check if the id is the same as customerId
      if (cust.getId().equals(customerId)) {
        // check if the product id exists
        
        for (Product item : products) {
          if (item.getId().equals(productId)) {
            Product desired_item = item;
            //check if productOptions parameter is valid
            if (!desired_item.validOptions(productOptions)){
              throw new Invalidproductoptions("Not a valid Option");
            }
            // if both are satisfied add the product to the cart
            CartItem custproduct = new CartItem(desired_item, productOptions);
            cust.add(custproduct);
          }
        }
      }
    }
    // if product id and/or customer id is not valid, do something:
    //
  }

  public void remcartitem(String productId, String customerId) {

    for (Customer cust : customers) {
      // check if the id is the same as customerId
      if (cust.getId().equals(customerId)) {
        // check if the product id exists
        for (Product item : products) {

          if (item.getId().equals(productId)) {
            Product desired_item = item;
            // if both are satisfied remove the product from the customer's cart
            CartItem custproduct = new CartItem(desired_item, "");
            cust.remove(custproduct);
          }
        }
      }
    }
    // if product id and/or customer id is not valid, do something:
    //
  }

  public void orderitems(String customerId) {
    for (Customer cust : customers) {
      // check if the id is the same as customerId
      if (cust.getId().equals(customerId)) {
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
  }

  // Part D
  /*private ArrayList FileProducts(String filename) throws IOException {
    File file = new File(filename);
    Scanner line = new Scanner(file);
    ArrayList<Product> products = new ArrayList<Product>();
    String item = "";
    Scanner readitem = new Scanner(item);

    while (line.hasNextLine()) {
      // reads the 5 lines in a time
      for (int i = 0; i < 5; i++) {
        item = (item + line.nextLine() + "\n");
        line.nextLine();
      }
      // reads through line, and assigns to correct parameters for product, then
      // clears item string
      String category = readitem.nextLine();
      String name = (readitem.nextLine());
      double price = Double.valueOf(readitem.nextLine());
      int stock = Integer.valueOf(readitem.nextLine());
      String id = generateCustomerId();
      products.add(new Product(name, id, price, stock, Product.Category.valueOf(category)));
      //System.out.println(category + name + price + stock + id);
      item = "";
    }
    line.close();
    readitem.close();
    return products;
  }
  
}
*/
//The first line is the product category. The 
//2nd line is the product name, the 3rd line is the price, the 4th line contains 
//stock count information.5th line contains 
//additional product information.
private void FileProducts(String filename) throws FileNotFoundException {
  File file = new File(filename);
  Scanner line = new Scanner(file);
  line.useDelimiter("\n");
  while (line.hasNext()){
    String category = line.nextLine();//Category
    String name = line.nextLine();//Product Name
    String price = line.nextLine();//Price
    String stock = line.nextLine();//Stock Count
    String prodinfo = line.nextLine();//Product Information (if applicable)

    if (name.equals("Book")){//checks to see if the item is a book (has additional product information)
      String[] stocks = stock.split(" "); //splits the stock of paperback/hardcover up
      String[] title_author = prodinfo.split(":"); //splits up information of the book details
      //Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author)

      //adding a book to arraylist products
      this.products.add(new Book(name, generateCustomerId(), Double.valueOf(price), Integer.valueOf(stocks[0]), Integer.valueOf(stocks[1]) ,title_author[0], title_author[1]));
    }
    else {//if its not a book, do the following
      //public Product(String name, String id, double price, int stock, Category category)
      this.products.add(new Product(name, generateCustomerId(), Double.valueOf(price), Integer.valueOf(stock), Product.Category.valueOf(category)));
    }
    //products.add(new Product(name, id, price, stock, Product.Category.valueOf(category)));
  }

}
// EXCEPTIONS:

class Unkowncust extends RuntimeException {
  public Unkowncust(String e) {
    super(e);
  }
}

class unknownprod extends RuntimeException {
  public unknownprod(String e) {
    super(e);
  }
}

class Invalidproductoptions extends RuntimeException {
  public Invalidproductoptions(String e) {
    super(e);
  }
}

class prodoutofstock extends RuntimeException {
  public prodoutofstock(String e) {
    super(e);
  }
}

class invalidcustname extends RuntimeException {
  public invalidcustname(String e) {
    super(e);
  }
}

class invalidcustaddress extends RuntimeException {
  public invalidcustaddress(String e) {
    super(e);
  }
}

class invalidordernumber extends RuntimeException {
  public invalidordernumber(String e) {
    super(e);
  }
}
}