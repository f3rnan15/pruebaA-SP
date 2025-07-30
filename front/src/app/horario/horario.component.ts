import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Schedule } from 'src/assets/horario';
import { ScheduleService } from '../services/schedule.service';

@Component({
  selector: 'app-horario',
  templateUrl: './horario.component.html',
  styleUrls: ['./horario.component.css']
})
export class HorarioComponent implements OnInit{
  constructor(private service: ScheduleService){

  }
  ngOnInit(): void {
    this.getHorario();
  }
  @Output() irACalendario = new EventEmitter<'invierno' | 'verano'>();
  horarios: Schedule[] = [];

  getHorario(){
    this.service.getSchedules().subscribe({
      next: value => {
        this.horarios = value;
      },
      error: err => {
        console.log(err);
      }
    })
  }
  
  getSeason(description: string): 'invierno' | 'verano' | null {
    if (description.includes('Summer')) return 'verano';
    if (description.includes('Winter')) return 'invierno';
    return null;
  }

  onHorarioClick(horario: any) {
    const season = this.getSeason(horario.description);
    if (season) {
      this.irACalendario.emit(season);
    }
  }

}
