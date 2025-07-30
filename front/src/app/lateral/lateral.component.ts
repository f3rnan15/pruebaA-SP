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
  userName: string = "";

  constructor(private authService: AuthService) {}

  setSection(section: 'jornada' | 'horario' | 'fichaje'){
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
