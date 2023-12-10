import React from "react";
import {
    CartProduct,
    addToCart,
    decreaseProductAmount,
    removeFromCart,
    selectProductAmount,
} from "../../redux/cart/cartSlice";
import { useDispatch, useSelector } from "react-redux";
import { GrFormClose } from "react-icons/gr";
import ProductAmountControl from "./ProductAmountControl";
import { validatePrice } from "../../lib/helpers/validatePrice";

type ShoppingCartPageProductProps = {
    product: CartProduct;
};

const ShoppingCartPageProduct: React.FC<ShoppingCartPageProductProps> = ({
    product,
}) => {
    const amount = useSelector(selectProductAmount(product.id));
    const price = validatePrice(product.price * amount);
    const dispatch = useDispatch();

    const incrementAmount = () => {
        dispatch(addToCart(product));
    };

    const decrementAmount = () => {
        dispatch(decreaseProductAmount(product.id));
    };
    
    const deleteItem = () => {
        dispatch(removeFromCart(product.id))
    }

    return (
        <li className="product-item">
            <div className="wrapper">
                <div className="product-info">
                    {/* <img src={product.imgUrl} alt={product.name} /> */}
                    <h3>{product.name}</h3>
                </div>

                <div className="buttons-wrapper">
                    <p>${price}</p>

                    <ProductAmountControl
                        incrementAmount={incrementAmount}
                        decrementAmount={decrementAmount}
                        amount={amount}
                    />

                    <button onClick={() => deleteItem()} className="remove"><GrFormClose /></button>
                </div>
            </div>
        </li>
    );
};

export default ShoppingCartPageProduct;
