import {Injectable} from '@angular/core';
import {Movie} from "../../../shared/models/movie";
import {Observable, of} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})

export class MovieService {

  private searchMovies = 'http://localhost:8080/search/movies';
  private searchPopularMovies = 'http://localhost:8080/search/movies/popular';

  constructor(private http: HttpClient) { }

  getMovies(): Observable<Movie[]> {
    let params = new HttpParams();
    params = params.append('search', "marvel");

    return this.http.get<Movie[]>(this.searchMovies, {params: params})
      .pipe(
        tap(_ => console.log('fetched movies', _)),
        catchError(this.handleError<Movie[]>('getMovies', []))
      );
  }

  getPopular(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.searchPopularMovies, {})
      .pipe(
        tap(_ => console.log('fetched popular movies', _)),
        catchError(this.handleError<Movie[]>('getPopular', []))
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


