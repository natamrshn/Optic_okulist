import { BACKEND_URL } from "../../apiConfig";
import {RegistrationRequest, RegistrationResponse} from "../Types/IRegistration";

export class AuthAPI {
  static url_user = `${BACKEND_URL}/user`;
  static url_auth = `${BACKEND_URL}/auth`;

  static headers = {
    "Authorization": `Bearer token`
  }

  static setTokenToHeader(token: string) {
    if (!token || token.length === 0) {
      throw new Error('You should give me a token. Not empty argument or undefined');
    }

    this.headers.Authorization = `Bearer ${token}`;
  }

  static async registrate(userData: RegistrationRequest) {
    const url: string = this.url_auth + 'register'

    return fetch(url, { method: 'POST', headers: this.headers, body: JSON.stringify(userData) })
  }
}