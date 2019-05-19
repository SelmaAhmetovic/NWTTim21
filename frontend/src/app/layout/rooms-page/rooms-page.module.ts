import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoomsPageRoutingModule } from './rooms-page-routing.module';
import { RoomsPageComponent } from './rooms-page.component';

@NgModule({
    imports: [CommonModule, RoomsPageRoutingModule],
    declarations: [RoomsPageComponent]
})
export class RoomsPageModule {}
