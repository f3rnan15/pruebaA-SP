import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Users } from 'src/assets/user';
import { environment, headers } from '../enviroments/enviroments';
import { AuthResponse } from 'src/assets/authResponse';
import { AuthRequest } from 'src/assets/authRequest';
import { DecodedToken } from 'src/assets/decodedToken';
import { jwtDecode } from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedUser!: Users;

  constructor(private http: HttpClient) {}

  public getloggedUser(): Users{
    return this.loggedUser;
  }

  public setloggedUser(loggedUser: Users){
    this.loggedUser=loggedUser;
  }

  public getUser(email: string): Observable<Users> {
    return this.http.get<Users>(`${environment.backendHost}/users/${email}`, { headers });
  }

  public login(authRequest: AuthRequest): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${environment.backendHost}/users/login`, authRequest);
  }

  public registerUser(user: Users): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${environment.backendHost}/users/register`, user);
  }

  public getDecodedToken(): DecodedToken | null {
    const token = localStorage.getItem('token');
    if (!token) return null;

    try {
      return jwtDecode<DecodedToken>(token);
    } catch (error) {
      console.error('Error al decodificar el token', error);
      return null;
    }
  }

}


