import { CartProduct } from "./cartSlice";

function tempCartFetcher(): CartProduct[] {
    return []
}

export function getShoppingCart(isUserLoggedIn: boolean = false): CartProduct[] {
    const storedCart = localStorage.getItem("cart");

    if (isUserLoggedIn) {
        return tempCartFetcher();
    }

    if (storedCart) {
        return JSON.parse(storedCart);
    }

    return [];
}