import React, { useState } from 'react';
import './AuthModal.scss';
import { GrFormClose } from 'react-icons/gr';
import Login from './Login';

type AuthModalProps = {
    dialogRef: React.MutableRefObject<HTMLDialogElement | null>;
};

const AuthModal: React.FC<AuthModalProps> = ({ dialogRef }) => {
    const closeModal = () => {
        if (dialogRef.current) {
            dialogRef.current.close();
        }
    };

    const [isNewUser, setIsNewUser] = useState<boolean>(true);

    const onFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
    };

    return (
        // <div>
        <dialog className='modal' ref={dialogRef}>
            <div className='modal-content'>
                <h3 className='modal-title'>
                    {isNewUser
                        ? 'Реєстрація'
                        : 'Ввійдіть у свій обліковий запис'}
                </h3>
                <button className='close' onClick={closeModal}>
                    <GrFormClose />
                </button>

                <form
                    className='modal-form'
                    onSubmit={(event) => onFormSubmit(event)}
                >
                    {isNewUser && (
                        <>
                            <input
                                className='modal-input'
                                type='text'
                                placeholder='Enter your first name'
                            />
                            <input
                                className='modal-input'
                                type='text'
                                placeholder='Enter your last name'
                            />
                            <input
                                className='modal-input'
                                type='number'
                                placeholder='Enter your phone number'
                            />
                        </>
                    )}
                    <input
                        className='modal-input'
                        type='email'
                        placeholder='Enter your email'
                    />
                    <input
                        className='modal-input'
                        type='password'
                        placeholder='Password'
                    />
                    {isNewUser && (
                        <input
                            className='modal-input'
                            type='password'
                            placeholder='Repeat the password'
                        />
                    )}

                    <button className='modal-submit' type='submit'>
                        {isNewUser ? 'Sign Up' : 'Sign In'}
                    </button>
                </form>
                <div className='modal-switch_msg'>
                    <p>
                        {isNewUser
                            ? 'У вас вже є обліковий запис? Тоді '
                            : 'Якщо у вас немає облікового запису, '}
                        <span>
                            <button
                                onClick={(event) => setIsNewUser(!isNewUser)}
                                className='modal-switch'
                            >
                                {isNewUser
                                    ? 'увійдіть до нього'
                                    : 'зареєструйтесь'}
                            </button>
                        </span>
                    </p>
                </div>
            </div>
        </dialog>
        // </div>
    );
};

export default AuthModal;
