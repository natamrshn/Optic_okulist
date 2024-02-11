import { BACKEND_URL } from "../../apiConfig";
import { LiquidGETResponse } from "../Types/LiquidGET";
import { LiquidPOSTRequest, LiquidPOSTResponse } from "../Types/LiquidPOST";
import { LiquidPutRequest, LiquidPutResponse } from "../Types/LiquidPUT";

export interface LiquidParams {
  volume: number;
  name: string;
}

export class LiquidAPI {
  static url_liquid = BACKEND_URL + "/liquid";
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

  static async getAll(): Promise<LiquidGETResponse[]> {
    return fetch(this.url_liquid).then((response) => response.json());
  }

  static async getById(id: number): Promise<LiquidGETResponse> {
    const url: string = this.url_liquid + `/${id}`;

    return fetch(url).then((response) => response.json());
  }

  static async getByParams(params: LiquidParams): Promise<LiquidGETResponse[]> {
    const { volume, name } = params;
    const url = this.url_liquid + `search?volume=${volume}&name=${name}`;

    return fetch(url).then((response) => response.json());
  }

  static async create(body: LiquidPOSTRequest): Promise<LiquidPOSTResponse> {
    const settings = {
      method: "POST",
      body: JSON.stringify(body),
    };

    return fetch(this.url_liquid, settings).then((response) => response.json());
  }

  static async update(body: LiquidPutRequest): Promise<LiquidPutResponse> {
    const url = this.url_liquid + `/${body.identifier}`;
    const settings = {
      method: "PUT",
      body: JSON.stringify(body),
    };

    return fetch(url, settings).then((response) => response.json());
  }

  static async delete(id: number) {
    const url: string = BACKEND_URL + `/liquid/${id}`;
    const settings = { method: "DELETE" };

    return fetch(url, settings);
  }
}
