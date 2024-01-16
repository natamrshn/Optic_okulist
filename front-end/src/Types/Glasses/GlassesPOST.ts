import { Category } from "../Category";
import { Variation } from "../Variation";

export type GlassesPOSTRequest = {
  name: string;
  price: number;
  description: string;
  identifier: string;
  color: string;
  model: string;
  manufacturer: string;
  imageUrl: string;
  imageUrlSecond: string;
  coverImage: string;
  categories: Array<Category>;
};


export type GlassesPOSTResponse = {
  id: number;
  name: string;
  price: number;
  description: string;
  identifier: string;
  color: string;
  model: string;
  manufacturer: string;
  imageUrl: string;
  imageUrlSecond: string;
  categories: Array<Category>;
  variations: Array<Variation>;
}