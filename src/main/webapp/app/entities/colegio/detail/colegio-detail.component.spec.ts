import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ColegioDetailComponent } from './colegio-detail.component';

describe('Colegio Management Detail Component', () => {
  let comp: ColegioDetailComponent;
  let fixture: ComponentFixture<ColegioDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ColegioDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ colegio: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ColegioDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ColegioDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load colegio on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.colegio).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
