import React from "react";
import ReactDOM from "react-dom/client";
import "./styles/main.scss";
import PageRouter from "./components/PageRouter/PageRouter";

const root = ReactDOM.createRoot(
    document.getElementById("root") as HTMLElement
);
root.render(
    <React.StrictMode>
        <PageRouter />
    </React.StrictMode>
);
