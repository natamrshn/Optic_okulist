import "./UserProfilePage.scss";
import { FC, useEffect, useState } from "react";
import { MyData } from "./Pages/MyData";
import { MyOrders } from "./Pages/MyOrders";
import { MyBonuses } from "./Pages/MyBonuses";
import { MyFavorites } from "./Pages/MyFavorites";

enum Pages {
  MyData,
  MyOrders,
  MyBonuses,
  Favorites,
}

export const UserProfilePage: FC = () => {
  const [currentPage, setCurrentPage] = useState<Pages>(Pages.MyData);

  const [orders, setOrders] = useState<[]>([]);
  const [bonuses, setBonuses] = useState<[]>([]);
  const [favorites, setFavorites] = useState<[]>([]);

  useEffect(() => {
    //  Here I should load info about user: firstname, lastname, email, phone, city, etc.
    //  Also, I should load info about its orders, favorites, bonuses
  }, []);

  return (
    <main className="user-profile">
      <div className="user-profile__pages"></div>

      {currentPage === Pages.MyData && <MyData />}
      {currentPage === Pages.MyOrders && <MyOrders orders={orders} />}
      {currentPage === Pages.MyBonuses && <MyBonuses bonuses={bonuses} />}
      {currentPage === Pages.Favorites && <MyFavorites favorites={favorites} />}
    </main>
  );
};
