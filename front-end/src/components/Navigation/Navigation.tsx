import React from "react";

import "./Navigation.scss";

import { Link } from "react-router-dom";
import Container from "../ui/Container/Container";
import { useFetchCategories } from "../../lib/hooks/useFetchCategories";

type CategoriesProps = {
    isPopUpOpened: boolean;
    setIsPopUpOpened: React.Dispatch<React.SetStateAction<boolean>>;
};

const Navigation: React.FC<CategoriesProps> = ({ isPopUpOpened }) => {
    const categories = useFetchCategories();

    return (
        <nav className="navigation">
            <Container>
                <ul
                    className={`navigation-categories ${
                        isPopUpOpened ? "open" : ""
                    }`}
                >
                    {categories.map(category => (
                        <li className="navigation-item" key={category.id}>
                            <Link className="navigation-link" to={`/${category.name}`}>
                                {category.name}
                            </Link>
                    </li>
                    ))}

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
