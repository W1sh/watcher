import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {Movie} from '../../../shared/models/movie';
import {MovieService} from '../../../core/services/movie/movie.service';
import {EventEmitterService} from '../../../core/services/event-emitter/event-emitter.service';
import {Subscription} from 'rxjs';
import {SelectionModel} from '@angular/cdk/collections';

@Component({
  selector: 'app-movies',
  styleUrls: ['./movies.component.scss'],
  templateUrl: './movies.component.html',
})

export class MoviesComponent implements OnInit {
  data: Movie[];
  displayedColumns: string[] = ['select', 'title', 'popularity', 'releaseDate', 'voteCount', 'voteAverage'];
  dataSource: MatTableDataSource<Movie>;
  selection = new SelectionModel<Movie>(true, []);
  subscription: Subscription;

  constructor(private movieService: MovieService, private eventEmitterService: EventEmitterService,
              private changeDetectorRefs: ChangeDetectorRef) { }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  ngOnInit() {
    this.movieService.getMovies().subscribe(movies => {
      this.data = movies;
      this.dataSource = new MatTableDataSource(this.data);
      if (this.sort) { // check it is defined.
        this.dataSource.sort = this.sort;
        this.dataSource.sortingDataAccessor = (item, property) => {
          switch (property) {
            case 'title': return item.original_title;
            case 'releaseDate': return new Date(item.release_date);
            case 'voteCount': return item.vote_count;
            case 'voteAverage': return item.vote_average;
            default: return item[property];
          }
        };
      }
      if (this.paginator) {
        this.dataSource.paginator = this.paginator;
      }
    });
    if (this.subscription === undefined) {
      this.subscription = this.eventEmitterService.invoker.subscribe(
        (type: string) => {
          switch (type) {
            case 'popular': this.searchPopular();
                            break;
            case 'upcoming': this.searchUpcoming();
                             break;
            case 'toprated': this.searchTopRated();
                             break;
            case 'nowplaying': this.searchNowPlaying();
                               break;
          }
        });
    }
  }

  searchPopular() {
    this.movieService.getPopular().subscribe(movies => {
      this.refresh(movies);
    });
  }

  searchUpcoming() {
    this.movieService.getUpcoming().subscribe(movies => {
      this.refresh(movies);
    });
  }

  searchTopRated() {
    this.movieService.getTopRated().subscribe(movies => {
      this.refresh(movies);
    });
  }

  searchNowPlaying() {
    this.movieService.getNowPlaying().subscribe(movies => {
      this.refresh(movies);
    });
  }

  save() {
    this.movieService.saveMovie(this.selection.selected).subscribe();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  checkboxLabel(row?: Movie): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.id + 1}`;
  }

  private refresh(movies: Movie[]) {
    this.data = movies;
    this.dataSource.data = this.data;
    this.dataSource.paginator = this.paginator;
    this.changeDetectorRefs.detectChanges();
  }
}
