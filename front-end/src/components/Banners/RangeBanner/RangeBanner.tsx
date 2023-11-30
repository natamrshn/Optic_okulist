import React from "react";
import Container from "../../ui/Container/Container";
import "./RangeBanner.scss";
import { Link } from "react-router-dom";
import { useFetchCategories } from "../../../lib/hooks/useFetchCategories";

const RangeBanner = () => {
    const categories = useFetchCategories();

    return (
        <aside className="banner">
            <Container>
                <h2>Наш ассортимент</h2>
                <ul className="list">
                    {categories.map((category) => {
                        return (
                            <li key={category.id} className="item">
                                <Link to={`/${category.name}`} className="item-content">
                                    <img className="item-image" src="https://placehold.co/600x400" alt="Category"/>
                                    <h3 className="item-title">{category.name}</h3>
                                </Link>
                            </li>
                        );
                    })}
                </ul>
            </Container>
        </aside>
    );
};

export default RangeBanner;
