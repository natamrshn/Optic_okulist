import { FC, useEffect } from "react";
import "./MainPage.scss";

export const MainPage: FC = () => {
  useEffect(() => {
    document.title = "Окуліст - головна";
  }, []);

  return <div className="main">Here will be a description of this shop</div>;
};
