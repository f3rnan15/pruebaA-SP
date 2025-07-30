import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LateralComponent } from './lateral/lateral.component';
import { JornadaComponent } from './jornada/jornada.component';
import { HorarioComponent } from './horario/horario.component';
import { FichajeComponent } from './fichaje/fichaje.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { IncidenciaComponent } from './incidencia/incidencia.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LateralComponent,
    JornadaComponent,
    HorarioComponent,
    FichajeComponent,
    IncidenciaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([]),
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
