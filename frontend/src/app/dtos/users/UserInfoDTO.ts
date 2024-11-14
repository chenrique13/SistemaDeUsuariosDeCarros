import { CarDTO } from '../cars/CarDTO';

export class UserInfoDTO {
  firstName: string;
  lastName: string;
  email: string;
  birthday: Date;
  login: string;
  phone: string;
  cars: CarDTO[];
  createdAt: Date;
  lastLogin: Date;

  constructor(object: any = {}) {
    this.firstName = object && object.firstName;
    this.lastName = object && object.lastName;
    this.email = object && object.email;
    this.birthday = object && object.birthday;
    this.login = object && object.login;
    this.phone = object && object.phone;
    this.cars = object && object.cars;
    this.createdAt = object && object.createdAt;
    this.lastLogin = object && object.lastLogin;
  }
}
