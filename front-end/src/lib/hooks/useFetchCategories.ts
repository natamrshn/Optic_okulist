import { useEffect, useState } from "react";
import { getCategories } from "../api/getCategories";
import { Category } from "../../types/Category";

export function useFetchCategories() {
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        async function fetchCategories() {
            try {
                const categoriesFromAPI = await getCategories();
                setCategories(categoriesFromAPI);
            } catch (error) {
                console.error("Error fetching categories:", error);
            }
        }

        fetchCategories();
    }, []);

    return categories;
}
