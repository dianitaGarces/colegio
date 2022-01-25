import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ColegioService } from '../service/colegio.service';
import { IColegio, Colegio } from '../colegio.model';

import { ColegioUpdateComponent } from './colegio-update.component';

describe('Colegio Management Update Component', () => {
  let comp: ColegioUpdateComponent;
  let fixture: ComponentFixture<ColegioUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let colegioService: ColegioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ColegioUpdateComponent],
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
      .overrideTemplate(ColegioUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ColegioUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    colegioService = TestBed.inject(ColegioService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const colegio: IColegio = { id: 456 };

      activatedRoute.data = of({ colegio });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(colegio));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Colegio>>();
      const colegio = { id: 123 };
      jest.spyOn(colegioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ colegio });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: colegio }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(colegioService.update).toHaveBeenCalledWith(colegio);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Colegio>>();
      const colegio = new Colegio();
      jest.spyOn(colegioService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ colegio });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: colegio }));
      saveSubject.complete();

      // THEN
      expect(colegioService.create).toHaveBeenCalledWith(colegio);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Colegio>>();
      const colegio = { id: 123 };
      jest.spyOn(colegioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ colegio });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(colegioService.update).toHaveBeenCalledWith(colegio);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
