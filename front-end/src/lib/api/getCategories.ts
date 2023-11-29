import axios, { AxiosResponse } from 'axios';
import { Category } from '../../types/Category';

export async function getCategories(token: string): Promise<Category[]> {
    const options = {
        headers: {
            Authorization: `Bearer ${token}`
        }
     }
    try {
        const response: AxiosResponse<Category[]> = await axios.get(
            'http://localhost:8080/api/categories', options
        );
        const categories = response.data;
        return categories;
    } catch (error) {
        return [];
    }
}
