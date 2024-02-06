import React, { useEffect, useState } from "react";
import "./CatalogPage.scss";

// Services
import { GlassesService } from "../../Services/Glasses/Glasses.service";
import { LiquidService } from "../../Services/Liquid/Liquid.service";

// Types
import { GlassesPOSTResponse } from "../../Services/Glasses/Types/GlassesPOST";
import { LiquidGETResponse } from "../../Services/Liquid/Types/LiquidGET";
import { LenseService } from "../../Services/Lense/Lense.service";
import { LenseGetResponse } from "../../Services/Lense/Types/LenseGet";

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
  const [lensesList, setLensesList] = useState<LenseGetResponse[]>([]);
  const [liquidsList, setLiquidsList] = useState<LiquidGETResponse[]>([]);

  useEffect(() => {
    switch (currentCategory) {
      case Category.GLASSES: {
        if (!glassesList.length) {
          GlassesService.getAll().then((glasses) => setGlassesList(glasses));
        }

        break;
      }

      case Category.LIQUIDS: {
        if (!liquidsList.length) {
          LiquidService.getAll().then((liquids) => setLiquidsList(liquids));
        }

        break;
      }

      case Category.LENSES: {
        if (!lensesList.length) {
          LenseService.getAll().then((lenses) => setLensesList(lenses));
        }
      }
    }
  }, [currentCategory]);

  return <main className="catalog"></main>;
};
