import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LateralComponent } from './lateral/lateral.component';
import { JornadaComponent } from './jornada/jornada.component';
import { HorarioComponent } from './horario/horario.component';
import { FichajeComponent } from './fichaje/fichaje.component';

const routes: Routes = [
  {path: '/login', component:LoginComponent},
  {path: '/lateral', component:LateralComponent},
  {path: '/jornada', component:JornadaComponent},
  {path: '/horario', component:HorarioComponent},
  {path: '/fichaje', component:FichajeComponent},
  {path: '', pathMatch:'full', redirectTo:'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
