import React, { ReactNode } from 'react';
import './RadioRadioButton.scss';
import classNames from "classnames";

interface Props {
  callback: () => void;
  children: ReactNode;
  isSecondary?: boolean;
}

export const RadioButton: React.FC<Props> = ({ callback, children, isSecondary = false }) => {
  return (
    <button
      onClick={callback}
      className={classNames( "button", { "button--secondary": isSecondary } )}
    >
      { children }
    </button>
  );
}
