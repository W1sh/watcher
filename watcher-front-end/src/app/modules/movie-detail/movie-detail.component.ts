import {Component, Input, OnInit} from '@angular/core';
import {Tile} from '../../shared/models/tile';
import {Router} from '@angular/router';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss']
})
export class MovieDetailComponent implements OnInit {
  tile: Tile;

  constructor(private router: Router) {
    this.tile = {id: 0, text: '', cols: 1, rows: 1, color: ''};
    this.tile.id = router.getCurrentNavigation().extras.state.id;
    console.log(this.tile.id);
  }

  ngOnInit() {
  }

}
