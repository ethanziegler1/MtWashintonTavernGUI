package queryConnection;

import java.util.ArrayList;

public class shoppingcart {
    ArrayList<item> cart = new ArrayList<item>();
    public ArrayList<item> getCart() {
        return cart;
    }
    public void addFood(item temp) {
        cart.add(temp);
    }

    public void removeFromCart(String name){
        cart.remove(item.getDescription() == "name");
    }
    @Override
    public String toString() {
        String fin= "";
        for(item t: cart){
            fin+=t.toString()+"\n";
        }
        return fin;
    }
}
