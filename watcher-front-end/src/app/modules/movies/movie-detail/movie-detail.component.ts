import {Component, Input, OnInit} from '@angular/core';
import {Tile} from '../../../shared/models/tile';
import {Router} from '@angular/router';
import {Observable, of} from 'rxjs';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss']
})
export class MovieDetailComponent implements OnInit {
  tile: Tile;
  similarMoviesTiles: Observable<Tile[]> = of([
    {id: 0, text: 'tt', cols: 1, rows: 1, color: 'lightpink'},
    {id: 1, text: 'tt', cols: 1, rows: 1, color: 'lightpink'},
    {id: 2, text: 'tt', cols: 1, rows: 1, color: 'lightpink'},
    {id: 3, text: 'tt', cols: 1, rows: 1, color: 'lightpink'},
    {id: 4, text: 'tt', cols: 1, rows: 1, color: 'lightpink'},
    {id: 5, text: 'tt', cols: 1, rows: 1, color: 'lightpink'},
  ]);

  constructor(private router: Router) {
    this.tile = {id: 0, text: '', cols: 1, rows: 1, color: ''};
    this.tile.id = router.getCurrentNavigation().extras.state.id;
    console.log(this.tile.id);
  }

  ngOnInit() {
  }

}
