import { ReviewsGETResponse } from "../Types/ReviewsGet";

export class ReviewsAPI {
  static accountId = "";
  static locationId = "";
  static reviews_url = `https://mybusiness.googleapis.com/v4/accounts/${this.accountId}/locations/${this.locationId}/reviews`;

  static async getAll(): Promise<ReviewsGETResponse> {
    const response = await fetch(this.reviews_url);

    if (response.status === 401) {
      throw new Error("Unauthorized for loading reviews");
    }

    if (!response.ok) {
      throw new Error("Unexpected error");
    }

    return response.json();
  }
}
