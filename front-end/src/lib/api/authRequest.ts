import axios from "axios";
import { UserData } from "../helpers/formValidation";
import { setAuthToken } from "../helpers/localStorageToken";

export async function login(loginObject: { email: string, password: string}) {
    try {
        const response = await axios.post(
            "http://localhost:8080/auth/login",
            loginObject
        );

        const token = response.data.token;
        setAuthToken(token);
        console.log(token)
    } catch (error) {
        console.error("Login failed:", error);
    }
}

export async function register(registerObject: UserData) {
    try {
        const response = await axios.post(
            "http://localhost:8080/auth/register",
            registerObject
        );

        const token = response.data;
        console.log("Received token:", token);
    } catch (error) {
        console.error("Login failed:", error);
    }
}
