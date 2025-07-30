import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-lateral',
  templateUrl: './lateral.component.html',
  styleUrls: ['./lateral.component.css']
})
export class LateralComponent {
  
  userName: string="";
    
  selectedSeason: 'invierno' | 'verano' | null = null;

  setCalendarioDesdeHorario(season: 'invierno' | 'verano') {
    this.selectedSeason = season;
    this.setSection('calendario');
  }


  isCollapsed = false;

  activeSection: 'calendario'| 'jornada' | 'horario' | 'fichaje' = 'jornada';

  constructor(private authService: AuthService) {}

  setSection(section: 'calendario'|'jornada' | 'horario' | 'fichaje'){
    this.activeSection = section;
  }

  toggleSidebar(){
    this.isCollapsed = !this.isCollapsed;
  }

  comprobar() {
    console.log(this.authService.loggedUser);
  }

  userLogged():boolean {
    if(this.authService.loggedUser.firstName == undefined){
      return false;
    } else {
      this.userName=this.authService.loggedUser.firstName;
      return true;
    }
  }
}
