import React, { useState } from 'react';

type ShoppingCartProductProps = {
    product: {
        name: string;
        price: number;
        description: string;
        imgUrl: string;
        amount: number;
    };
};

const ShoppingCartProduct: React.FC<ShoppingCartProductProps> = ({
    product,
}) => {
    const [productAmount, setProductAmount] = useState(product.amount);
    const productPrice = (product.price * productAmount).toFixed(2);
    
    return (
        <li className='cart-product'>
            <div className='wrapper'>
                <div>
                    <img src={product.imgUrl} alt={product.name} />
                    <h3>{product.name}</h3>
                </div>
                <div>
                    <p className='cart-price'>${productPrice}</p> 

                    <div className='cart-amount'>
                        <button className='amount-button' onClick={() => setProductAmount(prevState => prevState - 1)}>-</button>
                        <p>{productAmount}</p>
                        <button className='amount-button' onClick={() => setProductAmount(prevState => prevState + 1)}>+</button>
                    </div>
                </div>
            </div>
        </li>
    );
};

export default ShoppingCartProduct;
