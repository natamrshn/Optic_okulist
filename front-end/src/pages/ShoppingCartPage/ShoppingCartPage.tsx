import React from 'react';


import Container from '../../components/ui/Container/Container';
import { glassesProducts } from '../../components/ShoppingCart/ShoppingCart';
import './ShoppingCartPage.scss';

const ShoppingCartPage = () => {
    const totalPrice = Math.round(glassesProducts.slice(0, 5).reduce((totalPrice, product) => totalPrice + product.price, 0));

    return (
        <main className='cart-page'>
            <Container>
                <h2 style={{fontSize: '24px'}}>Cart</h2>
                <ul>
                    {glassesProducts.slice(0, 5).map((product, index) => (
                        <li className='cart-product' key={index}>
                            <div className='wrapper'>
                                <img src={product.imgUrl} alt={product.name} />
                                <h3>{product.name}</h3>
                                <p className='cart-price'>${product.price}</p>
                            </div>
                        </li>
                    ))}
                </ul>
                <h3 className='cart-total'>Total: ${totalPrice}</h3>
                <button>Purchase</button>
            </Container>
        </main>
    );
};

export default ShoppingCartPage;
