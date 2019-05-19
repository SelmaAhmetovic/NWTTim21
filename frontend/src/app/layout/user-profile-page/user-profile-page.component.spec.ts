import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateModule } from '@ngx-translate/core';

import { UserProfilePageComponent } from './user-profile-page.component';
import { LayoutModule } from '../layout.module';

describe('UserProfileComponent', () => {
  let component: UserProfilePageComponent;
  let fixture: ComponentFixture<UserProfilePageComponent>;

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
    fixture = TestBed.createComponent(UserProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
