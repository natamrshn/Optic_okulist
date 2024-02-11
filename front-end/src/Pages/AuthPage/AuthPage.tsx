import { FC, useState } from "react";
import "./AuthPage.scss";
import { Login } from "./components/Login";
import { Registration } from "./components/Registration";

enum Pages {
  REGISTRATION,
  LOGIN,
}

export const AuthPage: FC = () => {
  const [currentPage, setCurrentPage] = useState<Pages>(Pages.LOGIN);

  return (
    <div className="authPage">
      <button onClick={() => setCurrentPage(Pages.REGISTRATION)}>
        Registration
      </button>
      <button onClick={() => setCurrentPage(Pages.LOGIN)}>Login</button>

      {currentPage === Pages.LOGIN && <Login />}
      {currentPage === Pages.REGISTRATION && <Registration />}
    </div>
  );
};
