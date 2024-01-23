import React from "react";
import { ConfirmCodeRequest } from "./Types/IConfirm";
import { LoginRequest } from "./Types/ILogin";
import { RegistrationRequest, RegistrationResponse } from "./Types/IRegistration";
import { UserUpdateRequest } from "./Types/IUserUpdate";
import { AuthAPI } from "./api/AuthAPI";

interface Setters {
  setError: React.Dispatch<React.SetStateAction<string>>;
  setUserData: React.Dispatch<React.SetStateAction<RegistrationResponse>>;
}

export class AuthService {
  static async registrate(userFields: RegistrationRequest, token: string, setters: Setters) {
    const { setError, setUserData } = setters;

    try {
      AuthAPI.setTokenToHeader(token);
      const response = await AuthAPI.registrate(userFields);

      if (response.status === 200) {
        setUserData(await response.json());
      } else {
        setError('Bad request. Registration failed');
      }
    } catch (error: any) {
      console.log(error.message);
    }
  }

  static login(userFields: LoginRequest) {
    try {

    } catch (error: any) {
      console.log(error.message);
    }
  }

  static confirmCode(userFields: ConfirmCodeRequest) {
    try {

    } catch (error: any) {
      console.log(error.message);
    }
  }

  static updateData(userFields: UserUpdateRequest) {
    try {

    } catch (error: any) {
      console.log(error.message);
    }
  }

  // ask about user/start endpoint
}
