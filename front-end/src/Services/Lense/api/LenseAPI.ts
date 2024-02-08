import { BACKEND_URL } from "../../apiConfig";
import { LenseGetResponse } from "../Types/LenseGet";
import { LensePutRequest, LensePutResponse } from "../Types/LensePut";
import { LensePostRequest, LensePostResponse } from "../Types/LensePOST";

export class LenseAPI {
  static lenses_url = BACKEND_URL + "/contact-lenses";
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

  static async getAll(): Promise<LenseGetResponse[]> {
    return fetch(this.lenses_url).then((response) => response.json());
  }

  static async getById(id: number): Promise<LenseGetResponse> {
    const url = this.lenses_url + `/${id}`;
    const response = await fetch(url);

    if (response.status === 404) {
      throw new Error("Lenses was not found...");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return await response.json();
  }

  static async update(body: LensePutRequest): Promise<LensePutResponse> {
    const url = this.lenses_url + `/${body.identifier}`;
    const settings = {
      method: "PUT",
      body: JSON.stringify(body),
    };
    const response = await fetch(url, settings);

    if (response.status === 404) {
      throw new Error("Lenses was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async create(body: LensePostRequest): Promise<LensePostResponse> {
    const settings = {
      method: "POST",
      body: JSON.stringify(body),
    };
    const response = await fetch(this.lenses_url, settings);

    if (response.status === 400) {
      throw new Error("Bad request. Invalid data provided");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async delete(id: number): Promise<void> {
    const url = this.lenses_url + `/${id}`;
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error("Server error");
    }

    if (response.status === 404) {
      throw new Error("Lenses was not found...");
    }
  }
}
