import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AsignaturaComponent } from '../list/asignatura.component';
import { EstudianteComponent } from '../../estudiante/list/estudiante.component';

const asignaturaRoute: Routes = [
  {
    path: '',
    component: AsignaturaComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EstudianteComponent,
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(asignaturaRoute)],
  exports: [RouterModule],
})
export class AsignaturaRoutingModule {}
