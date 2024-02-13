import "./ReviewerComponent.scss";
import { FC } from "react";
import { Review, Reviewer } from "../../../../Services/Reviews/Types/Review";

interface Props {
  reviewer: Reviewer;
  rating: Review["starRating"];
}

export const ReviewerComponent: FC<Props> = ({ reviewer, rating }) => {
  return (
    <div className="review__reviewer">
      {reviewer.displayName}

      <img
        className="reviewer__avatar"
        src={reviewer.profilePhotoUrl}
        alt={reviewer.displayName + "avatar"}
      />

      {rating}
    </div>
  );
};
