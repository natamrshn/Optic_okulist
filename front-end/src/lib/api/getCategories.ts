import axios, { AxiosResponse } from "axios";
import { Category } from "../../types/Category";

export async function getCategories(): Promise<Category[]> {
    try {
        const response: AxiosResponse<Category[]> = await axios.get(
            "http://localhost:8080/categories"
        );
        const categories = response.data;
        return categories;
    } catch (error) {
        return [];
    }
}
