import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ColegioComponent } from './list/colegio.component';
import { ColegioDetailComponent } from './detail/colegio-detail.component';
import { ColegioRoutingModule } from './route/colegio-routing.module';

@NgModule({
  imports: [SharedModule, ColegioRoutingModule],
  declarations: [ColegioComponent, ColegioDetailComponent],
})
export class ColegioModule {}
