enum StarRating {
  STAR_RATING_UNSPECIFIED = "STAR_RATING_UNSPECIFIED",
  ONE = "ONE",
  TWO = "TWO",
  THREE = "THREE",
  FOUR = "FOUR",
  FIVE = "FIVE",
}

export interface Reviewer {
  profilePhotoUrl: string;
  displayName: string;
  isAnonymous: boolean;
}

interface ReviewReply {
  comment: string;
  updateTime: string;
}

export interface Review {
  name: string;
  reviewId: string;
  reviewer: Reviewer;
  starRating: StarRating;
  comment: string;
  createTime: string;
  updateTime: string;
  reviewReply: ReviewReply;
}
