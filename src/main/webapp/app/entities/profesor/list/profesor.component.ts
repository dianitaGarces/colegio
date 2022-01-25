import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IColegio } from '../../colegio/colegio.model';
import { IProfesor } from '../profesor.model';
import { ProfesorService } from '../service/profesor.service';
import { ColegioService } from '../../colegio/service/colegio.service';

@Component({
  selector: 'jhi-profesor',
  templateUrl: './profesor.component.html',
  styleUrls: ['./profesor.component.scss'],
})
export class ProfesorComponent implements OnInit {
  profesors?: IProfesor[];
  colegio?: IColegio;
  isLoading = false;

  constructor(protected colegioService: ColegioService, protected profesorService: ProfesorService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.profesorService.query().subscribe({
      next: (res: HttpResponse<IProfesor[]>) => {
        this.isLoading = false;
        this.profesors = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  loadColegio(): void {
    this.colegioService.query().subscribe({
      next: (res: HttpResponse<IColegio[]>) => {
        this.isLoading = false;
        const temp = res.body ?? [];
        this.colegio = temp[0];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.loadColegio();
  }

  trackId(index: number, item: IProfesor): number {
    return item.id!;
  }
}
