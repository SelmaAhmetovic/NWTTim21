import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CalendarEvent } from './calendar-event';
import { Observable } from 'rxjs';

@Injectable()
export class CalendarService {

  private calendarUrl: string;

  constructor(private http: HttpClient) {
    this.calendarUrl = 'http://localhost:8081/events/';
  }

  public findByUserId(): Observable<CalendarEvent[]> {

    localStorage.getItem('userId');
    const events = this.http.get<CalendarEvent[]>(this.calendarUrl + localStorage.getItem('userId'));
    console.log(events);
    return events;
  }

  public save(event) {

    return this.http.post<CalendarEvent>(this.calendarUrl, event);
  }
}
