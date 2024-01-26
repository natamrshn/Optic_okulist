import { BACKEND_URL } from "../../apiConfig";

export class LiquidAPI {
  static async delete(id: number) {
    const url: string = BACKEND_URL + `/liquid/${id}`;

    return fetch(url, { method: 'DELETE' });
  }
}
