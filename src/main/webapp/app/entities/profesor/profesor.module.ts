import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ProfesorComponent } from './list/profesor.component';
import { ProfesorDetailComponent } from './detail/profesor-detail.component';
import { ProfesorRoutingModule } from './route/profesor-routing.module';

@NgModule({
  imports: [SharedModule, ProfesorRoutingModule],
  declarations: [ProfesorComponent, ProfesorDetailComponent],
})
export class ProfesorModule {}
