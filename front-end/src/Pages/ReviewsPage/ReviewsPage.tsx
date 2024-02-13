import { FC, useEffect, useState } from "react";
import "./ReviewsPage";
import { ReviewsGETResponse } from "../../Services/Reviews/Types/ReviewsGet";
import { ReviewCard } from "./ReviewCard/ReviewCard";

export const ReviewsPage: FC = () => {
  const [reviews, setReviews] = useState<ReviewsGETResponse["reviews"]>([]);

  useEffect(() => {
    document.title = "Окуліст - відгуки";
  }, []);

  return (
    <div className="reviews">
      {reviews.map((review) => {
        return <ReviewCard review={review} key={review.reviewId} />;
      })}
    </div>
  );
};
