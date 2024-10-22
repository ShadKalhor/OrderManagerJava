package Controllers;

import Entities.Cart;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartController {
    private final List<Cart> cartList = new ArrayList<>();

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

        Cart newCart = new Cart(UUID.randomUUID(), quantity, itemId, subTotal);
        cartList.add(newCart);
        System.out.println("Cart item created successfully!");
    }

    public Cart readCart(UUID cartId) {
        return cartList.stream().filter(cart -> cart.id().equals(cartId)).findFirst().orElse(null);
    }

    public void updateCart(UUID cartId, int quantity, String itemId, double subTotal) {
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

        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = cartList.get(i);
            if (cart.id().equals(cartId)) {
                Cart updatedCart = new Cart(cartId, quantity, itemId, subTotal);
                cartList.set(i, updatedCart);
                System.out.println("Cart updated successfully!");
                return;
            }
        }
        System.out.println("Cart not found.");
    }

    public void deleteCart(UUID cartId) {
        boolean removed = cartList.removeIf(cart -> cart.id().equals(cartId));
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

    public void clearCart() {
        cartList.clear();
        System.out.println("Cart cleared successfully!");
    }
    public void setCartList(List<Cart> cartList) {
        this.cartList.clear();
        this.cartList.addAll(cartList);
    }

}
