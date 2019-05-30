import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoomsPageRoutingModule } from './rooms-page-routing.module';
import { RoomsPageComponent } from './rooms-page.component';
import { RoomService } from './rooms-page.service';
import { FormsModule } from '@angular/forms';


@NgModule({
    imports: [CommonModule, RoomsPageRoutingModule, FormsModule],
    declarations: [RoomsPageComponent],
    providers: [RoomService]
})
export class RoomsPageModule {}
