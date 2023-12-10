import React from "react";


import Container from "../../components/ui/Container/Container";
import "./ShoppingCartPage.scss";
import { selectCart } from '../../redux/cart/cartSlice';
import ShoppingCartPageProduct from "./ShoppingCartProductPage";
import { useSelector } from "react-redux";

const ShoppingCartPage = () => {
    const products = useSelector(selectCart);

    return (
        <main className="cart-page">
            <Container>
                <h2 style={{fontSize: "24px"}}>Cart</h2>
                
                <ul className="shopping-cart">
                    {products.map((product) => (
                        <ShoppingCartPageProduct key={product.id} product={product}/>
                    ))}
                </ul>

                <button className="purchase">Purchase</button>
            </Container>
        </main>
    );
};

export default ShoppingCartPage;
