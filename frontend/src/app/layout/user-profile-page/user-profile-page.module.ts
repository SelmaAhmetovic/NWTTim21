import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserProfilePageRoutingModule } from './user-profile-page-routing.module';
import { UserProfilePageComponent } from './user-profile-page.component';
import { PageHeaderModule } from './../../shared/modules/page-header/page-header.module';

@NgModule({
    imports: [CommonModule, UserProfilePageRoutingModule, PageHeaderModule],
    declarations: [UserProfilePageComponent]
})
export class UserProfilePageModule {}
