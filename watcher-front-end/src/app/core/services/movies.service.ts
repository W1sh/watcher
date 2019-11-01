import { Injectable } from '@angular/core';
import {Movies} from "../../shared/models/movies";
import {MOVIES} from "../mocks/mock.movies";

@Injectable({
  providedIn: 'root'
})

export class MoviesService {
  constructor() { }

  getMovies(): Movies[] {
    return MOVIES;
  }
}


