import java.util.ArrayList;
//Carlos Carandang
//501033552
/* A shoe is a product which has additional information, the size of the shoe and the color of the shoe
   A shoe product has sizes ranging for size 6 - 10, and has two color options, black or brown
*/
public class Shoes extends Product {
  private int size;
  private int sizes[] = {6, 7, 8, 9, 10};

  // Stock related information NOTE: inherited stockCount variable is used for
  int blackstock;
  int brownstock;

  public Shoes(String name, String id, double price, int blackstock, int brownstock, int size) {
    // Make use of the constructor in the super class Product. Initialize additional
    // Book instance variables.
    // Set category to BOOKS
    super(name, id, price, 100000, Product.Category.SHOES);
    this.size = size;
    this.blackstock = blackstock;
    this.brownstock = brownstock;
  }

  // Check if a valid format
  public boolean validOptions(String productOptions) {
    // Check to see if the size in product options is a valid size
    boolean exist = false;
    int userSize = Character.getNumericValue(productOptions.charAt(0)); // Changing the sizes array into a character, so it could be compared
    //Check if the size is a valid option
    for (int size : sizes) {
      // productOptions.charAt(0) refers to the desired shoe size
      if (userSize == size) {
        exist = true;  
        }
      }
    if (exist == false){
      return (false);
    }
    // check to see if the desired color is either black or brown
    if (productOptions.substring(2).equalsIgnoreCase("BLACK") || productOptions.substring(2).equalsIgnoreCase("BROWN")) {
        return (true);
    }
    //if the color is not valid, return false
    return (false);

  }

  // Override getStockCount() in super class.
  public int getStockCount(String productOptions) {
    // Use the productOptions to check for (and return) the number of stock for
    // shoe size + color

    // Checks if ProductOptions is a valid shoe type
    if (validOptions(productOptions)) {
      // If so, return the stock for the desired color
      // returning black stock
      if (productOptions.substring(2).equalsIgnoreCase("BLACK")) {
        return (this.blackstock);
      }
      // returning brown stock
      else if (productOptions.substring(2).equalsIgnoreCase("BROWN")) {
        return (this.brownstock);
      }

    }
    // If it is not either, it will return 0
    return (0);
  }

  public void setStockCount(int stockCount, String productOptions) {
    // Use the productOptions to check for (and set) the number of stock for
    // "Black" and "brown"
    // Use the variables paperbackStock and hardcoverStock at the top.
    // For "EBook", set the inherited stockCount variable.

    // Checks if ProductOptions is a valid shoe type
    if (validOptions(productOptions)) {
      // If so, set the stock count for the desired shoe color to stockCount
      if (productOptions.substring(2).equalsIgnoreCase("BLACK")) {
        this.blackstock = stockCount;
      } else if (productOptions.substring(2).equalsIgnoreCase("BROWN")) {
        this.brownstock = stockCount;
      }
    }
  }

  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions) {
    // Use the productOptions to check for (and reduce) the number of stock for
    // "Black" or "brown"

    // NOTE:Reduced it by 1 increment, as shown in the Product.java method
    // (reduceStockCount)

    // Checks if ProductOptions is a valid shoe type
    if (validOptions(productOptions)) {

      // If so, reduce the count for the desired shoe color
      if (productOptions.substring(2).equalsIgnoreCase("BLACK")) {
        this.blackstock--;
      } else if (productOptions.substring(2).equalsIgnoreCase("BROWN")) {
        this.brownstock--;
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
    System.out.print(" Shoe Size: " + size);
  }
}
