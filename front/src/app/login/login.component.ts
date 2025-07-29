import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Users } from 'src/assets/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  user!: Users;
  loginForm: FormGroup = new FormGroup([]);

  constructor(private router: Router, private authService: AuthService,  private formBuilder: FormBuilder) {}

  ngOnInit(): void {
      this.loginForm = this.formBuilder.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required]
      });
  }

  login(): void{
    this.authService.getUser(this.loginForm.get('email')?.value).subscribe({
      next: (data) =>{
        this.user=data;
        this.authService.loggedUser=this.user;
        console.log(this.authService.loggedUser);
        this.router.navigate(['lateral']); 
      } ,
      error: (err) => console.error('Usuario no encontrado', err)
    });
  }

}
