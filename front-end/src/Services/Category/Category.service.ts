import { CategoryAPI } from "./api/CategoryAPI";
import { Category } from "../../Types/Category";

export class CategoryService {
  static async getAll(token: string): Promise<Category[]> {
    CategoryAPI.setTokenToHeader(token);

    return CategoryAPI.getAll();
  }

  static async getById(token: string, id: Category["id"]): Promise<Category> {
    CategoryAPI.setTokenToHeader(token);

    return CategoryAPI.getById(id);
  }

  static async delete(token: string, id: Category["id"]): Promise<void> {
    CategoryAPI.setTokenToHeader(token);

    return CategoryAPI.delete(id);
  }

  static async update(token: string, category: Category): Promise<Category> {
    CategoryAPI.setTokenToHeader(token);

    if (!category.name || !category.name.length) {
      throw new Error("You must to provide a new name for the category");
    }

    return CategoryAPI.updateById(category);
  }

  static async create(
    token: string,
    categoryName: Category["name"]
  ): Promise<Category> {
    CategoryAPI.setTokenToHeader(token);

    if (!categoryName.length) {
      throw new Error("You must to provide a new name for the category");
    }

    return CategoryAPI.create(categoryName);
  }
}
