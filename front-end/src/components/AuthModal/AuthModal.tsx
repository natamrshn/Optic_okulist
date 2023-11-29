import React, { useState } from 'react';
import './AuthModal.scss';
import { GrFormClose } from 'react-icons/gr';
import {
    INITIAL_USER_DATA,
    UserData,
    UserInputFields,
    validateForm,
} from '../../lib/helpers/formValidation';
import AuthSwitchMessage from './AuthSwitchMessage';
import { login, register } from '../../lib/api/authRequest';

type AuthModalProps = {
    dialogRef: React.MutableRefObject<HTMLDialogElement | null>;
    closeAuth: () => void;
};

const AuthModal: React.FC<AuthModalProps> = ({ dialogRef, closeAuth }) => {
    const [error, setError] = useState<string>('');
    const [isUserNew, setIsUserNew] = useState<boolean>(true);
    const [userData, setUserData] = useState(INITIAL_USER_DATA);

    const formReset = () => {
        setUserData(INITIAL_USER_DATA);
    };

    const onFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        if (isUserNew) {
            const validationError = validateForm(userData);
            if (!validationError) {
                register(userData);
                formReset();
                setIsUserNew(false);
            } else {
                setError(validationError);
                return;
            }
        } else {
            login({email: userData.email, password: userData.password});
            formReset();
            closeAuth();
        }
    };

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setError('');
        setUserData({
            ...userData,
            [name]: value.trim(),
        });
    };

    return (
        <dialog className='modal' ref={dialogRef}>
            <div className='modal-content'>
                <h3 className='modal-title'>
                    {isUserNew
                        ? 'Реєстрація'
                        : 'Ввійдіть у свій обліковий запис'}
                </h3>
                <button className='close' onClick={closeAuth}>
                    <GrFormClose />
                </button>

                <form
                    className='modal-form'
                    onSubmit={(event) => onFormSubmit(event)}
                >
                    {UserInputFields.filter((field) =>
                        !isUserNew ? !field.forNewUser : true
                    ).map((field, index) => (
                        <input
                            key={index}
                            className='modal-input'
                            type={field.type}
                            name={field.name}
                            placeholder={field.placeholder}
                            value={userData[field.name as keyof UserData]}
                            onChange={handleInputChange}
                        />
                    ))}
                    <p className={`modal-caption ${error ? ' error' : ''}`}>
                        {error}
                    </p>

                    <button
                        disabled={!!error}
                        className='modal-submit'
                        type='submit'
                    >
                        {isUserNew ? 'Зараєструватись' : 'Увійти'}
                    </button>
                </form>
                <AuthSwitchMessage
                    isUserNew={isUserNew}
                    setIsUserNew={setIsUserNew}
                    setError={setError}
                />
            </div>
        </dialog>
    );
};

export default AuthModal;
