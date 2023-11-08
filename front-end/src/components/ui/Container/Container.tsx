import React from "react";
import "./Container.scss";

const Container = ({ children }: { children: React.ReactNode }) => {
    const containerStyles = {
        maxWidth: "1280px",
        marginInline: "auto",
    };

    return <div className="container">{children}</div>;
};

export default Container;
