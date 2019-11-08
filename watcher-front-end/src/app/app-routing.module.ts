import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './modules/home/home.component';
import {MoviesComponent} from './modules/movies/movies.component';
import {LoginComponent} from './modules/login/login.component';
import {RegisterComponent} from './modules/register/register.component';
import {DashboardComponent} from './modules/dashboard/dashboard.component';
import {AuthGuard} from './core/guards/auth.guard';
import {MovieDetailComponent} from './modules/movie-detail/movie-detail.component';


const routes: Routes = [
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'movies', component: MoviesComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'movies/details', component: MovieDetailComponent, canActivate: [AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
