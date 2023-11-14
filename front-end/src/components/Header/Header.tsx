import React, { useRef, useState } from 'react';

import { GiHamburgerMenu as BurgerMenu } from 'react-icons/gi';
import { BsCart3 as CartIcon } from 'react-icons/bs';
import { FaUserAlt as UserIcon } from 'react-icons/fa';
import './Header.scss';

import Navigation from '../Navigation/Navigation';
import ShoppingCart from '../ShoppingCart/ShoppingCart';
import Container from '../ui/Container/Container';
import { Link } from 'react-router-dom';
import HeaderSearch from '../HeaderSearch/HeaderSearch';
import AuthModal from '../AuthModal/AuthModal';

const Header = () => {
    const [isPopUpOpened, setIsPopUpOpened] = useState<boolean>(false);
    const [isCartOpened, setIsCartOpened] = useState<boolean>(false);
    const [isModalOpened, setIsModalOpened] = useState<boolean>(false);

    const dialogRef = useRef<HTMLDialogElement | null>(null);

    const openModal = () => {
        if (dialogRef.current) {
            dialogRef.current.showModal();
        }
    };

    return (
        <>
            <header className='header'>
                <Container>
                    <div className='header-wrapper'>
                        <Link to='/' className='logo'>
                            Optic Store
                        </Link>

                        <div className='header-buttons'>
                            <HeaderSearch />
                            <button
                                onClick={() => setIsCartOpened(!isCartOpened)}
                            >
                                <CartIcon />
                            </button>

                            {isCartOpened && (
                                <ShoppingCart
                                    setIsCartOpened={setIsCartOpened}
                                />
                            )}

                            <button onClick={openModal}>
                                <UserIcon />
                            </button>

                            <button
                                className='header-burger'
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

            <AuthModal
                dialogRef={dialogRef}
            />
        </>
    );
};

export default Header;
