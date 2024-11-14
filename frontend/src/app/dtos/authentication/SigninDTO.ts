export class SigninDTO {
  login: string;
  password: string;

  constructor(object: any = {}) {
    this.login = object && object.login;
    this.password = object && object.password;
  }
}
