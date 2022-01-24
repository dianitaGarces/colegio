import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAsignatura, getAsignaturaIdentifier } from '../asignatura.model';

export type EntityResponseType = HttpResponse<IAsignatura>;
export type EntityArrayResponseType = HttpResponse<IAsignatura[]>;

@Injectable({ providedIn: 'root' })
export class AsignaturaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/asignaturas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  findAllId(id: number): Observable<EntityArrayResponseType> {
    return this.http.get<IAsignatura[]>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
