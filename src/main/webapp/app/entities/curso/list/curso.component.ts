import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICurso } from '../curso.model';
import { CursoService } from '../service/curso.service';

@Component({
  selector: 'jhi-curso',
  templateUrl: './curso.component.html',
})
export class CursoComponent implements OnInit {
  cursos?: ICurso[];
  isLoading = false;

  constructor(protected cursoService: CursoService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.cursoService.query().subscribe({
      next: (res: HttpResponse<ICurso[]>) => {
        this.isLoading = false;
        this.cursos = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: ICurso): number {
    return item.id!;
  }
}
