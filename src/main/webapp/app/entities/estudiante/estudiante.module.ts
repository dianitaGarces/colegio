import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EstudianteComponent } from './list/estudiante.component';
import { EstudianteRoutingModule } from './route/estudiante-routing.module';

@NgModule({
  imports: [SharedModule, EstudianteRoutingModule],
  declarations: [EstudianteComponent],
})
export class EstudianteModule {}
