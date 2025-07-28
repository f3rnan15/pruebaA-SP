import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FichajeComponent } from './fichaje.component';

describe('FichajeComponent', () => {
  let component: FichajeComponent;
  let fixture: ComponentFixture<FichajeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FichajeComponent]
    });
    fixture = TestBed.createComponent(FichajeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
