import { ReviewsGETRequest, ReviewsGETResponse } from "./Types/ReviewsGet";

export class ReviewsService {
  static async getAll(
    request: ReviewsGETRequest
  ): Promise<ReviewsGETResponse | void> {}
}
