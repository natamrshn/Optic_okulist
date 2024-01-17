import { BACKEND_URL } from "../../apiConfig";

export class AuthAPI {
  static url = `BACKEND_URL/user`;
  static headers = {
    "Authorization": `Bearer token`
  }

  static setTokenToHeader(token: string) {
    try {
      if (!token) {
        throw new Error('You should give me a token. Not empty argument or undefined');
      }
  
      this.headers.Authorization = `Bearer ${token}`;
    } catch(error: any) {
      console.log(error.message)
    }
  }
}