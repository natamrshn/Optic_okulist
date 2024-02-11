import { Review } from "./Review";
import { OrderBy } from "./OrderBy";

export interface ReviewsGETRequest {
  pageSize: number;
  pageToken: string;
  orderBy: OrderBy;
}

export interface ReviewsGETResponse {
  reviews: Review[];
  averageRating: number;
  totalReviewCount: number;
  nextPageToken?: string;
}
