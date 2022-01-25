import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IColegio } from '../colegio.model';
import { ColegioService } from '../service/colegio.service';

@Component({
  templateUrl: './colegio-delete-dialog.component.html',
})
export class ColegioDeleteDialogComponent {
  colegio?: IColegio;

  constructor(protected colegioService: ColegioService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.colegioService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
