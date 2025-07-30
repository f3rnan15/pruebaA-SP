import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-lateral',
  templateUrl: './lateral.component.html',
  styleUrls: ['./lateral.component.css']
})
export class LateralComponent {
  
    
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

}
