import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ProfesorComponent } from '../list/profesor.component';
import { AsignaturaComponent } from '../../asignatura/list/asignatura.component';

const profesorRoute: Routes = [
  {
    path: '',
    component: ProfesorComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':nombre/:id/view',
    component: AsignaturaComponent,
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(profesorRoute)],
  exports: [RouterModule],
})
export class ProfesorRoutingModule {}
