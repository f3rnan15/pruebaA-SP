import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';

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

  createCheck(): Observable<Check> { // post
    return this.http.post<Check>(this.postUrl, null)
  }

  getChecks(date: string) :Observable<Check[]> {
    const params = new HttpParams().set('date', date)
    return this.http.get<Check[]>(this.getUrl, { params })
  }

  putChecks(id: string, date: string, inside: boolean){
    const url = `/checkin/${id}`;
    const body = {
      timestamp: date,
      inside: inside
    };

    return this.http.put(url, body);
  }

}