import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from '../../signup/signup-info';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'} )
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl: string;
  private signupUrl: string;

  constructor(private http: HttpClient) {
    this.loginUrl = 'http://localhost:8081/api/auth/signin';
    this.signupUrl = 'http://localhost:8081/api/auth/signup';
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    console.log(credentials);
    console.log(this.loginUrl);
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {

    return this.http.post(this.signupUrl, info, {responseType: 'text'});
  }
}
