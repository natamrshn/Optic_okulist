import React, { useEffect, useState } from "react";
import "./CatalogPage.scss";
import { GlassesService } from "../../Services/Glasses/Glasses.service";
import { GlassesPOSTResponse } from "../../Services/Glasses/Types/GlassesPOST";

enum Category {
  GLASSES,
  LENSES,
  LIQUIDS,
}

export const CatalogPage: React.FC = () => {
  const [currentCategory, setCurrentCategory] = useState<Category>(
    Category.GLASSES
  );
  const [glassesList, setGlassesList] = useState<GlassesPOSTResponse[]>([]);
  const [lensesList, setLensesList] = useState([]);
  const [liquidsList, setLiquidsList] = useState([]);

  useEffect(() => {
    switch (currentCategory) {
      case Category.GLASSES: {
        if (!glassesList.length) {
          GlassesService.getAll().then((glasses) => setGlassesList(glasses));
        }
      }
    }
  }, [currentCategory]);

  return <main className="catalog"></main>;
};
