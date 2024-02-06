import { BACKEND_URL } from "../../apiConfig";
import { GlassesPOSTRequest, GlassesPOSTResponse } from "../Types/GlassesPOST";

interface GlassesParams {
  color: string;
  model: string;
  manufacturer: string;
}

export class GlassesAPI {
  static url_glasses = BACKEND_URL + "/glasses";

  static async getAll(): Promise<GlassesPOSTResponse[]> {
    return fetch(this.url_glasses).then((response) => response.json());
  }

  // here we need to decide what there is needed for url: body.id or body.identifier. Now the body.identifier is used here
  static async getById(
    id: GlassesPOSTRequest["identifier"]
  ): Promise<GlassesPOSTResponse> {
    const url = this.url_glasses + `/${id}`;

    return fetch(url).then((response) => response.json());
  }

  static async getByParams(
    params: GlassesParams
  ): Promise<GlassesPOSTResponse[]> {
    const { color, model, manufacturer } = params;
    const url =
      this.url_glasses +
      "/search" +
      `?color=${color}&model=${model}&manufacturer=${manufacturer}`;

    return fetch(url).then((response) => response.json());
  }

  // here we also need to decide what there is needed for url: body.id or body.identifier. Now the body.id is used here
  static async update(body: GlassesPOSTRequest): Promise<GlassesPOSTResponse> {
    const url = this.url_glasses + `/${body.identifier}`;

    return fetch(url, { method: "PUT", body: JSON.stringify(body) }).then(
      (response) => response.json()
    );
  }

  static async create(body: GlassesPOSTRequest): Promise<GlassesPOSTResponse> {
    return fetch(this.url_glasses, {
      method: "POST",
      body: JSON.stringify(body),
    }).then((response) => response.json());
  }

  static async delete(id: GlassesPOSTRequest["identifier"]) {
    const url = this.url_glasses + `/${id}`;

    return fetch(url, { method: "DELETE" });
  }
}
