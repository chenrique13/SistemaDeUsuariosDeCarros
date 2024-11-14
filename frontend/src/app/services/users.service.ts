import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { SigninDTO } from '../dtos/authentication/SigninDTO';
import { UserDTO } from '../dtos/users/UserDTO';
import { UserInfoDTO } from '../dtos/users/UserInfoDTO';
import { SaveUserDTO } from '../dtos/users/SaveUserDTO';
import { UpdateUserDTO } from '../dtos/users/UpdateUserDTO';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private apiUrl = environment.backendUrl;
  private apiUrlUsers = `${this.apiUrl}/users`;

  constructor(private http: HttpClient) {}

  signin(signinDTO: SigninDTO): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.apiUrl}/signin`, signinDTO, {
      observe: 'response',
    });
  }

  me() {
    return this.http.get<UserInfoDTO>(`${this.apiUrl}/me`);
  }

  getUsers(): Observable<Array<UserDTO>> {
    return this.http.get<Array<UserDTO>>(this.apiUrlUsers);
  }

  getUserById(userId: string): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.apiUrlUsers}/${userId}`);
  }

  createUser(saveUserDTO: SaveUserDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(this.apiUrlUsers, saveUserDTO);
  }

  updateUser(userId: number, updateUserDTO: UpdateUserDTO): Observable<UserDTO> {
    return this.http.put<UserDTO>(`${this.apiUrlUsers}/${userId}`, updateUserDTO);
  }

  deleteUser(userId: number): Observable<UserDTO> {
    return this.http.delete<UserDTO>(`${this.apiUrlUsers}/${userId}`);
  }
}
