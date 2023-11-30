import { Category } from "./Category"

export type Product = {
    id: number,
    name: string,
    price: number,
    description: string,
    identifier: string,
    color: string,
    model: string,
    manufacturer: string,
    categories: Category[]
};