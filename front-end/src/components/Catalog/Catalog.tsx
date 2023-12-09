import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import Card from "../Card/Card";

import "./Catalog.scss";

import { Product } from "../../types/Product";
import { GetTypes } from "../../types/GetTypes";
import { getProducts } from "../../lib/api/getProducts";

const Catalog = () => {
    const [products, setProducts] = useState<Product[]>([]);

    const location = useLocation();
    const pathname = location.pathname.toLowerCase();

    useEffect(() => {
        async function fetchProducts() {
            let type: GetTypes = GetTypes.GLASSES;

            if (pathname.includes("lenses")) {
                type = GetTypes.CONTACT_LENSES;
            } else if (pathname.includes("liquids")) {
                type = GetTypes.LIQUIDS;
            }
            const productsFromAPI = await getProducts(type);
            setProducts(productsFromAPI);
        }

        fetchProducts();
    }, [pathname]);

    return (
        <section className="catalog">
            {products.map((product) => {
                return <Card key={product.id} product={product} />;
            })}
        </section>
    );
};

export default Catalog;
