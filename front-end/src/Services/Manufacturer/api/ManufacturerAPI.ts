import { BACKEND_URL } from "../../apiConfig";
import { ManufacturerGETResponse } from "../Types/ManufacturerGET";
import {
  ManufacturerPOSTRequest,
  ManufacturerPOSTResponse,
} from "../Types/ManufacturerPOST";

export class ManufacturerAPI {
  static manufacturer_url = BACKEND_URL + "/manufacturer";
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

  static async getAll(): Promise<ManufacturerGETResponse[]> {
    const settings = { headers: this.headers };
    const response = await fetch(this.manufacturer_url, settings);

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async getById(id: number): Promise<ManufacturerGETResponse> {
    const settings = { headers: this.headers };
    const url = this.manufacturer_url + `/${id}`;
    const response = await fetch(url, settings);

    if (response.status === 404) {
      throw new Error("Manufacturer was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async create(
    body: ManufacturerPOSTRequest
  ): Promise<ManufacturerPOSTResponse> {
    const settings = {
      method: "POST",
      headers: this.headers,
      body: JSON.stringify(body),
    };
    const response = await fetch(this.manufacturer_url, settings);

    if (response.status === 400) {
      throw new Error("Bad request");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return response.json();
  }

  static async delete(id: number): Promise<void> {
    const settings = {
      method: "DELETE",
      headers: this.headers,
    };
    const url = this.manufacturer_url + `/${id}`;
    const response = await fetch(url, settings);

    if (response.status === 404) {
      throw new Error("Manufacturer was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }
  }
}
