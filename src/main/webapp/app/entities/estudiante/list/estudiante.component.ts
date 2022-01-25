import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute } from '@angular/router';

import { IEstudiante } from '../estudiante.model';
import { EstudianteService } from '../service/estudiante.service';

@Component({
  selector: 'jhi-estudiante',
  templateUrl: './estudiante.component.html',
  styleUrls: ['./estudiante.component.scss'],
})
export class EstudianteComponent implements OnInit {
  estudiantes?: IEstudiante[];
  isLoading = false;
  nombreAsignatura?: any;

  constructor(protected estudianteService: EstudianteService, protected modalService: NgbModal, protected activatedRoute: ActivatedRoute) {}

  loadAll(): void {
    this.isLoading = true;

    const routeParams = this.activatedRoute.snapshot.paramMap;
    const idAsignatura = Number(routeParams.get('id'));
    this.nombreAsignatura = routeParams.get('nombre')?.toString();

    this.estudianteService.findAllId(idAsignatura).subscribe({
      next: (res: HttpResponse<IEstudiante[]>) => {
        this.isLoading = false;
        this.estudiantes = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEstudiante): number {
    return item.id!;
  }
}
