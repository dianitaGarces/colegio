import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CursoComponent } from './list/curso.component';
import { CursoDetailComponent } from './detail/curso-detail.component';
import { CursoRoutingModule } from './route/curso-routing.module';

@NgModule({
  imports: [SharedModule, CursoRoutingModule],
  declarations: [CursoComponent, CursoDetailComponent],
})
export class CursoModule {}
