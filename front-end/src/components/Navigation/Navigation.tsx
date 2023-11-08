import React from "react";

import "./Navigation.scss";

// import { BsSunglasses, BsEyeglasses } from "react-icons/bs";
import { Link } from "react-router-dom";
import Container from "../ui/Container/Container";
// import {gl} from "../../../public/*jpg"

type CategoriesProps = {
    isPopUpOpened: boolean;
    setIsPopUpOpened: React.Dispatch<React.SetStateAction<boolean>>;
};

const Navigation: React.FC<CategoriesProps> = ({ isPopUpOpened }) => {
    return (
        <nav className="navigation">
            <Container>
                <ul
                    className={`navigation-categories ${
                        isPopUpOpened ? "open" : ""
                    }`}
                >
                    <li className="navigation-item">
                        <Link className="navigation-link" to={`/eyeglasses`}>
                            Окуляри
                        </Link>
                    </li>
                    <li className="navigation-item">
                        <Link className="navigation-link" to={`/sunglasses`}>
                            Сонцезахисні Окуляри
                        </Link>
                    </li>
                    <li className="navigation-item">
                        <Link className="navigation-link" to={`/contact-lenses`}>
                            Контактні лінзи
                        </Link>
                    </li>
                    <li className="navigation-item">
                        <Link className="navigation-link" to={`/accessories`}>
                            Аксесуари
                        </Link>
                    </li>
                    <li className="navigation-item">
                        <Link className="navigation-link" to={`/doctor`}>
                            Запис до лікаря
                        </Link>
                    </li>
                </ul>
            </Container>
        </nav>
    );
};

export default Navigation;
