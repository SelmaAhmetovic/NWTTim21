import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserProfilePageRoutingModule } from './user-profile-page-routing.module';
import { UserProfilePageComponent } from './user-profile-page.component';
import { PageHeaderModule } from './../../shared/modules/page-header/page-header.module';
import {UserProfilePageService} from '../../shared/services/user-profile-page.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
    imports: [
      CommonModule,
      UserProfilePageRoutingModule,
      PageHeaderModule,
      FormsModule,
      ReactiveFormsModule
    ],
    declarations: [UserProfilePageComponent],
    providers: [UserProfilePageService]
})
export class UserProfilePageModule {}
