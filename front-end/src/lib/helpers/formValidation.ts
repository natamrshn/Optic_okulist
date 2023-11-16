import React from 'react';

export const INITIAL_USER_DATA = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    email: '',
    password: '',
    repeatPassword: '',
};

export type UserData = typeof INITIAL_USER_DATA;

export function validateName(name: string) {
    return name.trim().length > 2;
}

export function validatePhoneNumber(phoneNumber: string) {
    const validNumber = phoneNumber.split('').every(n => !isNaN(+n));
    return validNumber && phoneNumber.trim().length > 8;
}

export function validatePassword(password: string) {
    const containsLetters = /[a-zA-Z]/.test(password);
    const containsNumbers = /\d/.test(password);
    const isValidLength = password.length >= 8;

    return isValidLength && containsLetters && containsNumbers;
}

export function validateEmail(email: string) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

export function validatePasswordMatch(
    password: string,
    repeatPassword: string
) {
    return password !== '' && password === repeatPassword;
}

export function validateForm(
    userData: UserData,
    isNewUser: boolean,
    setError: React.Dispatch<React.SetStateAction<string>>
) {
    if (isNewUser) {
        if (
            !validateName(userData.firstName) ||
            !validateName(userData.lastName)
        ) {
            setError(
                "Ім'я та прізвище мають мати довжину щонайменше 2 символи."
            );
            return false;
        } else if (!validatePhoneNumber(userData.phoneNumber)) {
            setError('Номер телефону має мати довжину щонайменше 10 символів та містити лише цифри.');
            return false;
        } else if (!validateEmail(userData.email)) {
            setError('Введіть правильну електронну адресу.');
            return false;
        } else if (!validatePassword(userData.password)) {
            setError(
                'Пароль має містити літера та цифрри і мати довжину щонайменше 8 символів.'
            );
            return false;
        } else if (
            !validatePasswordMatch(userData.password, userData.repeatPassword)
        ) {
            setError('Паролі не співпадають.');
            return false;
        } else {
            setError('');
            return true;
        }
    } else {
        setError('');
        return true;
    }
}

export const UserInputFields = [
    {
        type: 'text',
        name: 'firstName',
        placeholder: "Ім'я",
        forNewUser: true,
    },
    {
        type: 'text',
        name: 'lastName',
        placeholder: 'Прізвище',
        forNewUser: true,
    },
    {
        type: 'tel',
        pattern: '[0-9]*',
        name: 'phoneNumber',
        placeholder: 'Номер телефону',
        forNewUser: true,
    },
    {
        type: 'email',
        name: 'email',
        placeholder: 'Email',
        forNewUser: false,
    },
    {
        type: 'password',
        name: 'password',
        placeholder: 'Пароль',
        forNewUser: false,
    },
    {
        type: 'password',
        name: 'repeatPassword',
        placeholder: 'Повторіть пароль',
        forNewUser: true,
    },
];
