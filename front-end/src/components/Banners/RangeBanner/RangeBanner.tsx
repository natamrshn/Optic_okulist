import { useEffect, useState } from 'react';
import Container from "../../ui/Container/Container";
import "./RangeBanner.scss";
import { Link } from "react-router-dom";
import { getCategories } from '../../../lib/api/getCategories';
import { Category } from '../../../types/Category';

const RangeBanner = () => {
    const [categories, setCategories] = useState<Category[]>([]);
    const temporaryToken = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTcwMTI1NjI3NCwiZXhwIjo0ODU0ODU2Mjc0fQ.H70OT57MFgIjedqYyNd9t7B5fBnWMaMIWQHb1eoY1Zw';

    
    useEffect(() => {
        async function fetchCategories() {
            const categoriesFromAPI = await getCategories(temporaryToken);
            setCategories(categoriesFromAPI);
            console.log(categoriesFromAPI)
        }

        fetchCategories();
    }, []);

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
