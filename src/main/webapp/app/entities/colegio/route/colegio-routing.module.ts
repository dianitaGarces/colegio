import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ColegioComponent } from '../list/colegio.component';
import { ProfesorComponent } from '../../profesor/list/profesor.component';
import { ColegioDetailComponent } from '../detail/colegio-detail.component';
import { ColegioRoutingResolveService } from './colegio-routing-resolve.service';

const colegioRoute: Routes = [
  {
    path: '',
    component: ColegioComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProfesorComponent,
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(colegioRoute)],
  exports: [RouterModule],
})
export class ColegioRoutingModule {}
