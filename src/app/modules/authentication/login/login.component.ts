import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { JwtSessionService } from 'src/app/services/jwt-session.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	form = new FormGroup({
		username: new FormControl('', [Validators.required]),
		password: new FormControl('', [Validators.required]),
	});
	get er() { return this.form.controls; }
	hide = true; hidePin = true;
	roles: any;
	staff: any;
	inProgress = false;

	constructor(private token: JwtSessionService, private authServ: AuthenticationService,
		private route: Router, private notifyServ: NotificationService, private jwtHelper: JwtHelperService) { }
	ngOnInit(): void {
	}
	login() {
		this.inProgress = true;
		this.token.logout();
		this.authServ.signin(this.form.value).subscribe((data: any) => {
			if (data) {
				this.notifyServ.showSuccess("Please wait, you will be redirected automatically.", "Logged in successfully.");
				this.token.saveToken(data.jwtToken);
				this.route.navigate([""])
					.then(() => {
						window.location.reload();
					});
			} else {
				this.notifyServ.showError("Please, recheck your userID and password.", "Login failled.");
			}
			this.inProgress = false;
		},
			error => {
				if (error.status == 409) {
					this.notifyServ.showError("Wrong username or pass", "Login failled.");
				} else {
					this.notifyServ.showError("Something went wrong while trying to login.", "Login failled.");
				}
				this.inProgress = false;
			});
	}
}
