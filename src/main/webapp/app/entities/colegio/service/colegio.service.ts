import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IColegio, getColegioIdentifier } from '../colegio.model';

export type EntityResponseType = HttpResponse<IColegio>;
export type EntityArrayResponseType = HttpResponse<IColegio[]>;

@Injectable({ providedIn: 'root' })
export class ColegioService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/colegios');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(colegio: IColegio): Observable<EntityResponseType> {
    return this.http.post<IColegio>(this.resourceUrl, colegio, { observe: 'response' });
  }

  update(colegio: IColegio): Observable<EntityResponseType> {
    return this.http.put<IColegio>(`${this.resourceUrl}/${getColegioIdentifier(colegio) as number}`, colegio, { observe: 'response' });
  }

  partialUpdate(colegio: IColegio): Observable<EntityResponseType> {
    return this.http.patch<IColegio>(`${this.resourceUrl}/${getColegioIdentifier(colegio) as number}`, colegio, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IColegio>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IColegio[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addColegioToCollectionIfMissing(colegioCollection: IColegio[], ...colegiosToCheck: (IColegio | null | undefined)[]): IColegio[] {
    const colegios: IColegio[] = colegiosToCheck.filter(isPresent);
    if (colegios.length > 0) {
      const colegioCollectionIdentifiers = colegioCollection.map(colegioItem => getColegioIdentifier(colegioItem)!);
      const colegiosToAdd = colegios.filter(colegioItem => {
        const colegioIdentifier = getColegioIdentifier(colegioItem);
        if (colegioIdentifier == null || colegioCollectionIdentifiers.includes(colegioIdentifier)) {
          return false;
        }
        colegioCollectionIdentifiers.push(colegioIdentifier);
        return true;
      });
      return [...colegiosToAdd, ...colegioCollection];
    }
    return colegioCollection;
  }
}
