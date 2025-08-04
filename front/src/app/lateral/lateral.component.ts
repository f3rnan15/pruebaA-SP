import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-lateral',
  templateUrl: './lateral.component.html',
  styleUrls: ['./lateral.component.css']
})
export class LateralComponent {
  
  userEmail: string="";
    
  selectedSeason: 'invierno' | 'verano' | null = null;

  isCollapsed = false;

  activeSection: 'calendario'| 'jornada' | 'horario' | 'fichaje' = 'jornada';

  constructor(private authService: AuthService) {}

  ngOnInit() {
    const decoded = this.authService.getDecodedToken();
    if (decoded) {
      this.userEmail = decoded.sub;
      // Si has añadido más campos como nombre o rol, también puedes mostrarlos
    }
  }

  setSection(section: 'calendario'|'jornada' | 'horario' | 'fichaje'){
    this.activeSection = section;
  }

  setCalendarioDesdeHorario(season: 'invierno' | 'verano') {
    this.selectedSeason = season;
    this.setSection('calendario');
  }

  toggleSidebar(){
    this.isCollapsed = !this.isCollapsed;
  }

  userLogged():boolean {
    if(this.userEmail == ""){
      return false;
    } else {
      return true;
    }
  }

}
