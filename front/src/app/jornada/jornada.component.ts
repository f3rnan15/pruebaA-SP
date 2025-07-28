import { Component } from '@angular/core';

@Component({
  selector: 'app-jornada',
  templateUrl: './jornada.component.html',
  styleUrls: ['./jornada.component.css']
})
export class JornadaComponent {
  trabajando : boolean = false;

  fichajes: {
    entrada: string | null;
    lugarEntrada: string;
    salida: string | null;
    lugarSalida: string;
    ausencia: string;
  }[] = [];

  ficharEntrada(): void{
    const ahora = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    this.fichajes.push({
      entrada: ahora,
      lugarEntrada: 'Oficina',
      salida: null,
      lugarSalida: '',
      ausencia: '—'
    });
    this.trabajando = true;
  }

  ficharSalida(): void{
    const ahora = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    const ultimo = this.fichajes[this.fichajes.length - 1];
    if (ultimo && !ultimo.salida) {
      ultimo.salida = ahora;
      ultimo.lugarSalida = 'Oficina';
    }
    this.trabajando = false;
  }

  refrescarDatos(){} // Trae la informacion de fichajes del dia actual del back
}
