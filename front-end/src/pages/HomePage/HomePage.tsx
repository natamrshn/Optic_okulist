import React from "react";
import RangeBanner from "../../components/Banners/RangeBanner/RangeBanner";
import AddsBanner from "../../components/Banners/AddsBanner/AddsBanner";
import DoctorBanner from "../../components/Banners/DoctorBanner.tsx/DoctorBanner";

const HomePage = () => {
    return (
        <>
            <RangeBanner />
            <AddsBanner />
            <DoctorBanner />
        </>
    );
};

export default HomePage;