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

  logIn: boolean = true;
  loginForm: FormGroup = new FormGroup([]);
  registerForm: FormGroup = new FormGroup([]);

  constructor(
    private router: Router, 
    private authService: AuthService,  
    private formBuilder: FormBuilder) {}

  ngOnInit():void {
      this.loginForm = this.formBuilder.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required]
      });
      this.registerForm = this.formBuilder.group({
        firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)]],
        lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)]],
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required]
      });
  }

  login():void {
    this.authService.getUser(this.loginForm.get('email')?.value).subscribe({
      next: (data) =>{
        this.authService.loggedUser=data;
        this.router.navigate(['lateral']); 
      } ,
      error: (err) => console.error('Usuario no encontrado', err)
    });
  }

  registrar():void {
    let user: Users = {
      firstName: this.registerForm.get('firstName')?.value,
      lastName: this.registerForm.get('lastName')?.value,
      email: this.registerForm.get('email')?.value,
      userPassword: this.registerForm.get('password')?.value
    };
    this.authService.registerUser(user).subscribe({
      next: (user) =>{
        this.authService.loggedUser=user;
        this.router.navigate(['lateral']); 
      } ,
      error: (err) => console.error('Usuario no encontrado', err)
    });
  }

  cambioFormulario():void {
    this.logIn=!this.logIn;
  }

}
