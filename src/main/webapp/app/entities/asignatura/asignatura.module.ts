import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AsignaturaComponent } from './list/asignatura.component';
import { AsignaturaRoutingModule } from './route/asignatura-routing.module';

@NgModule({
  imports: [SharedModule, AsignaturaRoutingModule],
  declarations: [AsignaturaComponent],
})
export class AsignaturaModule {}
