import {Injectable} from '@angular/core';
import {Movie} from '../../../shared/models/movie';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private saveMovies = 'http://localhost:8080/movies';
  private searchMovies = 'http://localhost:8080/search/movies';
  private searchPopularMovies = 'http://localhost:8080/search/movies/popular';
  private searchUpcomingMovies = 'http://localhost:8080/search/movies/upcoming';
  private searchTopRatedMovies = 'http://localhost:8080/search/movies/toprated';
  private searchNowPlayingMovies = 'http://localhost:8080/search/movies/nowplaying';

  constructor(private http: HttpClient) { }

  saveMovie(movies: Movie[]): Observable<unknown> {
    return this.http.post<Movie>(this.saveMovies, movies, httpOptions)
      .pipe(
        catchError(this.handleError('save'))
      );
  }

  getMovies(): Observable<Movie[]> {
    let params = new HttpParams();
    params = params.append('search', 'marvel');

    return this.http.get<Movie[]>(this.searchMovies, {params})
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

  getUpcoming(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.searchUpcomingMovies, {})
      .pipe(
        tap(_ => console.log('fetched upcoming movies', _)),
        catchError(this.handleError<Movie[]>('getUpcoming', []))
      );
  }

  getNowPlaying(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.searchNowPlayingMovies, {})
      .pipe(
        tap(_ => console.log('fetched now playing movies', _)),
        catchError(this.handleError<Movie[]>('getNowPlaying', []))
      );
  }

  getTopRated(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.searchTopRatedMovies, {})
      .pipe(
        tap(_ => console.log('fetched top rated movies', _)),
        catchError(this.handleError<Movie[]>('getTopRated', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      // this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}


