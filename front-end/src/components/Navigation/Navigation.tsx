import React, { useEffect, useState } from "react";

import "./Navigation.scss";

import { Link } from "react-router-dom";
import Container from "../ui/Container/Container";
import { Category } from '../../types/Category';
import { getCategories } from '../../lib/api/getCategories';
import { GetTypes } from '../../types/GetTypes';

type CategoriesProps = {
    isPopUpOpened: boolean;
    setIsPopUpOpened: React.Dispatch<React.SetStateAction<boolean>>;
};

const Navigation: React.FC<CategoriesProps> = ({ isPopUpOpened }) => {
    const [categories, setCategories] = useState<Category[]>([]);
    const temporaryToken = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTcwMTI1NjI3NCwiZXhwIjo0ODU0ODU2Mjc0fQ.H70OT57MFgIjedqYyNd9t7B5fBnWMaMIWQHb1eoY1Zw';

    
    useEffect(() => {
        async function fetchCategories() {
            const categoriesFromAPI = await getCategories(temporaryToken);
            setCategories(categoriesFromAPI);
        }

        fetchCategories();
    }, [])
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
