import { Injectable } from '@angular/core';
import {Movie} from "../../shared/models/movie";
import {Observable, of} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, map, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})

export class MovieService {

  private searchMovies = 'http://localhost:8080/search/movies';

  constructor(private http: HttpClient) { }

  getMovies(): Observable<Movie[]> {
    let params = new HttpParams();
    params = params.append('search', "marvel");

    return this.http.get<Movie[]>(this.searchMovies, {params: params})
      .pipe(
        tap(_ => console.log('fetched heroes', _)),
        catchError(this.handleError<Movie[]>('getMovies', []))
      );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      //this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}


