import React from "react";

type ProductAmountControlProps = {
    incrementAmount: () => void;
    decrementAmount: () => void;
    amount: number;
};

const ProductAmountControl: React.FC<ProductAmountControlProps> = ({
    incrementAmount,
    decrementAmount,
    amount,
}) => {
    const maxAmount = amount === 99;
    const minAmount = amount === 1;

    return (
        <div className="amount">
            <button
                disabled={minAmount}
                onClick={() => decrementAmount()}
                className="amount-button"
            >
                -
            </button>
            <p className="amount-caption">{amount}</p>
            <button
                disabled={maxAmount}
                onClick={() => incrementAmount()}
                className="amount-button"
            >
                +
            </button>
        </div>
    );
};

export default ProductAmountControl;
