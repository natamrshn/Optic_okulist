import { Category } from "../../../Types/Category";

export interface LensePostRequest {
  lensConfigurationId: number;
  categories: Category[];
  name: string;
  price: number;
  identifier: string;
  description: string;
  imageUrl: string;
  imageUrlSecond: string;
}

export interface LensePostResponse {
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
