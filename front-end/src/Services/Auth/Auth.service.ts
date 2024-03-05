import {
  RegistrationRequest,
  RegistrationResponse,
} from "./Types/IRegistration";
import { LoginRequest, LoginResponse } from "./Types/ILogin";
import { ConfirmCodeRequest, ConfirmCodeResponse } from "./Types/IConfirm";
import { UserUpdateRequest, UserUpdateResponse } from "./Types/IUserUpdate";

import { AuthAPI } from "./api/AuthAPI";

export class AuthService {
  static async registrate(
    userFields: RegistrationRequest
  ): Promise<RegistrationResponse> {
    const response = await AuthAPI.registrate(userFields);

    if (response.status === 400) {
      throw new Error("Bad request.");
    }

    if (!response.ok) {
      throw new Error("Unexpected behavior");
    }

    return await response.json();
  }

  static async login(userFields: LoginRequest): Promise<LoginResponse> {
    const response = await AuthAPI.login(userFields);

    if (response.status === 400) {
      throw new Error("Bad request. Login failed");
    }

    if (response.status === 404) {
      throw new Error("User with this email was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return await response.json();
  }

  static async confirmCode(
    userFields: ConfirmCodeRequest,
    token: string
  ): Promise<ConfirmCodeResponse> {
    AuthAPI.setTokenToHeader(token);

    const response = await AuthAPI.confirmCode(userFields);

    if (response.status === 400) {
      throw new Error("Bad request. Confirmation failed");
    }

    if (response.status === 404) {
      throw new Error("User with this email was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return await response.json();
  }

  static async updateData(
    userFields: UserUpdateRequest,
    token: string,
    userId: number
  ): Promise<UserUpdateResponse> {
    AuthAPI.setTokenToHeader(token);

    const response = await AuthAPI.update(userFields, userId);

    if (response.status === 400) {
      throw new Error("Bad request. Confirmation failed");
    }

    if (response.status === 404) {
      throw new Error("User with this email was not found");
    }

    if (!response.ok) {
      throw new Error("Server error");
    }

    return await response.json();
  }

  static async initPasswordChanging(token: string) {
    AuthAPI.setTokenToHeader(token);

    const response = AuthAPI.initPasswordChanging();
  }

  static async getUserInfo(
    token: string
  ): Promise<Omit<RegistrationResponse, "id">> {
    AuthAPI.setTokenToHeader(token);

    return AuthAPI.getUserInfo();
  }
}
