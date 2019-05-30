import { Component, OnInit } from '@angular/core';
import { Room } from './room';
import { RoomService } from './rooms-page.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-rooms-page',
  templateUrl: './rooms-page.component.html',
  styleUrls: ['./rooms-page.component.scss']
})
export class RoomsPageComponent implements OnInit {

  constructor(private roomService: RoomService, private router: Router, ) {
  }

  rooms  = [];
  room = new Room('', '', 0);

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.roomService.findAll().subscribe(roomsData => {
      this.rooms = roomsData;
      this.room = new Room('', '', 0);
    });
  }

  save() {
    console.log(this.room);
    this.roomService.save(this.room).subscribe(() => { this.initData(); });
  }

  delete(id) {
    this.roomService.delete(id).subscribe(() => { this.initData(); });
  }
}
