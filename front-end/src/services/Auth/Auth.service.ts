import React from "react";

import { RegistrationRequest, RegistrationResponse } from "./Types/IRegistration";
import { LoginRequest } from "./Types/ILogin";
import { ConfirmCodeRequest } from "./Types/IConfirm";
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

  static async login(userFields: LoginRequest, token: string, setters: Setters) {
    const { setError, setUserData } = setters;

    try {
      const response = await AuthAPI.login(userFields);

      if (response.status === 200) {
        setUserData(await response.json());
      } else {
        setError('Bad request. Login failed');
      }
    } catch (error: any) {
      console.log(error.message);
    }
  }

  static async confirmCode(userFields: ConfirmCodeRequest, token: string, setters: Setters) {
    const { setError, setUserData } = setters;

    try {
      AuthAPI.setTokenToHeader(token);

      const response = await AuthAPI.confirmCode(userFields);

      if (response.status === 200) {
        setUserData(await response.json());
      } else {
        setError('Bad request. Confirmation failed');
      }
    } catch (error: any) {
      console.log(error.message);
    }
  }

  static async updateData(userFields: UserUpdateRequest, token: string, userId: number, setters: Setters) {
    const { setError, setUserData } = setters;

    try {
      AuthAPI.setTokenToHeader(token);

      const response = await AuthAPI.update(userFields, userId);

      if (response.status === 200) {
        setUserData(await response.json());
      } else {
        setError('Bad request. Updating failed');
      }
    } catch (error: any) {
      console.log(error.message);
    }
  }

  // ask about user/start endpoint
}
