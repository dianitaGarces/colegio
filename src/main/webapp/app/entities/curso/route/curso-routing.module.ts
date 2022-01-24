import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CursoComponent } from '../list/curso.component';
import { CursoDetailComponent } from '../detail/curso-detail.component';
import { CursoRoutingResolveService } from './curso-routing-resolve.service';

const cursoRoute: Routes = [
  {
    path: '',
    component: CursoComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CursoDetailComponent,
    resolve: {
      curso: CursoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(cursoRoute)],
  exports: [RouterModule],
})
export class CursoRoutingModule {}
