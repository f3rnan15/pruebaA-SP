import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LateralComponent } from './lateral/lateral.component';
import { JornadaComponent } from 'src/app/jornada/jornada.component';
import { HorarioComponent } from './horario/horario.component';
import { FichajeComponent } from './fichaje/fichaje.component';
import { RouterModule } from '@angular/router';
import { IncidenciaComponent } from './incidencia/incidencia.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CalendarioComponent } from './calendario/calendario.component';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/token';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LateralComponent,
    JornadaComponent,
    HorarioComponent,
    FichajeComponent,
    IncidenciaComponent,
    CalendarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([]),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
