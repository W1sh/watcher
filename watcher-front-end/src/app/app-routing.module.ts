import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { MoviesComponent } from './modules/movies/movies.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'movies', component: MoviesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
