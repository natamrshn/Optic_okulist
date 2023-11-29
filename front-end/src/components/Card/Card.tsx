import React from 'react';

import { BsCart3 as CartIcon } from 'react-icons/bs';
import './Card.scss';
import { Link } from 'react-router-dom';
import { Product } from '../../types/Product';

type CardProps = {
    product: Product;
};

const Card: React.FC<CardProps> = ({ product }) => {
    const { name,  price } = product;
    return (
        <div className='product'>
            <div className='product-photo'>
                <img src="https://placehold.co/600x400" alt={name + ' photo'} />
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
