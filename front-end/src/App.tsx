import { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.scss";

import { Header } from "./Components/Common/Header";
import { Footer } from "./Components/Common/Footer";

import { MainPage } from "./Pages/MainPage";
import { AboutUsPage } from "./Pages/AboutUsPage";
import { ReviewsPage } from "./Pages/ReviewsPage";
import { ServicesPage } from "./Pages/ServicesPage";
import { UsefulArticlesPage } from "./Pages/UsefulArticlesPage";
import { ContactsPage } from "./Pages/ContactsPage";
import { LocalStorage } from "./Utils/LocalStorageUtil";

import { AuthContext } from "./Contexts/AuthContext";

function App() {
  const [token, setToken] = useState(LocalStorage.getToken() || "");

  return (
    <div className="App">
      <AuthContext.Provider value={{ token, setToken }}>
        <Header />

        <Router>
          <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/about-us" element={<AboutUsPage />} />
            <Route path="/reviews" element={<ReviewsPage />} />
            <Route path="/services" element={<ServicesPage />} />
            <Route path="/contacts" element={<UsefulArticlesPage />} />
            <Route path="/useful-articles" element={<ContactsPage />} />
          </Routes>
        </Router>
      </AuthContext.Provider>

      <Footer />
    </div>
  );
}

export default App;
