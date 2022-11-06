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

public class CartItem {
    // The CartItem object has 2 parameters, product and productOptions

    private Product product;
    private String productOptions;

    public CartItem(Product product, String productOptions) {
        this.product = product;

        this.productOptions = productOptions;
    }

    // // check if the productOptions is valid
    // public boolean equals(Object other) {
    //     Product otherP = (Product) other;
    //     return this.product.getId().equals(otherP.getId());
    // }
    public Product getproduct(){
        return(this.product);
}
    // prints item(s) i  customer's Cart object
    public void print() {
        this.product.print();
    }

    public String getprodoptions(){
        return productOptions;
    }
}
