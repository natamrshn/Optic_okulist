import { BACKEND_URL } from "../../apiConfig";
import { Category } from "../../../Types/Category";

export class CategoryAPI {
  static category_url = BACKEND_URL + "/categories";
  static headers = {
    Authorization: `Bearer token`,
  };

  static setTokenToHeader(token: string) {
    if (!token || token.length === 0) {
      throw new Error(
        "You should give me a token. Not empty argument or undefined"
      );
    }

    this.headers.Authorization = `Bearer ${token}`;
  }

  static async getAll(): Promise<Category[]> {
    const settings = { headers: this.headers };
    const response = await fetch(this.category_url, settings);

    if (response.status === 403) {
      throw new Error("You don't have permissions to do it");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async getById(id: Category["id"]): Promise<Category> {
    const settings = { headers: this.headers };
    const url = this.category_url + `/${id}`;
    const response = await fetch(url, settings);

    if (response.status === 403) {
      throw new Error("You don't have permissions to do it");
    }

    if (response.status === 404) {
      throw new Error("This category was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async updateById(category: Category): Promise<Category> {
    const settings = {
      method: "PUT",
      headers: this.headers,
      body: JSON.stringify(category.name),
    };
    const url = this.category_url + `/${category.id}`;
    const response = await fetch(url, settings);

    if (response.status === 403) {
      throw new Error("You don't have permissions to do it");
    }

    if (response.status === 404) {
      throw new Error("This category was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async create(body: Category["name"]): Promise<Category> {
    const settings = {
      method: "POST",
      headers: this.headers,
      body: JSON.stringify(body),
    };
    const response = await fetch(this.category_url, settings);

    if (response.status === 403) {
      throw new Error("You don't have permissions to do it");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async delete(id: Category["id"]): Promise<void> {
    const settings = {
      method: "DELETE",
      headers: this.headers,
    };
    const url = this.category_url + `/${id}`;
    const response = await fetch(url, settings);

    if (response.status === 403) {
      throw new Error("You don't have permissions to do it");
    }

    if (response.status === 404) {
      throw new Error("This category was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }
  }
}
