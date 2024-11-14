import { HttpInterceptorFn } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { NotificationService } from '../services/notification.service';
import { inject } from '@angular/core';
import { ErrorDTO } from '../dtos/common/ErrorDTO';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const notificationService = inject(NotificationService);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {     
      let errorMessage = '';

      if (error.error instanceof ErrorEvent) {
        errorMessage = `Erro: ${error.error.message}`;
      } else {
        let errorDTO: ErrorDTO =  new ErrorDTO(error.error);

        errorMessage = `Error Code: ${errorDTO.errorCode} - Message: ${errorDTO.message}`;
      }

      notificationService.showError(errorMessage);

      return throwError(() => new Error(errorMessage));
    })
  );
};
