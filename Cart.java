import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Cart {
    //

    private ArrayList<CartItem> shoppingCart;

    /*
     * Constructor method
     * 
     * @param customerid
     */
    public Cart() {
        this.shoppingCart = new ArrayList<CartItem>();
    }

    // Setter getter methods
    public ArrayList<CartItem> getshoppingcart() {

        return this.shoppingCart;
    }

    public void printcart() {
        for (CartItem items : shoppingCart) {
            items.print();
        }
    }

    public void addtocart(CartItem item) {
        shoppingCart.add(item);
    }

    public void removefromcart(CartItem item) {
        // if (shoppingCart.contains(item)) {
        //     System.out.println("Conatined");
        // } else {
        //     System.out.println("Not Conatined");
        // }
        Iterator<CartItem> itr = shoppingCart.iterator();
       
        boolean outcome = false;
        while (itr.hasNext()){
            
            CartItem next = itr.next();
            //gets the product id of the parameter and the items in the iterator itr and checks if they are equal
            
            if ((item.getproduct().getId()).equals(next.getproduct().getId())){
                itr.remove();
                outcome = true;
            }
            //Check if outcome is true, then the method has done its job and can break out of loop
            if (outcome == true){break;}
        }

    }
}