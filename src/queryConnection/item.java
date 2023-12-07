package queryConnection;

import java.util.Currency;

public class item {
    private String price;
    private String category;
    private static String description;
    private String ItemID;
 
    public item(String price,String category, String description, String ItemID ) {
        this.price = price;
        this.category= category;
        this.ItemID = ItemID;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public static String getDescription() {
        return description;
    }
    public String getItemID() {
        return ItemID;
    }
    public String getPrice() {
        return price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setDescription(String description) {
        item.description = description;
    }
    public void setItemID(String itemID) {
        ItemID = itemID;
    }public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return description +" : " + price;
    }
    
}
