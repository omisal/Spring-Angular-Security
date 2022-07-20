import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth-config/auth.guard';

const routes: Routes = [
	{
		path: '',
		canActivate: [AuthGuard],
		canActivateChild: [AuthGuard],
		loadChildren: () =>
			import('./modules/main/main.module').then((m) => m.MainModule),
	},
	{
		path: 'auth',
		loadChildren: () =>
			import('./modules/authentication/authentication.module').then((m) => m.AuthenticationModule)
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }