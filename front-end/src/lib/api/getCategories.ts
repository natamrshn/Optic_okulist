import axios, { AxiosResponse } from "axios";
import { Category } from "../../types/Category";
import { getAuthToken } from "../helpers/localStorageToken";

export async function getCategories(): Promise<Category[]> {
    const t = getAuthToken();
    const options = {
        headers: {
            Authorization: `Bearer ${t}`
        }
     }
    try {
        const response: AxiosResponse<Category[]> = await axios.get(
            "http://localhost:8080/api/categories", options
        );
        const categories = response.data;
        return categories;
    } catch (error) {
        return [];
    }
}
