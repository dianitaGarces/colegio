import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IColegio } from '../colegio.model';
import { ColegioService } from '../service/colegio.service';

@Component({
  selector: 'jhi-colegio',
  templateUrl: './colegio.component.html',
})
export class ColegioComponent implements OnInit {
  colegios?: IColegio[];
  isLoading = false;

  constructor(protected colegioService: ColegioService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.colegioService.query().subscribe({
      next: (res: HttpResponse<IColegio[]>) => {
        this.isLoading = false;
        this.colegios = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IColegio): number {
    return item.id!;
  }
}
