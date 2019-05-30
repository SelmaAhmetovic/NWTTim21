import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Room } from './room';
import { Observable } from 'rxjs';

@Injectable()
export class RoomService {

  private roomsUrl: string;

  constructor(private http: HttpClient) {
    this.roomsUrl = 'http://localhost:8081/room';
  }

  public findAll(): Observable<Room[]> {
    return this.http.get<Room[]>(this.roomsUrl);
  }

  public save(room: Room) {
    return this.http.post(this.roomsUrl, room, {responseType: 'text'});
  }

  public delete(id: number) {
    return this.http.get(this.roomsUrl + 'Delete/' + id);
  }
}
