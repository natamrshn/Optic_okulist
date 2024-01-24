import { BACKEND_URL } from "../../apiConfig";
import {CartItem} from "../Types/CartItem";

export class CartAPI {
  static url_cart = BACKEND_URL + '/cart';

  static headers = {
    "Authorization": `Bearer token`
  }

  static setTokenToHeader(token: string) {
    if (!token || token.length === 0) {
      throw new Error('You should give me a token. Not empty argument or undefined');
    }

    this.headers.Authorization = `Bearer ${token}`;
  }

  static async getGoods() {
    return fetch(this.url_cart);
  }

  static async addGood(newGood: CartItem) {
    return fetch(this.url_cart, { method: 'POST', body: JSON.stringify(newGood)});
  }
}
