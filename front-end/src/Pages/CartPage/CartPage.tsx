import React, { useContext, useEffect, useState } from "react";
import "./CartPage.scss";
import { CartService } from "../../Services/Cart/Cart.service";
import { AuthContext } from "../../Contexts/AuthContext";

export const CartPage: React.FC = () => {
  const { token } = useContext(AuthContext);
  const [goods, setGoods] = useState([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);

  useEffect(() => {
    CartService.getGoods(token).then((data) => {
      setGoods(data);
      setIsLoading(false);
    });
  }, []);

  return (
    <main className="cart">
      <h3 className="cart__title">The cart</h3>

      {goods.length ? (
        goods.map((good) => {
          return <article className="cart__good"></article>;
        })
      ) : (
        <section className="cart__cart-without-goods">
          <p className="cart-without-goods__title">There are not goods</p>

          <button className="cart-without-goods__link-to-catalog">
            Go to catalog
          </button>
        </section>
      )}
    </main>
  );
};
