import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EstudianteComponent } from '../list/estudiante.component';

const estudianteRoute: Routes = [
  {
    path: '',
    component: EstudianteComponent,
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(estudianteRoute)],
  exports: [RouterModule],
})
export class EstudianteRoutingModule {}
