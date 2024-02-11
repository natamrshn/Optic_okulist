import { LenseAPI } from "./api/LenseAPI";
import { LensePostRequest } from "./Types/LensePOST";
import { LensePutRequest } from "./Types/LensePut";

export class LenseService {
  static async getAll() {
    return LenseAPI.getAll();
  }

  static async getByID(id: number) {
    return LenseAPI.getById(id);
  }

  static async create(token: string, body: LensePostRequest) {
    LenseAPI.setTokenToHeader(token);

    return LenseAPI.create(body);
  }

  static async update(token: string, body: LensePutRequest) {
    LenseAPI.setTokenToHeader(token);

    return LenseAPI.update(body);
  }

  static async delete(token: string, id: number) {
    LenseAPI.setTokenToHeader(token);

    return LenseAPI.delete(id);
  }
}
