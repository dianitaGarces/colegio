import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IColegio, Colegio } from '../colegio.model';
import { ColegioService } from '../service/colegio.service';

@Component({
  selector: 'jhi-colegio-update',
  templateUrl: './colegio-update.component.html',
})
export class ColegioUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]],
  });

  constructor(protected colegioService: ColegioService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ colegio }) => {
      this.updateForm(colegio);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const colegio = this.createFromForm();
    if (colegio.id !== undefined) {
      this.subscribeToSaveResponse(this.colegioService.update(colegio));
    } else {
      this.subscribeToSaveResponse(this.colegioService.create(colegio));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IColegio>>): void {
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

  protected updateForm(colegio: IColegio): void {
    this.editForm.patchValue({
      id: colegio.id,
      nombre: colegio.nombre,
    });
  }

  protected createFromForm(): IColegio {
    return {
      ...new Colegio(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }
}
