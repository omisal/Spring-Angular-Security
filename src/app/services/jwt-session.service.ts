import { isNull } from '@angular/compiler/src/output/output_ast';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { delay, of, Subscription } from 'rxjs';
const token_key = "auth-token";
const refreshToken_key = 'auth-refreshtoken';
const user_key = 'auth-user';
const token_expired = 'token-expired';
const token_timeout = 'token-timeout';
const expired_page = 'expired_page';
@Injectable({
	providedIn: 'root'
})
export class JwtSessionService {
	timeout: any;
	constructor(private router: Router,private jwtHelper: JwtHelperService) { }
	logout() {
		sessionStorage.clear();
	}
	public saveToken(token: string) {
		sessionStorage.removeItem(token_key);
		sessionStorage.setItem(token_key, token);
	}
	public getToken() {
		return sessionStorage.getItem(token_key);
	}
  public getTokenUser(){
    const token=sessionStorage.getItem(token_key);
    return this.jwtHelper.decodeToken(token?.toString()).user
  }
}