import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LateralComponent } from './lateral/lateral.component';
import { JornadaComponent } from './jornada/jornada.component';
import { HorarioComponent } from './horario/horario.component';
import { FichajeComponent } from './fichaje/fichaje.component';

const routes: Routes = [
  {path: 'login', component:LoginComponent},
  {path: 'lateral', component:LateralComponent},
  {path: '', component:FichajeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
