import { Component, numberAttribute } from '@angular/core';
import { Check, Checkin_Service } from 'src/app/checkin.service';
import { TmplAstDeferredBlockLoading } from '@angular/compiler';

@Component({
  selector: 'app-jornada',
  templateUrl: './jornada.component.html',
  styleUrls: ['./jornada.component.css']
})
export class JornadaComponent {

  constructor(private Checkin_Service: Checkin_Service) { }

  working!: boolean

  totalHours!: number

  workingHours!: string

  selectedDate: string = new Date().toISOString().split('T')[0]

  visualSelectedDate!: string

  fichajes: {
    id_entrada: number;
    id_salida: number | null;
    incidencia: boolean;
    entradaFecha: string | null;
    entradaHora: string | null;
    lugarEntrada: string;
    salidaFecha: string | null;
    salidaHora: string | null;
    lugarSalida: string;
    ausencia: string;
  }[] = [];

  ficharEntrada(): void {
    this.Checkin_Service.createCheck().subscribe({
      next: (check) => {
        console.log('Fichaje recibido del backend:', check);
        console.log('working: ', check.inside)
        this.fichajes.push({
          id_entrada: check.checkId,
          incidencia: false,
          entradaFecha: this.formatFecha(check.timestamp),
          entradaHora: this.formatHora(check.timestamp),
          lugarEntrada: 'Oficina',
          id_salida: null,
          salidaFecha: null,
          salidaHora: null,
          lugarSalida: '',
          ausencia: '-'
        });
      },
      error: (err) => {
        console.error('Error al fichar entrada', err);
      }
    });
    this.working = true;
  }

  ficharSalida(): void {
    this.Checkin_Service.createCheck().subscribe({
      next: (check) => {
        console.log('working: ', check.inside)
        const ultimo = this.fichajes[this.fichajes.length - 1];
        if (ultimo && ultimo.salidaHora == null) {
          ultimo.id_salida = check.checkId
          ultimo.salidaFecha = this.formatFecha(check.timestamp)
          ultimo.salidaHora = this.formatHora(check.timestamp);
          ultimo.lugarSalida = 'Oficina';
        }
      }
    })
    this.working = false;
  }

  refrescarDatos(): void {

    this.Checkin_Service.getChecks(this.selectedDate).subscribe({
      next: (checks) => {
        console.log('Fichajes obtenidos del backend:', checks);

        this.fichajes = [];

        for (let i = 0; i < checks.length; i++) {
          const check = checks[i];

          if (check.inside) { // entrada
            this.fichajes.push({
              incidencia: false,
              id_entrada: check.checkId,
              entradaFecha: this.formatFecha(check.timestamp),
              entradaHora: this.formatHora(check.timestamp),
              lugarEntrada: 'Oficina',
              id_salida: null,
              salidaFecha: null,
              salidaHora: null,
              lugarSalida: '',
              ausencia: '-'
            });
          } else { // salida
            const ultimaEntrada = this.fichajes.find(f => f.salidaHora === null);
            if (ultimaEntrada) {
              ultimaEntrada.id_salida = check.checkId
              ultimaEntrada.salidaFecha = this.formatFecha(check.timestamp)
              ultimaEntrada.salidaHora = this.formatHora(check.timestamp);
              ultimaEntrada.lugarSalida = 'Oficina';
            }
          }
        }
        if (checks.length != 0) {
          this.working = checks[checks.length - 1].inside
          console.log('working status: ' + this.working)
        } else {
          this.working = false;
        }
        this.totalWorkingHours(checks)
        console.log('Horas trabajadas: ' + this.totalHours)
        this.workingHours = this.numToHours(this.totalHours)
        this.visualSelectedDate = this.longDate(this.selectedDate)
      },
      error: (err) => {
        console.error('Error al obtener fichajes del día:', err);
      }
    });
  } // Trae la informacion de fichajes del dia actual del back

  formatFecha(timestamp: string): string { // YYYY-MM-DD
    const fecha = new Date(timestamp);
    return fecha.toISOString().split('T')[0];
  }

  formatHora(timestamp: string): string { // HH-MM
    const fecha = new Date(timestamp);
    const horas = fecha.getHours().toString().padStart(2, '0');
    const minutos = fecha.getMinutes().toString().padStart(2, '0');
    return `${horas}:${minutos}`;
  }

  ngOnInit() {
    this.refrescarDatos()
  }

  guardarIncidencia(
    id_entrada: number | null,
    id_salida: number | null,
    entradaFecha: string | null,
    entradaHora: string | null,
    salidaFecha: string | null,
    salidaHora: string | null
  ): void {
    if (id_entrada !== null && entradaFecha && entradaHora) {
      const entradaTimestamp = `${entradaFecha}T${entradaHora}:00`; // formato ISO
      this.Checkin_Service.putChecks(id_entrada.toString(), entradaTimestamp, true).subscribe({
        next: () => console.log('Entrada corregida con éxito'),
        error: (err) => console.error('Error al guardar entrada', err)
      });
    }

    if (id_salida !== null && salidaFecha && salidaHora) {
      const salidaTimestamp = `${salidaFecha}T${salidaHora}:00`; // formato ISO
      this.Checkin_Service.putChecks(id_salida.toString(), salidaTimestamp, false).subscribe({
        next: () => console.log('Salida corregida con éxito'),
        error: (err) => console.error('Error al guardar salida', err)
      });
    }
  }

  salirIncidencia(incidencia: boolean | null) {
    incidencia = false
    this.refrescarDatos()
  }

  changeDays(days: number): void {
    const date = new Date(this.selectedDate);
    date.setDate(date.getDate() + days);
    this.selectedDate = date.toISOString().split('T')[0];
    this.refrescarDatos();
  }

  totalWorkingHours(checks: Check[]) {
    let adding: boolean = false
    let enter: string = ''
    let out: string = ''
    this.totalHours = 0
    for (let i = 0; i < checks.length; i++) {
      if (checks[i].inside && !adding) {
        adding = true
        enter = checks[i].timestamp
      } else if (!checks[i].inside && adding) {
        adding = false
        out = checks[i].timestamp
        console.log('entrada: ' + enter + ', salida: ' + out)
        this.totalHours += this.calcularDuracion(enter, out)
        enter = ''
        out = ''
      }
    }
  }

  calcularDuracion(entrada: string, salida: string): number {
    const inicio = new Date(entrada);
    const fin = new Date(salida);

    const ms = fin.getTime() - inicio.getTime();
    const horas = ms / (1000 * 60 * 60);
    console.log('horas: ' + horas)
    return horas;
  }

  numToHours(hours: number): string {
    let h = Math.floor(hours)
    let m = Math.round((hours - h) * 60)

    let hStr = h.toString()
    let mStr = m.toString()
    return `${hStr}h  ${mStr}min`
  }

  longDate(fechaISO: string): string {
    let fecha = new Date(fechaISO);
    let opciones: Intl.DateTimeFormatOptions = {
      weekday: 'long',
      day: 'numeric',
      month: 'long'
    };

    return fecha.toLocaleDateString('es-ES', opciones);
  }

}
