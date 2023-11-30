import axios, { AxiosResponse } from "axios";
import { GetTypes } from "../../types/GetTypes";
import { Product } from "../../types/Product";
import { getAuthToken } from "../helpers/localStorageToken";

export async function getProducts(type: GetTypes): Promise<Product[]> {
    const token = getAuthToken()
    const options = {
        headers: {
            Authorization: `Bearer ${token}`
        }
     }
    try {
        const response: AxiosResponse<Product[]> = await axios.get(
            `http://localhost:8080/api/${type}`, options
        );
        const categories = response.data;
        return categories;
    } catch (error) {
        return [];
    }
}