import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'estudiante',
        data: { pageTitle: 'Estudiantes' },
        loadChildren: () => import('./estudiante/estudiante.module').then(m => m.EstudianteModule),
      },
      {
        path: 'profesor',
        data: { pageTitle: 'Profesors' },
        loadChildren: () => import('./profesor/profesor.module').then(m => m.ProfesorModule),
      },
      {
        path: 'asignatura',
        data: { pageTitle: 'Asignaturas' },
        loadChildren: () => import('./asignatura/asignatura.module').then(m => m.AsignaturaModule),
      },
      {
        path: 'curso',
        data: { pageTitle: 'Cursos' },
        loadChildren: () => import('./curso/curso.module').then(m => m.CursoModule),
      },
      {
        path: 'colegio',
        data: { pageTitle: 'Colegios' },
        loadChildren: () => import('./colegio/colegio.module').then(m => m.ColegioModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
