import {Component} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {Router} from "@angular/router";
import {EventEmitterService} from "../../core/services/event-emitter/event-emitter.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent {
  private router: Router;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private _router: Router,
              private eventEmitterService: EventEmitterService) {
    this.router = _router;
  }

  searchPopular() {
    this.eventEmitterService.onSearchPopular();
  }

  searchTopRated() {

  }

  searchUpcoming() {

  }

  searchNowPlaying() {

  }

}
