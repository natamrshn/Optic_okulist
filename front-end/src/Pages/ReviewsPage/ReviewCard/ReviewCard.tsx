import "./ReviewCard.scss";
import { FC } from "react";
import { Review } from "../../../Services/Reviews/Types/Review";
import { ReviewerComponent } from "./ReviewerComponent";

interface Props {
  review: Review;
}

export const ReviewCard: FC<Props> = ({ review }) => {
  const { comment, reviewer, starRating } = review;

  return (
    <article className="reviews__review review">
      <p className="reviews__text">{comment}</p>

      <ReviewerComponent reviewer={reviewer} rating={starRating} />
    </article>
  );
};
