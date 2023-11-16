import React from 'react';

type AuthSwitchMessageProps = {
    isUserNew: boolean;
    setIsUserNew: React.Dispatch<React.SetStateAction<boolean>>;
    setError: React.Dispatch<React.SetStateAction<string>>;
};

const AuthSwitchMessage: React.FC<AuthSwitchMessageProps> = ({
    isUserNew,
    setIsUserNew,
    setError,
}) => {
    return (
        <div className='modal-switch_msg'>
            <p>
                {isUserNew
                    ? 'У вас вже є обліковий запис? Тоді '
                    : 'Якщо у вас немає облікового запису, '}
                <span>
                    <button
                        onClick={(event) => {
                            setIsUserNew(!isUserNew);
                            setError('');
                        }}
                        className='modal-switch'
                    >
                        {isUserNew ? 'увійдіть до нього' : 'зареєструйтесь'}
                    </button>
                </span>
            </p>
        </div>
    );
};

export default AuthSwitchMessage;
