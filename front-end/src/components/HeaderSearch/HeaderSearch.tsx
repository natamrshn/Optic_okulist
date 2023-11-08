import React from "react";
import { BsSearch } from "react-icons/bs";

const HeaderSearch = () => {
    const onFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
    }
    return (
        <form className="header-search" onSubmit={(event) => onFormSubmit(event)}>
            <input type="text" />

            <button>
                <BsSearch />
            </button>
        </form>
    );
};

export default HeaderSearch;
