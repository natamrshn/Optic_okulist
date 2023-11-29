import React, { useEffect, useState } from "react";
import Card from "../Card/Card";

import './Catalog.scss';

import { Product } from '../../types/Product';
import { GetTypes } from '../../types/GetTypes';
import { getProducts } from '../../lib/api/getProducts';
import { useLocation } from 'react-router-dom';

const Catalog = () => {
    const [products, setProducts] = useState<Product[]>([]);

    const temporaryToken = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBleGFtcGxlLmNvbSIsImlhdCI6MTcwMTI1NjI3NCwiZXhwIjo0ODU0ODU2Mjc0fQ.H70OT57MFgIjedqYyNd9t7B5fBnWMaMIWQHb1eoY1Zw';

    const location = useLocation()
    const pathname = location.pathname.toLowerCase();

    useEffect(() => {
        async function fetchCategories() {
            let type: GetTypes = GetTypes.GLASSES;

            if (pathname.includes('lenses')) {
                type = GetTypes.CONTACT_LENSES
            } else if (pathname.includes('liquids')) {
                type = GetTypes.LIQUIDS
            }
            const productsFromAPI = await getProducts(temporaryToken, type);
            setProducts(productsFromAPI);
        }

        fetchCategories();
    }, [pathname])
    return (
        <section className='catalog'>
            {products.map((product) => {
                return <Card key={product.id} product={product} />;
            })}
        </section>
    );
};

export default Catalog;
