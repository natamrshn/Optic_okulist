import { Dispatch, SetStateAction } from "react";
import { CartItem } from "./Types/CartItem";
import { CartAPI } from "./api/CartAPI";
import { Cart } from "./Types/Cart";

interface Setters {
  setGoodsInCart: Dispatch<SetStateAction<Cart[]>>;
}

export class AuthService {
  static async getGoods(token: string, setters: Setters) {
    const { setGoodsInCart } = setters;

    try {
      const response= await CartAPI.getGoods();
      CartAPI.setTokenToHeader(token);

      if (response.status === 200) {
        setGoodsInCart(await response.json())
      }
    } catch (error: any) {
      console.log(error.message);
    }
  }

  static async addGood(token: string, setters: Setters, newGood: CartItem) {
    const { setGoodsInCart } = setters;

    try {
      CartAPI.setTokenToHeader(token);

      const response = await CartAPI.addGood(newGood);

      if (response.status === 200) {
        const addedGood: Cart = await response.json();

        setGoodsInCart(goods => {
          const newListGoods = [...goods];

          newListGoods.push(addedGood);

          return newListGoods;
        });
      }
    } catch (error: any) {
      console.log(error.message);
    }
  }

  static async updateGood( updatedGood: CartItem, token: string, setters: Setters) {
    const { setGoodsInCart } = setters;

    try {
      CartAPI.setTokenToHeader(token);

      const response = await CartAPI.updateGood(updatedGood.quantity, updatedGood.productId);

      if (response.status === 200) {
        // success
      }

      if (response.status === 404) {
        throw new Error('Item of the cart was not found');
      }


    } catch (error: any) {
      console.log(error.message);
    }
  }

  static async removeGood(goodID: CartItem['productId'], token: string, setters: Setters) {
    const { setGoodsInCart } = setters;

    try {
      CartAPI.setTokenToHeader(token);

      const response = await CartAPI.deleteGood(goodID);

      if (response.status === 200) {
        // remove the good from the goodlist
      }

      if (response.status === 404) {
        throw new Error('Item of the cart was not found')
      }
    } catch (error: any) {
      console.log(error.message);
    }
  }
}