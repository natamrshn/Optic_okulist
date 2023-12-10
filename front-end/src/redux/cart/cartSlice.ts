import { createSelector, createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { getShoppingCart } from "./getShoppingCart";

export type CartProduct = {
    id: number;
    name: string;
    price: number;
    amount: number;
};

type CartState = {
    products: CartProduct[];
};

const initialState: CartState = {
    products: getShoppingCart(false),
};

const saveCartToServer = (cart: CartProduct[]) => {
    return new Promise<void>((resolve) => {
        setTimeout(() => {
            console.log("Cart saved on server:", cart);
            resolve();
        }, 1000);
    });
};

const saveCart = (isUserLoggedIn = false, products: CartProduct[]) => {
    if (isUserLoggedIn) {
        saveCartToServer(products);
    } else {
        localStorage.setItem("cart", JSON.stringify(products));
    }
};

const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        addToCart: (state, action: PayloadAction<CartProduct>) => {
            const existingProductIndex = state.products.findIndex(
                (product) => product.id === action.payload.id
            );

            if (existingProductIndex !== -1) {
                state.products[existingProductIndex].amount += 1;
            } else {
                state.products.push(action.payload);
            }

            saveCart(false, state.products);
        },
        removeFromCart: (state, action: PayloadAction<number>) => {
            state.products = state.products.filter(
                (product) => product.id !== action.payload
            );

            saveCart(false, state.products);
        },
        clearCart: (state) => {
            state.products = [];
            saveCart(false, state.products);
        },
        decreaseProductAmount: (state, action: PayloadAction<number>) => {
            const productId = action.payload;
            const product = state.products.find(
                (product) => product.id === productId
            );
            if (product && product.amount > 1) {
                product.amount -= 1;
            }
            saveCart(false, state.products);
        },
    },
});

export const selectProductAmount = (productId: number) =>
    createSelector(
        (state: RootState) => state.cart.products,
        (products: CartProduct[]) => {
            const product = products.find(
                (product) => product.id === productId
            );
            return product ? product.amount : 0;
        }
    );

export const { addToCart, removeFromCart, clearCart, decreaseProductAmount } =
    cartSlice.actions;

export const selectCart = (state: RootState) => state.cart.products;

export default cartSlice.reducer;
