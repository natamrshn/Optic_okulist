import React from "react";
import { CartProduct, selectProductAmount } from "../../redux/cart/cartSlice";
import { useSelector } from "react-redux";
import { validatePrice } from "../../lib/helpers/validatePrice";

type ShoppingCartProductProps = {
    product: CartProduct;
};

const ShoppingCartProduct: React.FC<ShoppingCartProductProps> = ({
    product,
}) => {
    const productAmount = useSelector(selectProductAmount(product.id));
    const productPrice = validatePrice(product.price * productAmount);

    return (
        <li className="cart-product">
            <div className="wrapper">
                <div>
                    {/* <img src={product.imgUrl} alt={product.name} /> */}
                    <h3>{product.name}</h3>
                </div>
                <div>
                    <p className="cart-price">${productPrice}</p>

                    <div className="cart-amount">
                        <p style={{ fontSize: "12px", color: "gray" }}>
                            {productAmount} items
                        </p>
                    </div>
                </div>
            </div>
        </li>
    );
};

export default ShoppingCartProduct;
