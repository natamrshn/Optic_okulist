import {useContext} from "react";
import {AuthContext} from "../../Contexts/AuthContext";
import { Navigate, Outlet, useLocation } from "react-router-dom";

export const PrivateRoute = () => {
  const { token } = useContext(AuthContext);
  const location = useLocation();

  if (!token) {
    return <Navigate to="/" state={{ from: location }} replace />
  }

  return <Outlet />
}