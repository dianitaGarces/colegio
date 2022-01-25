import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ProfesorComponent } from './list/profesor.component';
import { ProfesorRoutingModule } from './route/profesor-routing.module';

@NgModule({
  imports: [SharedModule, ProfesorRoutingModule],
  declarations: [ProfesorComponent],
})
export class ProfesorModule {}
