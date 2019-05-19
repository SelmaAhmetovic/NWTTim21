import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarPageHeaderComponent } from './calendar-page-header.component';

describe('CalendarPageHeaderComponent', () => {
  let component: CalendarPageHeaderComponent;
  let fixture: ComponentFixture<CalendarPageHeaderComponent>;

  beforeEach(
    async(() => {
      TestBed.configureTestingModule({
        declarations: [CalendarPageHeaderComponent]
      }).compileComponents();
    })
  );

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarPageHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
