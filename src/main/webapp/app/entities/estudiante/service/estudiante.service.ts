import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEstudiante, getEstudianteIdentifier } from '../estudiante.model';

export type EntityResponseType = HttpResponse<IEstudiante>;
export type EntityArrayResponseType = HttpResponse<IEstudiante[]>;

@Injectable({ providedIn: 'root' })
export class EstudianteService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/estudiantes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  findAllId(id: number): Observable<EntityArrayResponseType> {
    return this.http.get<IEstudiante[]>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
