import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateModule } from '@ngx-translate/core';

import { RoomsPageComponent } from './rooms-page.component';
import { LayoutModule } from '../layout.module';

describe('RoomsPageComponent', () => {
  let component: RoomsPageComponent;
  let fixture: ComponentFixture<RoomsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        LayoutModule,
        RouterTestingModule,
        TranslateModule.forRoot(),
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
