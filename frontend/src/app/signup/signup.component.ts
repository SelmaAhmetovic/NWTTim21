import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../router.animations';

import { AuthService } from '../layout/auth/auth.service';
import { SignUpInfo } from './signup-info';


@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss'],
    animations: [routerTransition()]
})
export class SignupComponent implements OnInit {
    form: any = {};
    signupInfo: SignUpInfo;
    isSignedUp = false;
    isSignUpFailed = false;
    errorMessage = '';

    constructor(private authService: AuthService) { }

    ngOnInit() { }

    onSubmit() {
      console.log(this.form);

      this.signupInfo = new SignUpInfo(
        this.form.firstName,
        this.form.username,
        this.form.lastName,
        this.form.password);

      this.authService.signUp(this.signupInfo).subscribe(
        data => {
          console.log(data);
          this.isSignedUp = true;
          this.isSignUpFailed = false;
        },
        error => {
          console.log(error);
          this.errorMessage = error.error.message;
          this.isSignUpFailed = true;
        }
      );
    }
}
