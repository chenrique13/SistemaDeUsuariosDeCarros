export class ErrorDTO {
  message: string;
  errorCode: number;

  constructor(object: any = {}) {
    this.message = object && object.message;
    this.errorCode = object && object.errorCode;
  }
}
