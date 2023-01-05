package demo.IMT2021019;

import java.util.*;
import demo.*;
import ecomm.*;

public class IMT2021019_seller extends Seller{


    private ArrayList<Product> productsOffered = new ArrayList<Product>();
    private ArrayList<Platform> platforms = new ArrayList<Platform>();

    public IMT2021019_seller(String id){
        super(id);
    }
	
	// Platform it is being added to.
	// Should in turn add itself to the Platform (with addSeller)
	public void addPlatform(Platform thePlatform){
        platforms.add(thePlatform);
        thePlatform.addSeller(this);
    };
	
	// Seller to return listing of Products of specified Category
	public ArrayList<Product> findProducts(Globals.Category whichOne){
        ArrayList<Product> temp = new ArrayList<Product>();

        for (int i=0; i<productsOffered.size(); i++){
            if (productsOffered.get(i).getCategory().equals(whichOne)){
                temp.add(this.productsOffered.get(i));
            }
        }

        return temp;
    };

	// User wants to buy specified quantity of productID
	// Return true if transaction succeeds, false otherwise. 
	// Transaction fails if incorrect productID or quantity exceeds available inventory

	public boolean buyProduct(String productID, int quantity){
        for (int i=0; i<productsOffered.size(); i++){
            if (productsOffered.get(i).getProductID().equals(productID)){
                if (productsOffered.get(i).getQuantity() >= quantity){
                    if (productsOffered.get(i).getCategory().equals(Globals.Category.Book)){
                        Book book = (Book)productsOffered.get(i);
                        book.setQuantity(quantity);
                        productsOffered.add(i, book);
                    } 
                    else{
                        Mobile mobile = (Mobile)productsOffered.get(i);
                        mobile.setQuantity(quantity);
                        productsOffered.add(i, mobile);
                    }

                    return true;
                    
                }else{
                    return false;
                }   
            }
        }

        return false;
    };
	
	public void AddProd(Product obj){
		this.productsOffered.add(obj);
	}
    
}
