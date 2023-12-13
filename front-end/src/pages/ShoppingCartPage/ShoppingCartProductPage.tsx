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
    const { id, name, price, imageUrl } = product;
    const productAmount = useSelector(selectProductAmount(id));
    const totalProductPrice = validatePrice(price * productAmount);
    const dispatch = useDispatch();

    const incrementAmount = () => {
        dispatch(addToCart(product));
    };

    const decrementAmount = () => {
        dispatch(decreaseProductAmount(id));
    };
    
    const deleteItem = () => {
        dispatch(removeFromCart(id))
    }

    return (
        <li className="product-item">
            <div className="wrapper">
                <div className="product-info">
                    <img src={imageUrl} alt={name} />
                    <h3>{name}</h3>
                </div>

                <div className="buttons-wrapper">
                    <p>${totalProductPrice}</p>

                    <ProductAmountControl
                        incrementAmount={incrementAmount}
                        decrementAmount={decrementAmount}
                        amount={productAmount}
                    />
                    
                    <button onClick={() => deleteItem()} className="remove"><GrFormClose /></button>
                </div>
            </div>
        </li>
    );
};

export default ShoppingCartPageProduct;
