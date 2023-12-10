import axios, { AxiosResponse } from "axios";
import { GetTypes } from "../../types/GetTypes";
import { Product } from "../../types/Product";

export async function getProducts(type: GetTypes): Promise<Product[]> {
    try {
        const response: AxiosResponse<Product[]> = await axios.get(
            `http://localhost:8080/${type}`
        );
        const categories = response.data;
        return categories;
    } catch (error) {
        return [];
    }
}