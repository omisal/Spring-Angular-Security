import { Injectable } from '@angular/core';
import {
	HttpRequest,
	HttpHandler,
	HttpEvent,
	HttpInterceptor,
	HTTP_INTERCEPTORS
} from '@angular/common/http';
import { catchError, Observable, take, throwError } from 'rxjs';
import { JwtSessionService } from '../services/jwt-session.service';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class HttpConfigInterceptor implements HttpInterceptor {
	constructor(private tokenServ: JwtSessionService, private authService: AuthenticationService) { }
	private isRefreshing = false;

	intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		const token = this.tokenServ.getToken();
		if (token) {
			request = request.clone({
				setHeaders: { Authorization: 'Bearer ' + token }
			});
		}
		if (!request.headers.has('Content-Type')) {
			request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });
		}
		return next.handle(request).pipe(catchError(error => {
			if (error.status == 401) {
				// handle 401 error automatically the way you want
			}
			return throwError(error);
		}));
	}
}

export const authInterceptorProviders = [{
	provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true
}];
