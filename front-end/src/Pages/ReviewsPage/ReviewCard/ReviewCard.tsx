import "./ReviewCard.scss";
import { FC } from "react";

interface Props {
  reviewText: string;
  nickname: string;
  linkToUserAvatar: string;
  rating: number;
}

export const ReviewCard: FC<Props> = ({ rating }) => {
  return <article className="reviews__review"></article>;
};
