import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { environment } from './enviroments/enviroments';

export interface User {
  userId: number;
  firstName: string;
  lastName: string;
  email: string;
  userPassword: string;
  checkins: Check[];
}

export interface Check {
  checkId: number;
  user: User;
  timestamp: string;
  inside: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class Checkin_Service {
  private postUrl = '/checkin/new';
  private getUrl = '/checkin/daysCheckins'

  constructor(private http: HttpClient) {}

  createCheck(userId: number): Observable<Check> { // post
    return this.http.post<Check>(`${environment.backendHost}/checkin/new`, userId)
  }

  getChecks(date: string, userId: number) :Observable<Check[]> {
    const params = new HttpParams().set('date', date).set('userId',userId);
    let headers = new HttpHeaders().set('Authorization', localStorage.getItem('token')!);
    return this.http.get<Check[]>(`${environment.backendHost}/checkin/daysCheckin`, { params, headers });
  }

  putChecks(id: string, date: string, inside: boolean){
    const url = `${environment.backendHost}/checkin/${id}`;
    const body = {
      timestamp: date,
      inside: inside
    };

    return this.http.put(url, body);
  }

}
