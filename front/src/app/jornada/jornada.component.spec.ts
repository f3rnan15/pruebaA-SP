import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JornadaComponent } from './jornada.component';

describe('JornadaComponent', () => {
  let component: JornadaComponent;
  let fixture: ComponentFixture<JornadaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JornadaComponent]
    });
    fixture = TestBed.createComponent(JornadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
