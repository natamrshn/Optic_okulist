import { ManufacturerGETResponse } from "./Types/ManufacturerGET";
import { ManufacturerAPI } from "./api/ManufacturerAPI";
import {
  ManufacturerPOSTRequest,
  ManufacturerPOSTResponse,
} from "./Types/ManufacturerPOST";

export class ManufacturerService {
  static async getAll(token: string): Promise<ManufacturerGETResponse[]> {
    ManufacturerAPI.setTokenToHeader(token);

    return ManufacturerAPI.getAll();
  }

  static async getById(
    token: string,
    id: number
  ): Promise<ManufacturerGETResponse> {
    ManufacturerAPI.setTokenToHeader(token);

    return ManufacturerAPI.getById(id);
  }

  static async create(
    token: string,
    body: ManufacturerPOSTRequest
  ): Promise<ManufacturerPOSTResponse> {
    ManufacturerAPI.setTokenToHeader(token);

    return ManufacturerAPI.create(body);
  }

  static async delete(token: string, id: number): Promise<void> {
    ManufacturerAPI.setTokenToHeader(token);

    return ManufacturerAPI.delete(id);
  }
}
