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

  static async updateGood(token: string, setters: Setters) {
    try {

    } catch (error: any) {
      console.log(error.message);
    }
  }

  static async removeGood(goodID: CartItem['productId'], token: string, setters: Setters) {
    try {

    } catch (error: any) {
      console.log(error.message);
    }
  }
}