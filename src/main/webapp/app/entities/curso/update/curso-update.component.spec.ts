import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CursoService } from '../service/curso.service';
import { ICurso, Curso } from '../curso.model';
import { IColegio } from 'app/entities/colegio/colegio.model';
import { ColegioService } from 'app/entities/colegio/service/colegio.service';

import { CursoUpdateComponent } from './curso-update.component';

describe('Curso Management Update Component', () => {
  let comp: CursoUpdateComponent;
  let fixture: ComponentFixture<CursoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cursoService: CursoService;
  let colegioService: ColegioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CursoUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(CursoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CursoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cursoService = TestBed.inject(CursoService);
    colegioService = TestBed.inject(ColegioService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Colegio query and add missing value', () => {
      const curso: ICurso = { id: 456 };
      const id_colegio: IColegio = { id: 81100 };
      curso.id_colegio = id_colegio;

      const colegioCollection: IColegio[] = [{ id: 56557 }];
      jest.spyOn(colegioService, 'query').mockReturnValue(of(new HttpResponse({ body: colegioCollection })));
      const additionalColegios = [id_colegio];
      const expectedCollection: IColegio[] = [...additionalColegios, ...colegioCollection];
      jest.spyOn(colegioService, 'addColegioToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ curso });
      comp.ngOnInit();

      expect(colegioService.query).toHaveBeenCalled();
      expect(colegioService.addColegioToCollectionIfMissing).toHaveBeenCalledWith(colegioCollection, ...additionalColegios);
      expect(comp.colegiosSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const curso: ICurso = { id: 456 };
      const id_colegio: IColegio = { id: 73856 };
      curso.id_colegio = id_colegio;

      activatedRoute.data = of({ curso });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(curso));
      expect(comp.colegiosSharedCollection).toContain(id_colegio);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Curso>>();
      const curso = { id: 123 };
      jest.spyOn(cursoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ curso });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: curso }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(cursoService.update).toHaveBeenCalledWith(curso);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Curso>>();
      const curso = new Curso();
      jest.spyOn(cursoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ curso });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: curso }));
      saveSubject.complete();

      // THEN
      expect(cursoService.create).toHaveBeenCalledWith(curso);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Curso>>();
      const curso = { id: 123 };
      jest.spyOn(cursoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ curso });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cursoService.update).toHaveBeenCalledWith(curso);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackColegioById', () => {
      it('Should return tracked Colegio primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackColegioById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
