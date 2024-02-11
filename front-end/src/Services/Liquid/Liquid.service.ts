import { LiquidAPI, LiquidParams } from "./api/LiquidAPI";

import { LiquidPOSTRequest } from "./Types/LiquidPOST";
import { LiquidPutRequest } from "./Types/LiquidPUT";

export class LiquidService {
  static async getAll() {
    return LiquidAPI.getAll();
  }

  static async getByID(id: number) {
    return LiquidAPI.getById(id);
  }

  static async getByParams(params: LiquidParams) {
    return LiquidAPI.getByParams(params);
  }

  static async create(token: string, body: LiquidPOSTRequest) {
    LiquidAPI.setTokenToHeader(token);

    return LiquidAPI.create(body);
  }

  static async update(token: string, body: LiquidPutRequest) {
    LiquidAPI.setTokenToHeader(token);

    return LiquidAPI.update(body);
  }

  static async delete(token: string, id: number) {
    LiquidAPI.setTokenToHeader(token);

    return LiquidAPI.delete(id);
  }
}
