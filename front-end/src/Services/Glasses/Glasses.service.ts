import { GlassesAPI } from "./api/GlassesAPI";

export class GlassesService {
  static async getAll() {
    return GlassesAPI.getAll();
  }

  static async getByID() {}

  static async getByParams() {}

  static async create() {}

  static async update() {}

  static async delete() {}
}
