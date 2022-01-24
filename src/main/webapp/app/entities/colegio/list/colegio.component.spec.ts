import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { ColegioService } from '../service/colegio.service';

import { ColegioComponent } from './colegio.component';

describe('Colegio Management Component', () => {
  let comp: ColegioComponent;
  let fixture: ComponentFixture<ColegioComponent>;
  let service: ColegioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ColegioComponent],
    })
      .overrideTemplate(ColegioComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ColegioComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ColegioService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.colegios?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
