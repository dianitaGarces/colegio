import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IColegio, Colegio } from '../colegio.model';
import { ColegioService } from '../service/colegio.service';

@Injectable({ providedIn: 'root' })
export class ColegioRoutingResolveService implements Resolve<IColegio> {
  constructor(protected service: ColegioService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IColegio> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((colegio: HttpResponse<Colegio>) => {
          if (colegio.body) {
            return of(colegio.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Colegio());
  }
}
