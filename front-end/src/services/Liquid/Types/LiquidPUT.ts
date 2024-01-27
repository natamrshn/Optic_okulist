import {Category} from "../../../Types/Category";
import {Variation} from "../../../Types/Variation";

export interface LiquidPutRequest {
  name: string;
  volume: number;
  price: number;
  description: string;
  identifier: string;
  imageUrl: string;
  imageSecondUrl?: string;
  categories: Category[];
  coverImage: string;
}

export interface LiquidPutResponse extends LiquidPutRequest {
  id: number;
  variations: Variation[];
}
