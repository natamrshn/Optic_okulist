import React, { useRef, useState } from "react";

import { GiHamburgerMenu as BurgerMenu } from "react-icons/gi";
import { BsCart3 as CartIcon } from "react-icons/bs";
import { FaUserAlt as UserIcon } from "react-icons/fa";
import "./Header.scss";

import Navigation from "../Navigation/Navigation";
import ShoppingCart from "../ShoppingCart/ShoppingCart";
import Container from "../ui/Container/Container";
import { Link } from "react-router-dom";
import HeaderSearch from "../HeaderSearch/HeaderSearch";
import AuthModal from "../AuthModal/AuthModal";
import useOutsideClick from "../../lib/hooks/useOutsideClick";
import { useSelector } from "react-redux";
import { selectCart } from "../../redux/cart/cartSlice";

const Header = () => {
    const [isPopUpOpened, setIsPopUpOpened] = useState<boolean>(false);
    const [isCartOpened, setIsCartOpened] = useState<boolean>(false);

    const dialogRef = useRef<HTMLDialogElement | null>(null);
    const cartRef = useOutsideClick<HTMLDivElement>(() =>
        setIsCartOpened(false)
    );

    const openAuth = () => {
        if (dialogRef.current) {
            dialogRef.current.showModal();
        }
    };

    const closeAuth = () => {
        if (dialogRef.current) {
            dialogRef.current.close();
        }
    };

    const cartProducts = useSelector(selectCart);

    const amountOfProducts = cartProducts.reduce(
        (acc, product) => acc + product.amount,
        0
    );

    return (
        <>
            <header className="header">
                <Container>
                    <div className="header-wrapper">
                        <Link to="/" className="logo">
                            Optic Store
                        </Link>

                        <div className="header-buttons">
                            <HeaderSearch />
                            <button
                                className="header-cart-button"
                                onClick={() => setIsCartOpened(!isCartOpened)}
                            >
                                {amountOfProducts > 0 && (
                                    <p className="header-products-amount">
                                        {amountOfProducts}
                                    </p>
                                )}
                                <CartIcon />
                            </button>

                            {isCartOpened && (
                                <div ref={cartRef}>
                                    <ShoppingCart
                                        setIsCartOpened={setIsCartOpened}
                                    />
                                </div>
                            )}

                            <button onClick={openAuth}>
                                <UserIcon />
                            </button>

                            <button
                                className="header-burger"
                                onClick={() => setIsPopUpOpened(!isPopUpOpened)}
                            >
                                <BurgerMenu />
                            </button>
                        </div>
                    </div>
                    <Navigation
                        isPopUpOpened={isPopUpOpened}
                        setIsPopUpOpened={setIsPopUpOpened}
                    />
                </Container>
            </header>

            <AuthModal dialogRef={dialogRef} closeAuth={closeAuth} />
        </>
    );
};

export default Header;
