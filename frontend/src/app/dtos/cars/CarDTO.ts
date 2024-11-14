export class CarDTO {
  id: number;
  year: number;
  licensePlate: string;
  model: string;
  color: string;

  constructor(object: any = {}) {
    this.id = object && object.id;
    this.year = object && object.year;
    this.licensePlate = object && object.licensePlate;
    this.model = object && object.model;
    this.color = object && object.color;
  }
}
