import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { JwtSessionService } from 'src/app/services/jwt-session.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  user!:User;
  constructor(private authServ:AuthenticationService,private token:JwtSessionService) { }

  ngOnInit(): void {
    this.user=this.token.getTokenUser();
  }
  logout(){
    this.authServ.logout();

  }

}
