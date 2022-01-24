import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstudiante } from '../estudiante.model';
import { EstudianteService } from '../service/estudiante.service';

@Component({
  selector: 'jhi-estudiante',
  templateUrl: './estudiante.component.html',
})
export class EstudianteComponent implements OnInit {
  estudiantes?: IEstudiante[];
  isLoading = false;

  constructor(protected estudianteService: EstudianteService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.estudianteService.findAllId(1).subscribe({
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
