import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-calendario',
  templateUrl: './calendario.component.html',
  styleUrls: ['./calendario.component.css']
})
export class CalendarioComponent {
  @Input() temporada: 'invierno' | 'verano' | null = null;

}
