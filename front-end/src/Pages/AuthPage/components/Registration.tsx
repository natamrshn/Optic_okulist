import { ChangeEvent, FC, useEffect, useState } from "react";

// Components
import { Input } from "../../../Components/UI/Input";
import { Button } from "../../../Components/UI/Button";

// Utils
import { Capitalize } from "../../../Utils/Text/Capitalize";
import { RegistrationRequest } from "../../../Services/Auth/Types/IRegistration";
import { AuthService } from "../../../Services/Auth/Auth.service";

export const Registration: FC = () => {
  const [name, setName] = useState<string>("");
  const [surname, setSurname] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [repeatPassword, setRepeatPassword] = useState<string>("");
  const [phoneNumber, setPhoneNumber] = useState<string>("");

  const [isPasswordsMatch, setIsPasswordMatch] = useState<boolean>(false);

  const changeNameHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setName(Capitalize(e.target.value.trim()));
  };

  const changeSurnameHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setSurname(Capitalize(e.target.value.trim()));
  };

  const changeEmailHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value.trim());
  };

  const changePasswordHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value.trim());
  };

  const changeRepeatPasswordHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setRepeatPassword(e.target.value.trim());
  };

  const changePhoneNumberHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setPhoneNumber(e.target.value);
  };

  useEffect(() => {
    if (password === repeatPassword) {
      setIsPasswordMatch(true);
    }
  }, [password, repeatPassword]);

  const registrateHandler = () => {
    const body: RegistrationRequest = {
      firstName: name,
      lastName: surname,
      email,
      password,
      repeatPassword,
      phoneNumber: +phoneNumber,
    };

    AuthService.registrate(body).then((response) => {
      const { firstName, lastName, email, phoneNumber } = response;

      // here I also need to get a jwt-token
    });
  };

  return (
    <div className="registration">
      <Input value={name} setValue={changeNameHandler} error={false}>
        Марія
      </Input>

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

      <Button callback={registrateHandler}>Зареєструватись</Button>
    </div>
  );
};
