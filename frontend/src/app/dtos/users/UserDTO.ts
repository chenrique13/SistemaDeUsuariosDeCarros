import { CarDTO } from '../cars/CarDTO';

export class UserDTO {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  birthday: Date;
  login: string;
  password: string;
  phone: string;
  cars: CarDTO[];

  constructor(object: any = {}) {
    this.id = object && object.id;
    this.firstName = object && object.firstName;
    this.lastName = object && object.lastName;
    this.email = object && object.email;
    this.birthday = object && object.birthday;
    this.login = object && object.login;
    this.password = object && object.password;
    this.phone = object && object.phone;
    this.cars = object && object.cars;
  }
}
