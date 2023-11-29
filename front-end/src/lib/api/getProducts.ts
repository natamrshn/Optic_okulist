import axios, { AxiosResponse } from 'axios';
import { GetTypes } from '../../types/GetTypes';
import { Product } from '../../types/Product';

export async function getProducts(token: string, type: GetTypes): Promise<Product[]> {
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