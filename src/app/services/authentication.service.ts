import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { catchError, map, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { JwtSessionService } from './jwt-session.service';
import { NotificationService } from './notification.service';
const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {
	private readonly apiUrl = environment.apiUrl + '/auth/signin';

	constructor(
		private jwtHelper: JwtHelperService,private token:JwtSessionService,
		private http: HttpClient,
		private router: Router,
		private route: ActivatedRoute,
		private notify: NotificationService
	) { }

	signin(credentials: any): Observable<any> {
		return this.http.post<{ jwtToken: string }>(this.apiUrl, credentials)
	}
	
	logout(returnUrl?: string): void {
		sessionStorage.removeItem('auth-token');
		this.router.navigate(['/auth'], { queryParams: { returnUrl } });
	}

	loggedIn(): boolean {
		return !this.jwtHelper.isTokenExpired(this.token.getToken()?.toString());
	}

	get user() {
		const decoded = this.token.getTokenUser();
		return decoded;
	}
}