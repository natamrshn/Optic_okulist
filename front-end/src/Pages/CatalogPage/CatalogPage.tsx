import React, { useEffect, useState } from "react";
import "./CatalogPage.scss";

enum Category {
  GLASSES,
  LENSES,
  LIQUIDS,
}

export const CatalogPage: React.FC = () => {
  const [currentCategory, setCurrentCategory] = useState<Category>(
    Category.GLASSES
  );
  const [glassesList, setGlassesList] = useState([]);
  const [lensesList, setLensesList] = useState([]);
  const [liquidsList, setLiquidsList] = useState([]);

  useEffect(() => {}, [currentCategory]);

  return <main className="catalog"></main>;
};
