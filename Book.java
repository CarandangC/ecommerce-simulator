//Carlos Carandang
//501033552
/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product {
  private String author;
  private String title;

  // Stock related information NOTE: inherited stockCount variable is used for
  // EBooks
  int paperbackStock;
  int hardcoverStock;

  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title,
      String author) {
    // Make use of the constructor in the super class Product. Initialize additional
    // Book instance variables.
    // Set category to BOOKS
    super(name, id, price, 100000, Product.Category.BOOKS);
    this.title = title;
    this.author = author;
    this.paperbackStock = paperbackStock;
    this.hardcoverStock = hardcoverStock;
  }

  // Check if a valid format
  public boolean validOptions(String productOptions) {
    // check the parameter productOptions if it is a "Paperback" or "Hardcover" or
    // "EBook"
    // if it is one of these, return true, else return false
    if (productOptions.equalsIgnoreCase("PAPERBACK") || productOptions.equalsIgnoreCase("HARDCOVER")
        || productOptions.equalsIgnoreCase("EBOOK")) {
      return (true);
    } else {
      return (false);
    }
  }

  // Override getStockCount() in super class.
  public int getStockCount(String productOptions) {
    // Use the productOptions to check for (and return) the number of stock for
    // "Paperback" etc
    // Use the variables paperbackStock and hardcoverStock at the top.
    // For "EBook", use the inherited stockCount variable.

    // Checks if ProductOptions is a valid book type

    // If so, return the stock for the desired booktype
    if (productOptions.equals("Paperback")) {
      return (this.paperbackStock);
    } else if (productOptions.equals("Hardcover")) {
      return (this.hardcoverStock);
    }
    // If it is not either, it will resort to return Ebook stock
    else {
      return (super.getStockCount(productOptions));
    }
  }

  public void setStockCount(int stockCount, String productOptions) {
    // Use the productOptions to check for (and set) the number of stock for
    // "Paperback" etc
    // Use the variables paperbackStock and hardcoverStock at the top.
    // For "EBook", set the inherited stockCount variable.

    // Checks if ProductOptions is a valid book type
    if (validOptions(productOptions)) {
      // If so, set the stock count for the desired booktype to stockCount
      if (productOptions.equalsIgnoreCase("PAPERBACK")) {
        this.paperbackStock = stockCount;
      } else if (productOptions.equalsIgnoreCase("HARDCOVER")) {
        this.hardcoverStock = stockCount;
      }
      // Setting the inherited stockCount variable
      else {
        super.setStockCount(stockCount, productOptions);
      }
    }
  }

  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions) {
    // Use the productOptions to check for (and reduce) the number of stock for
    // "Paperback" etc
    // Use the variables paperbackStock and hardcoverStock at the top.
    // For "EBook", set the inherited stockCount variable.

    // NOTE:Reduced it by 1 increment, as shown in the Product.java method
    // (reduceStockCount)

    // Checks if ProductOptions is a valid book type
    if (validOptions(productOptions)) {

      // If so, reduce the count for the desired booktype
      if (productOptions.equals("Paperback")) {
        this.paperbackStock--;
      } else if (productOptions.equals("Hardcover")) {
        this.hardcoverStock--;
      }
      // Setting the inhertied stockCount variable
      else {
        super.reduceStockCount(productOptions);
      }
    }
  }

  /*
   * Print product information in super class and append Book specific information
   * title and author
   */
  public void print() {
    // Replace the line below.
    // Make use of the super class print() method and append the title and author
    // info. See the video
    super.print();
    System.out.print(" Book Title: " + title + "Author: " + author);
  }
}
