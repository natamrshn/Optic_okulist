import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../store';

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
    products: [],
};

const saveCartToServer = (cart: CartProduct[]) => {
    return new Promise<void>((resolve) => {
        setTimeout(() => {
            console.log('Cart saved on server:', cart);
            resolve();
        }, 1000);
    });
};

const saveCart = (isUserLoggedIn = false, products: CartProduct[]) => {
    if (isUserLoggedIn) {
        saveCartToServer(products);
    } else {
        localStorage.setItem('cart', JSON.stringify(products));
    }
};

const cartSlice = createSlice({
    name: 'cart',
    initialState,
    reducers: {
        addToCart: (state, action: PayloadAction<CartProduct>) => {
            const existingProductIndex = state.products.findIndex(
                (product) => product.id === action.payload.id
            );

            if (existingProductIndex !== -1) {
                state.products[existingProductIndex].amount +=
                    action.payload.amount;
            } else {
                state.products.push(action.payload);
            }

            console.log(state.products);
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
    },
});

export const { addToCart, removeFromCart, clearCart } = cartSlice.actions;

export const selectCart = (state: RootState) => state.cart.products;

export default cartSlice.reducer;
