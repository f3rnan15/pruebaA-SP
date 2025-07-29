import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-lateral',
  templateUrl: './lateral.component.html',
  styleUrls: ['./lateral.component.css']
})
export class LateralComponent {

  isCollapsed = false;
  activeSection: 'jornada' | 'horario' | 'fichaje' = 'jornada';

  constructor(private authService: AuthService) {}

  setSection(section: 'jornada' | 'horario' | 'fichaje'){
    this.activeSection = section;
  }

  toggleSidebar(){
    this.isCollapsed = !this.isCollapsed;
  }

}
