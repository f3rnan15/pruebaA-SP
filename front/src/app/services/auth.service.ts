import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Users } from 'src/assets/user';
import { environment, headers } from '../enviroments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public loggedUser!: Users;

  constructor(private http: HttpClient) {}

  public getUser(email: string): Observable<Users> {
    return this.http.get<Users>(`${environment.backendHost}/users/${email}`, { headers });
  }

}


