import { Category } from "../../../Types/Category";

export interface LenseGetResponse {
  id: number;
  colors: string[];
  cylinders: number[];
  degrees: number[];
  diopters: number[];
  spheres: number[];
  categories: Category[];

  name: string;
  price: number;
  identifier: string;
  description: string;
  imageUrl: string;
  imageUrlSecond: string;
}
