import { Category } from "../../../Types/Category";

export interface LensePutRequest {
  lensConfigurationId: number;
  categories: Category[];
  name: string;
  price: number;
  identifier: string;
  description: string;
  imageUrl: string;
  imageUrlSecond: string;
}

export interface LensePutResponse {
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
