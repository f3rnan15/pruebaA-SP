import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Schedule } from 'src/assets/horario';
import { Observable } from 'rxjs';
import { environment, headers } from '../enviroments/enviroments';


@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

    constructor(private http: HttpClient) { }

  public getSchedules(): Observable<Array<Schedule>> {
    return this.http.get<Array<Schedule>>(`${environment.backendHost}/schedules`, { headers });
  }
}
