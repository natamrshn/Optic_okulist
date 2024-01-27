import React, { ReactNode } from 'react';
import './Button.scss';
import classNames from "classnames";

export interface ButtonProps {
  callback: () => void;
  children: ReactNode;
  isSecondary?: boolean;
}

export const Button: React.FC<ButtonProps> = ({ callback, children, isSecondary = false }) => {
  return (
    <button
      onClick={callback}
      className={classNames( "button", { "button--secondary": isSecondary } )}
    >
      { children }
    </button>
  );
}
