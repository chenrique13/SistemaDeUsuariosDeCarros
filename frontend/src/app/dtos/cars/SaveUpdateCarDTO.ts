export class SaveUpdateCarDTO {
  year: number;
  licensePlate: string;
  model: string;
  color: string;

  constructor(object: any = {}) {
    this.year = object && object.year;
    this.licensePlate = object && object.licensePlate;
    this.model = object && object.model;
    this.color = object && object.color;
  }
}
