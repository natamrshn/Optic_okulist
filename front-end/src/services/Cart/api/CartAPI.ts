import { BACKEND_URL } from "../../apiConfig";
import { CartItem } from "../Types/CartItem";

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
    return fetch(this.url_cart, { method: 'GET', headers: this.headers });
  }

  static async addGood(newGood: CartItem) {
    return fetch(this.url_cart, { method: 'POST', body: JSON.stringify(newGood), headers: this.headers });
  }

  static async updateGood(updatedGood: CartItem['quantity'], goodId: CartItem['productId']) {
    const url = this.url_cart + `/cart-items/${goodId}`;

    return fetch(url, { method: 'PUT', body: JSON.stringify(updatedGood), headers: this.headers });
  }

  static async deleteGood(goodId:  CartItem['productId']) {
    const url = this.url_cart + `/${goodId}`;

    return fetch(url, { method: 'DELETE', headers: this.headers});
  }
}
