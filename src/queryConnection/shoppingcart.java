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
        
        for(int i = 0; i< cart.size(); i++){
            if(cart.get(i).getDescription() == name){
                cart.remove(i);
                break;
            }
        }
    }
    @Override
    public String toString() {
        String fin= "";
        for(item t: cart){
            fin+=t.toString()+"\n";
        }
        return fin;
    }

    public double getTotal(){
        double total =0;
        for(item t : cart){
            total = total + t.getPriceNum();

        }
        return total;
    }
}
