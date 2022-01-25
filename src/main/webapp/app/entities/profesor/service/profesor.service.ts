import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IProfesor, getProfesorIdentifier } from '../profesor.model';

export type EntityResponseType = HttpResponse<IProfesor>;
export type EntityArrayResponseType = HttpResponse<IProfesor[]>;

@Injectable({ providedIn: 'root' })
export class ProfesorService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/profesors');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProfesor>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProfesor[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
