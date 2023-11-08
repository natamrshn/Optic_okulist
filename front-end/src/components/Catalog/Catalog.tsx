import React from "react";
import Card from "../Card/Card";

import './Catalog.scss';
import { prodsWithIdsIds } from '../ShoppingCart/ShoppingCart';

export type Glasses = {
    name: string;
    price: number;
    description: string;
    imgUrl: string;
};
export type GlassesList = Glasses[];

const Catalog = () => {
    return (
        <section className='catalog'>
            {prodsWithIdsIds.map((product) => {
                return <Card key={product.id} product={product} />;
            })}
        </section>
    );
};

export default Catalog;
