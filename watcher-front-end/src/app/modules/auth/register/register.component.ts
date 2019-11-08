import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  username: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  confirmedEmail: string;
  confirmedPassword: string;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  register(): void {
    if (this.username === 'admin' && this.password === 'admin') {
      this.router.navigate(['home']);
    } else {
      alert('Invalid credentials');
    }
  }
}
