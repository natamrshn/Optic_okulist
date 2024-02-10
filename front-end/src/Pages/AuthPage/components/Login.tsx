import { ChangeEvent, FC, useState } from "react";
import { Input } from "../../../Components/UI/Input";
import { Button } from "../../../Components/UI/Button";
import { AuthService } from "../../../Services/Auth/Auth.service";
import { LoginRequest } from "../../../Services/Auth/Types/ILogin";

export const Login: FC = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const changePasswordHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const changeEmailHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const LoginHandler = () => {
    if (!email.length || !password.length) {
      // here I need to set an error

      return;
    }

    const body: LoginRequest = {
      email,
      password,
    };

    AuthService.login(body)
      .then(() => {
        // here I need to get a jwt-token
      })
      .catch((error) => {
        // here I need to handle an error
      });
  };

  const RedirectToRecoveryPageHandler = () => {
    // Here I need to redirect to page where user can reset its password
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
        type="password"
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
