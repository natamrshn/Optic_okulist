import "./AsideMenu.scss";
import React from "react";
import { Link } from "react-router-dom";

export interface Props {
  closeMenu: () => void;
}

export const AsideMenu: React.FC<Props> = ({ closeMenu }) => {
  return (
    <aside>
      <Link to="/" onClick={closeMenu}>
        Головна
      </Link>
      <Link to="/about-us" onClick={closeMenu}>
        Про нас
      </Link>
      <Link to="/services" onClick={closeMenu}>
        Послуги
      </Link>
      <Link to="/useful-articles" onClick={closeMenu}>
        Корисне
      </Link>
      <Link to="/catalog" onClick={closeMenu}>
        Каталог
      </Link>
      <Link to="/reviews" onClick={closeMenu}>
        Відгуки
      </Link>
      <Link to="/contacts" onClick={closeMenu}>
        Контакти
      </Link>
      <hr />
      <Link to="/authorization" onClick={closeMenu}>
        Кабінет
      </Link>
    </aside>
  );
};
