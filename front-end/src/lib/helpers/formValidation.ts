export const INITIAL_USER_DATA = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    email: '',
    password: '',
    repeatPassword: '',
};

export type UserData = typeof INITIAL_USER_DATA;

export type UserLoginData = {
    email: string,
    password: string,
}

export function validateName(name: string): boolean {
    return name.trim().length >= 2;
}

export function validatePhoneNumber(phoneNumber: string): boolean {
    const validNumber = phoneNumber.split('').every((n) => !isNaN(+n));
    return validNumber && phoneNumber.trim().length > 8;
}

export function validatePassword(password: string): boolean {
    const containsLetters = /[a-zA-Z]/.test(password);
    const containsNumbers = /\d/.test(password);
    const isValidLength = password.length >= 8;

    return isValidLength && containsLetters && containsNumbers;
}

export function validateEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

export function validatePasswordMatch(
    password: string,
    repeatPassword: string
): boolean {
    return password !== '' && password === repeatPassword;
}

export function validateForm(userData: UserData): string {
    const {
        firstName,
        lastName,
        email,
        phoneNumber,
        password,
        repeatPassword,
    } = userData;
    switch (true) {
        case !validateName(firstName):
            return "Ім'я має мати довжину щонайменше 2 символи.";
        case !validateName(lastName):
            return 'Прізвище має мати довжину щонайменше 2 символи.';
        case !validatePhoneNumber(phoneNumber):
            return 'Номер телефону має мати довжину щонайменше 10 символів та містити лише цифри.';
        case !validateEmail(email):
            return 'Введіть правильну електронну адресу.';
        case !validatePassword(password):
            return 'Пароль має містити літеру та цифри і мати довжину щонайменше 8 символів.';
        case !validatePasswordMatch(password, repeatPassword):
            return 'Паролі не співпадають.';
        default:
            return '';
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