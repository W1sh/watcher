import {Component, OnInit} from '@angular/core';
import {Tile} from '../../shared/models/tile';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';
import {MOVIES} from '../../core/mocks/mock.movies';
import {Movie} from '../../shared/models/movie';
import {isNumber} from 'util';
import {MovieService} from "../../core/services/movie/movie.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  search: string;
  filteredSortedTiles: Observable<Tile[]>;
  selectedTile: Tile;

  constructor(private movieService: MovieService) {}

  ngOnInit() {
    this.filteredSortedTiles = this.movieService.getSavedMovies().pipe(
      map(movies => this.moviesToTiles(movies))
    );
    console.log(this.filteredSortedTiles);
  }

  moviesToTiles(movies: Movie[]): Tile[] {
    const movieTiles: Tile[] = [];
    movies.forEach((movie) => {
      movieTiles.push({id: movie.id, text: movie.original_title, cols: 1, rows: 1, color: movie.poster_path});
    });
    return movieTiles;
  }

  filter() {
    this.filteredSortedTiles = this.filteredSortedTiles.pipe(
      map(tiles => tiles.filter(tile => tile.text.toLowerCase().includes(this.search.toLowerCase()))));
  }
}

