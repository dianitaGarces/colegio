import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IColegio } from '../colegio.model';

@Component({
  selector: 'jhi-colegio-detail',
  templateUrl: './colegio-detail.component.html',
})
export class ColegioDetailComponent implements OnInit {
  colegio: IColegio | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ colegio }) => {
      this.colegio = colegio;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
