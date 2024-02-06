import { BACKEND_URL } from "../../apiConfig";
import { RegistrationRequest } from "../Types/IRegistration";
import { LoginRequest } from "../Types/ILogin";
import { ConfirmCodeRequest } from "../Types/IConfirm";
import { UserUpdateRequest } from "../Types/IUserUpdate";

export class AuthAPI {
  static url_user = `${BACKEND_URL}/user`;
  static url_auth = `${BACKEND_URL}/auth`;

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

  static async registrate(userData: RegistrationRequest) {
    const url: string = this.url_auth + "/register";

    return fetch(url, { method: "POST", body: JSON.stringify(userData) });
  }

  static async login(userData: LoginRequest) {
    const url: string = this.url_auth + "/login";

    return fetch(url, { method: "POST", body: JSON.stringify(userData) });
  }

  static async confirmCode(userData: ConfirmCodeRequest) {
    const url: string = this.url_user + "/confirm";

    return fetch(url, {
      method: "POST",
      headers: this.headers,
      body: JSON.stringify(userData),
    });
  }

  static async update(userData: UserUpdateRequest, userId: number) {
    const url: string = this.url_user + `/${userId}` + "/update";

    return fetch(url, {
      method: "POST",
      headers: this.headers,
      body: JSON.stringify(userData),
    });
  }

  static async initPasswordChanging() {
    const url = this.url_user + "/start";

    return fetch(url, { method: "POST" }); // POST? Maybe is it better to use 'GET'?
  }
}
