import React from 'react';
import { Glasses } from '../Catalog/Catalog';

import { BsCart3 as CartIcon } from 'react-icons/bs';
import './Card.scss';
import { Link } from 'react-router-dom';

type CardProps = {
    product: Glasses & {id: string};
};

const Card: React.FC<CardProps> = ({ product }) => {
    const { name, imgUrl, price } = product;
    return (
        <div className='product'>
            <div className='product-photo'>
                <img src={imgUrl} alt={name + ' photo'} />
            </div>
            
            <Link to={`/product/${product.id}`} className='product-name'>{name}</Link>

            <div className='product-bottom'>
                <p className='product-price'>${price}</p>
                <button><CartIcon /></button>
            </div>
        </div>
    );
};

export default Card;
