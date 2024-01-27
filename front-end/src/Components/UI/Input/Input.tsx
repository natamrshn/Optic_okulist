import React from "react";
import classNames from "classnames";
import './Input.scss';

export interface InputProps {
  type?: 'text' | 'password' | 'email' | 'tel' | 'number';
  value: string | number;
  setValue: (e: React.ChangeEvent<HTMLInputElement>) => void;
  children?: string;
  error: boolean;
  disabled?: boolean;
}

export const Input: React.FC<InputProps> = ({ type = 'text',value, setValue, children, error, disabled = false }) => {
  const classes = classNames(
    "input",
    {
      "input--error": error,
      "input--filled": Boolean(`${value}`.length)
    }
  );

  return (
    <input
      className={classes}
      type={type}
      placeholder={children}
      value={value}
      onChange={e => setValue(e)}
      disabled={disabled}
    />
  );
}
