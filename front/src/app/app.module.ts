import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LateralComponent } from './lateral/lateral.component';
import { JornadaComponent } from './jornada/jornada.component';
import { HorarioComponent } from './horario/horario.component';
import { FichajeComponent } from './fichaje/fichaje.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LateralComponent,
    JornadaComponent,
    HorarioComponent,
    FichajeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
