import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const jwtToken = localStorage.getItem('jwtToken');

  const modifiedReq = jwtToken
    ? req.clone({ setHeaders: { Authorization: jwtToken } })
    : req;

  return next(modifiedReq);
};
