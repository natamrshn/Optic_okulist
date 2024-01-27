import {Category} from "../../../Types/Category";

export interface Cart {
  id: number;
  product: {
    id: number;
    name: string;
    price: number;
    identifier: string;
    description: string;
    categories: Array<Category>;
    imageUrl: string;
    imageUrlSecond: string;
  };
  lenseConfig: {
    color: string;
    cylinder: string;
    degree: string;
    diopter: string;
    sphere: string;
  };
  quantity: number;
}
