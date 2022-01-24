import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IColegio, Colegio } from '../colegio.model';

import { ColegioService } from './colegio.service';

describe('Colegio Service', () => {
  let service: ColegioService;
  let httpMock: HttpTestingController;
  let elemDefault: IColegio;
  let expectedResult: IColegio | IColegio[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ColegioService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      nombre: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Colegio', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Colegio()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Colegio', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nombre: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Colegio', () => {
      const patchObject = Object.assign(
        {
          nombre: 'BBBBBB',
        },
        new Colegio()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Colegio', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nombre: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Colegio', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addColegioToCollectionIfMissing', () => {
      it('should add a Colegio to an empty array', () => {
        const colegio: IColegio = { id: 123 };
        expectedResult = service.addColegioToCollectionIfMissing([], colegio);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(colegio);
      });

      it('should not add a Colegio to an array that contains it', () => {
        const colegio: IColegio = { id: 123 };
        const colegioCollection: IColegio[] = [
          {
            ...colegio,
          },
          { id: 456 },
        ];
        expectedResult = service.addColegioToCollectionIfMissing(colegioCollection, colegio);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Colegio to an array that doesn't contain it", () => {
        const colegio: IColegio = { id: 123 };
        const colegioCollection: IColegio[] = [{ id: 456 }];
        expectedResult = service.addColegioToCollectionIfMissing(colegioCollection, colegio);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(colegio);
      });

      it('should add only unique Colegio to an array', () => {
        const colegioArray: IColegio[] = [{ id: 123 }, { id: 456 }, { id: 97706 }];
        const colegioCollection: IColegio[] = [{ id: 123 }];
        expectedResult = service.addColegioToCollectionIfMissing(colegioCollection, ...colegioArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const colegio: IColegio = { id: 123 };
        const colegio2: IColegio = { id: 456 };
        expectedResult = service.addColegioToCollectionIfMissing([], colegio, colegio2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(colegio);
        expect(expectedResult).toContain(colegio2);
      });

      it('should accept null and undefined values', () => {
        const colegio: IColegio = { id: 123 };
        expectedResult = service.addColegioToCollectionIfMissing([], null, colegio, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(colegio);
      });

      it('should return initial array if no Colegio is added', () => {
        const colegioCollection: IColegio[] = [{ id: 123 }];
        expectedResult = service.addColegioToCollectionIfMissing(colegioCollection, undefined, null);
        expect(expectedResult).toEqual(colegioCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
