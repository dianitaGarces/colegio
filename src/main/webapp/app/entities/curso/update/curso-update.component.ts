import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ICurso, Curso } from '../curso.model';
import { CursoService } from '../service/curso.service';
import { IColegio } from 'app/entities/colegio/colegio.model';
import { ColegioService } from 'app/entities/colegio/service/colegio.service';

@Component({
  selector: 'jhi-curso-update',
  templateUrl: './curso-update.component.html',
})
export class CursoUpdateComponent implements OnInit {
  isSaving = false;

  colegiosSharedCollection: IColegio[] = [];

  editForm = this.fb.group({
    id: [],
    salon: [null, [Validators.required]],
    grado: [null, [Validators.required]],
    id_colegio: [],
  });

  constructor(
    protected cursoService: CursoService,
    protected colegioService: ColegioService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ curso }) => {
      this.updateForm(curso);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const curso = this.createFromForm();
    if (curso.id !== undefined) {
      this.subscribeToSaveResponse(this.cursoService.update(curso));
    } else {
      this.subscribeToSaveResponse(this.cursoService.create(curso));
    }
  }

  trackColegioById(index: number, item: IColegio): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurso>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(curso: ICurso): void {
    this.editForm.patchValue({
      id: curso.id,
      salon: curso.salon,
      grado: curso.grado,
      id_colegio: curso.id_colegio,
    });

    this.colegiosSharedCollection = this.colegioService.addColegioToCollectionIfMissing(this.colegiosSharedCollection, curso.id_colegio);
  }

  protected loadRelationshipsOptions(): void {
    this.colegioService
      .query()
      .pipe(map((res: HttpResponse<IColegio[]>) => res.body ?? []))
      .pipe(
        map((colegios: IColegio[]) => this.colegioService.addColegioToCollectionIfMissing(colegios, this.editForm.get('id_colegio')!.value))
      )
      .subscribe((colegios: IColegio[]) => (this.colegiosSharedCollection = colegios));
  }

  protected createFromForm(): ICurso {
    return {
      ...new Curso(),
      id: this.editForm.get(['id'])!.value,
      salon: this.editForm.get(['salon'])!.value,
      grado: this.editForm.get(['grado'])!.value,
      id_colegio: this.editForm.get(['id_colegio'])!.value,
    };
  }
}
