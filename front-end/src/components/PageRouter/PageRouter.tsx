import React from "react";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import HomePage from "../../pages/HomePage/HomePage";
import Header from "../Header/Header";
import Footer from "../Footer/Footer";

import ShoppingCartPage from "../../pages/ShoppingCartPage/ShoppingCartPage";
import CatalogPage from "../../pages/CatalogPage/CatalogPage";
import ProductPage from "../../pages/ProductPage/ProductPage";
import DoctorPage from "../../pages/DoctorPage/DoctorPage";

const PageRouter = () => {
    return (
        <>
            <Router>
                <Header />
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/cart" element={<ShoppingCartPage />} />
                    <Route path="/doctor" element={<DoctorPage />} />
                    <Route path="/:catalog" element={<CatalogPage />} />
                    <Route path="/product/:id" element={<ProductPage />} />
                </Routes>
                <Footer />
            </Router>
        </>
    );
};

export default PageRouter;
