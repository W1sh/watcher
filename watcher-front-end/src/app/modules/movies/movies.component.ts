import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from "@angular/material/sort";
import {Movie} from "../../shared/models/movie";
import {MovieService} from "../../core/services/movie/movie.service";
import {EventEmitterService} from "../../core/services/event-emitter/event-emitter.service";

@Component({
  selector: 'app-movies',
  styleUrls: ['./movies.component.scss'],
  templateUrl: './movies.component.html',
})

export class MoviesComponent implements OnInit {
  data: Movie[];
  displayedColumns: string[] = ['title', 'popularity', 'releaseDate', 'voteCount', 'voteAverage'];
  dataSource: MatTableDataSource<Movie>;

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
        }
      }
      if(this.paginator){
        this.dataSource.paginator = this.paginator;
      }
    });
    if(this.eventEmitterService.subscription == undefined){
      this.eventEmitterService.subscription = this.eventEmitterService.invokeSearchPopular.subscribe(
        (name: string) => {
        this.searchPopular();
      });
    }
    this.changeDetectorRefs.detectChanges();
  }

  searchPopular(){
    this.movieService.getPopular().subscribe(movies => {
      this.data = movies;
      this.dataSource.data = this.data;
      this.dataSource.paginator = this.paginator;
      this.changeDetectorRefs.detectChanges();
    })
  }
}
