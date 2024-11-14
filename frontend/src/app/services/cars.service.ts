import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CarDTO } from '../dtos/cars/CarDTO';
import { SaveUpdateCarDTO } from '../dtos/cars/SaveUpdateCarDTO';

@Injectable({
  providedIn: 'root',
})
export class CarsService {
  private apiUrl = `${environment.backendUrl}/cars`;

  constructor(private http: HttpClient) {}

  getCars(): Observable<Array<CarDTO>> {
    return this.http.get<Array<CarDTO>>(this.apiUrl);
  }

  getCarById(carId: number): Observable<CarDTO> {
    return this.http.get<CarDTO>(`${this.apiUrl}/${carId}`);
  }

  createCar(saveUpdateCarDTO: SaveUpdateCarDTO): Observable<CarDTO> {
    return this.http.post<CarDTO>(this.apiUrl, saveUpdateCarDTO);
  }

  updateCar(
    carId: number,
    saveUpdateCarDTO: SaveUpdateCarDTO
  ): Observable<CarDTO> {
    return this.http.put<CarDTO>(`${this.apiUrl}/${carId}`, saveUpdateCarDTO);
  }

  deleteCar(carId: number): Observable<CarDTO> {
    return this.http.delete<CarDTO>(`${this.apiUrl}/${carId}`);
  }
}
