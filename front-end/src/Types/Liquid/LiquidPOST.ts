import { Category } from "../Category";
import { Variation } from "../Variation";

export interface LiquidPOSTRequest {
  name: string;
  volume: number;
  price: number;
  description: string;
  identifier: string;
  imageUrl: string;
  imageUrlSecond: string;
  categories: Array<Category>;
  coverImage: string;
};


export interface LiquidPOSTResponse {
  id: number;
  name: string;
  volume: number;
  price: number;
  description: string;
  identifier: string;
  imageUrl: string;
  imageUrlSecond: string;
  categories: Array<Category>;
  variations: Array<Variation>;
}