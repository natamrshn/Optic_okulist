import React from "react";
import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { BsCart3 as CartIcon } from "react-icons/bs";

import { CartProduct, addToCart } from "../../redux/cart/cartSlice";

import { Product } from "../../types/Product";

import "./Card.scss";

type CardProps = {
    product: Product;
};

const Card: React.FC<CardProps> = ({ product }) => {
    const { name, price, id } = product;
    const dispatch = useDispatch();

    const productForCart = {
        id,
        name,
        price,
        amount: 1,
    };

    const handleAddToCart = (product: CartProduct) => {
        dispatch(addToCart(product));
    };

    return (
        <div className="product">
            <div className="product-photo">
                <img src="https://placehold.co/600x400" alt={name + " photo"} />
            </div>

            <Link to={`/product/${product.id}`} className="product-name">
                {name}
            </Link>

            <div className="product-bottom">
                <p className="product-price">${price}</p>
                <button onClick={() => handleAddToCart(productForCart)}>
                    <CartIcon />
                </button>
            </div>
        </div>
    );
};

export default Card;
