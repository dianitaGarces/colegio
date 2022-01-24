import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute } from '@angular/router';

import { IAsignatura } from '../asignatura.model';
import { AsignaturaService } from '../service/asignatura.service';

@Component({
  selector: 'jhi-asignatura',
  templateUrl: './asignatura.component.html',
})
export class AsignaturaComponent implements OnInit {
  asignaturas?: IAsignatura[];
  isLoading = false;

  constructor(protected asignaturaService: AsignaturaService, protected modalService: NgbModal, private activatedRoute: ActivatedRoute) {}

  loadAll(): void {
    const routeParams = this.activatedRoute.snapshot.paramMap;
    const idProfesor = Number(routeParams.get('id'));

    this.isLoading = true;

    this.asignaturaService.findAllId(idProfesor).subscribe({
      next: (res: HttpResponse<IAsignatura[]>) => {
        this.isLoading = false;
        this.asignaturas = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IAsignatura): number {
    return item.id!;
  }
}
