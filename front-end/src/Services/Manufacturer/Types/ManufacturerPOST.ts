export interface ManufacturerPOSTRequest {
  name: string;
  colorsIds: number[];
  additionsIds: number[];
  cylinderId: number;
  degreeId: number;
  diopterId: number;
  spheresIds: number[];
}

export interface ManufacturerPOSTResponse {
  id: number;
  name: string;
  colorsId: number[];
  cylinderId: number;
  degreeId: number;
  diopterId: number;
  spheresIds: number[];
}
