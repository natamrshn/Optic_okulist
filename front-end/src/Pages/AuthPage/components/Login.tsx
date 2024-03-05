import { ChangeEvent, FC, useContext, useState } from "react";

import { Input } from "../../../Components/UI/Input";
import { Button } from "../../../Components/UI/Button";

import { AuthContext } from "../../../Contexts/AuthContext";

import { AuthService } from "../../../Services/Auth/Auth.service";
import { LoginRequest } from "../../../Services/Auth/Types/ILogin";
import { LocalStorage } from "../../../Utils/LocalStorageUtil";

export const Login: FC = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [showPassword, setShowPassword] = useState<boolean>(false);
  const { token, setToken } = useContext(AuthContext);

  const changePasswordHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const changeEmailHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const LoginHandler = () => {
    if (!email.trim().length || !password.trim().length) {
      // here I need to set an error

      return;
    }

    const body: LoginRequest = {
      email,
      password,
    };

    AuthService.login(body)
      .then((res) => {
        LocalStorage.setToken(res.token);
        setToken(res.token);
      })
      .catch((error) => {
        // here I need to handle an error
      });
  };

  const RedirectToRecoveryPageHandler = () => {
    // Here I need to redirect to a page where user can reset its password
  };

  const visibilityPasswordToggleHandler = () => {
    setShowPassword((prev) => !prev);
  };

  return (
    <div className="login">
      <Input
        type="email"
        value={email}
        setValue={changeEmailHandler}
        error={false}
      >
        example.ua@gmail.com
      </Input>

      <Input
        type={showPassword ? "text" : "password"}
        value={password}
        setValue={changePasswordHandler}
        error={false}
      >
        Введіть пароль
      </Input>

      <Button callback={LoginHandler}>Увійти</Button>

      <Button callback={RedirectToRecoveryPageHandler} isSecondary={true}>
        Забули пароль?
      </Button>
    </div>
  );
};
