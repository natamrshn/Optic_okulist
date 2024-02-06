import { LiquidAPI } from "./api/LiquidAPI";

export class LiquidService {
  static async getAll() {
    return LiquidAPI.getAll();
  }

  static async getByID() {}

  static async create() {}

  static async update() {}

  static async delete() {}
}
