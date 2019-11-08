import {Component, OnInit} from '@angular/core';
import {Tile} from '../../shared/models/tile';
import {FormBuilder, FormControl, Validators} from '@angular/forms';
import {BehaviorSubject, combineLatest, Observable, of} from 'rxjs';
import {filter, map, tap} from 'rxjs/operators';
import {MOVIES} from '../../core/mocks/mock.movies';
import {Movie} from '../../shared/models/movie';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  /*tiles: Tile[] = [
    {text: 'One', cols: 1, rows: 1, color: 'lightblue'},
    {text: 'Two', cols: 1, rows: 1, color: 'lightgreen'},
    {text: 'Three', cols: 1, rows: 1, color: 'lightpink'},
    {text: 'Four', cols: 1, rows: 1, color: '#DDBDF1'},
    {text: 'One', cols: 1, rows: 1, color: 'lightblue'},
    {text: 'Two', cols: 1, rows: 1, color: 'lightgreen'},
    {text: 'Three', cols: 1, rows: 1, color: 'lightpink'},
    {text: 'Four', cols: 1, rows: 1, color: '#DDBDF1'},
    {text: 'One', cols: 1, rows: 1, color: 'lightblue'},
    {text: 'Two', cols: 1, rows: 1, color: 'lightgreen'},
    {text: 'Three', cols: 1, rows: 1, color: 'lightpink'},
    {text: 'Four', cols: 1, rows: 1, color: '#DDBDF1'},
  ];*/
  search: string;
  filteredSortedTiles: Observable<Tile[]>;

  constructor() {
    this.filteredSortedTiles = of(this.moviesToTiles(MOVIES));
    console.log(this.filteredSortedTiles);
  }

  ngOnInit() {

  }

  moviesToTiles(movies: Movie[]): Tile[] {
    const movieTiles: Tile[] = [];
    movies.forEach((movie) => {
      movieTiles.push({text: movie.original_title, cols: 1, rows: 1, color: 'lightblue'});
    });
    return movieTiles;
  }

  showDetails(tile: Tile) {
    console.log('ALLO');
  }

  filter() {
    this.filteredSortedTiles = this.filteredSortedTiles.pipe(
      map(tiles => tiles.filter(tile => tile.text.toLowerCase().includes(this.search)))
    );
  }
}

