import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MoviesComponent} from './movies/movies.component';
import {AuthGuard} from '../../core/guards/auth.guard';
import {MovieDetailComponent} from './movie-detail/movie-detail.component';

const routes: Routes = [
  {path: 'movies', component: MoviesComponent, canActivate: [AuthGuard]},
  {path: 'movies/details', component: MovieDetailComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MoviesRoutingModule { }
