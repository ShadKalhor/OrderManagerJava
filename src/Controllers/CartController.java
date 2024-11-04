/*
package Controllers;

import Entities.Cart;
import Entities.Item;
import Entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartController {
    List<Cart> cartList = new ArrayList<>();
    private static final String CART_FILE = "cart.json";
    private ItemController itemController = new ItemController();
*/
/*

    public CartController(){
        cartList = loadCartFromFile();
    }
    public void addItemToCart(Item item) {
        cartList.add(item.getId());
        saveCartToFile();
        System.out.println("Item added to cart and saved to file.");
    }
    private void saveCartToFile() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(CART_FILE)) {
            gson.toJson(cartList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Cart> loadCartFromFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CART_FILE)) {
            Type cartType = new TypeToken<List<Cart>>() {}.getType();
            return gson.fromJson(reader, cartType);
        } catch (IOException e) {
            System.out.println("No existing cart file found. Starting with an empty cart.");
            return new ArrayList<>(); // Start with an empty cart if file is not found
        }
    }
*//*


    public void createCart(int quantity, String itemId, double subTotal) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Must be greater than zero.");
            return;
        }
        if (itemId == null || itemId.isEmpty()) {
            System.out.println("Invalid item ID.");
            return;
        }
        if (subTotal < 0) {
            System.out.println("Subtotal cannot be negative.");
            return;
        }

        Cart newCart = new Cart(UUID.randomUUID(), quantity, UUID.randomUUID(), subTotal);
        cartList.add(newCart);
        System.out.println("Cart item created successfully!");
    }

    public Cart loadCartById(UUID cartId) {
        return cartList.stream().filter(cart -> cart.getId().equals(cartId)).findFirst().orElse(null);
    }
    public Cart loadCartByUserId(UUID userId) {
        return cartList.stream().filter(cart -> cart.getUserId().equals(userId)).findFirst().orElse(null);
    }
    public void loadCarts() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("cart.json")) {
            Type cartListType = new TypeToken<List<Cart>>() {}.getType();
            cartList = gson.fromJson(reader, cartListType);

            if (cartList == null) {
                cartList = new ArrayList<>(); // Initialize if the file is empty or null
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load carts from cart.json. Returning empty list.");
        }
}

    public void updateCart(UUID cartId, int quantity, UUID itemId, double subTotal) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Must be greater than zero.");
            return;
        }
        if (itemId == null) {
            System.out.println("Invalid item ID.");
            return;
        }
        if (subTotal < 0) {
            System.out.println("Subtotal cannot be negative.");
            return;
        }

        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = cartList.get(i);
            if (cart.getId().equals(cartId)) {
                Cart updatedCart = new Cart(cartId, quantity, itemId, subTotal);
                cartList.set(i, updatedCart);
                System.out.println("Cart updated successfully!");
                return;
            }
        }
        System.out.println("Cart not found.");
    }

    public void deleteCart(UUID cartId) {
        boolean removed = cartList.removeIf(cart -> cart.getId().equals(cartId));
        if (removed) {
            System.out.println("Cart item deleted successfully!");
        } else {
            System.out.println("Cart item not found.");
        }
    }

    public List<Cart> listCarts() {
        if (cartList.isEmpty()) {
            System.out.println("Cart is empty.");
        }
        return new ArrayList<>(cartList);
    }

    public List<Item> GetAddedItems(UUID userId){
        loadCarts();
        Cart cart = loadCartByUserId(userId);
        List<UUID> itemIds = cart.getItemIds();
        List<Item> items = itemController.LoadItemsById(itemIds);
        return items;
    }
    public void AddItemToCart(UUID itemId,int quantity, User loggedInUser){

        Cart cart = new Cart(loggedInUser.getId(),quantity, itemId);
        Item item = itemController.getItemById(itemId);
        cart.CalculateSubtotal();




    }

    public void clearCart() {
        cartList.clear();
        System.out.println("Cart cleared successfully!");
    }
    public void setCartList(List<Cart> cartList) {
        this.cartList.clear();
        this.cartList.addAll(cartList);
    }

}
*/
