import { ReviewsGETRequest, ReviewsGETResponse } from "./Types/ReviewsGet";
import { ReviewsAPI } from "./api/ReviewsAPI";

export class ReviewsService {
  static async getAll(request: ReviewsGETRequest): Promise<ReviewsGETResponse> {
    return ReviewsAPI.getAll();
  }
}
