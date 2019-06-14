import { ChangeDetectionStrategy, ViewChild, TemplateRef, Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { startOfDay, endOfDay, subDays, addDays, endOfMonth, isSameDay, isSameMonth, addHours} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CalendarEventAction, CalendarEventTimesChangedEvent, CalendarView } from 'angular-calendar';
import { CalendarEvent } from './calendar-event';
import { Room } from '../rooms-page/room';

import { CalendarService } from './calendar-page.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from '../rooms-page/rooms-page.service';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  }
};


@Component({
  selector: 'app-calendar-page',
  templateUrl: './calendar-page.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./calendar-page.component.scss'],
  animations: [routerTransition()]
})
export class CalendarPageComponent implements OnInit {

  constructor(private modal: NgbModal, private calendarService: CalendarService, private roomService:RoomService, private router: Router, private ref: ChangeDetectorRef) {

  }

  /*
export class DemoComponent {*/
  @ViewChild('modalContent') modalContent: TemplateRef<any>;


  view: CalendarView;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  modalData: {
    action: string;
    event: CalendarEvent;
  };

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fa fa-fw fa-pencil"></i>',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      }
    },
    {
      label: '<i class="fa fa-fw fa-times"></i>',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter(iEvent => iEvent !== event);
        this.handleEvent('Deleted', event);
      }
    }
  ];

  rooms: Room[] = [];
  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];
  activeDayIsOpen = true;
  ngOnInit() {

    this.view = CalendarView.Month;

    this.roomService.findAll().subscribe(roomsData => {
      this.rooms = roomsData;
      this.ref.detectChanges();
      console.log(roomsData);

      this.calendarService.findByUserId().subscribe(data => {
        this.events = data;
        this.events.forEach(element => {
          element.start = new Date(element.start);
          element.end = new Date(element.end);
          element.color = colors.red,
          element.draggable = true,
          element.resizable = {
            beforeStart: true,
            afterEnd: true
          };
          element.roomName = element.roomId != null ? this.rooms.find(x=>x.id == element.roomId).roomName:"Select room"
  
        });
  
        this.view = CalendarView.Month;
        this.ref.detectChanges();
      });
    });
  }
  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
    }
  }

  ngInitDone() {
    return true;
  }

  eventTimesChanged({
                      event,
                      newStart,
                      newEnd
                    }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map(iEvent => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }

  addEvent(): void {
    this.events = [
      ...this.events,
      {
        title: 'New event',
        start: startOfDay(new Date()),
        end: endOfDay(new Date()),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true
        },
        eventId: -1,
        // tslint:disable-next-line:radix
        userId: parseInt(localStorage.getItem('userId')),
        roomName: "Select room"
      }
    ];
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.calendarService.delete(eventToDelete).subscribe(data=>{
      console.log(data);
      this.calendarService.findByUserId().subscribe(eventsData => {
        this.events = eventsData;
        this.events.forEach(element => {
          element.start = new Date(element.start);
          element.end = new Date(element.end);
          element.color = colors.red,
          element.draggable = true,
          element.resizable = {
            beforeStart: true,
            afterEnd: true
          };
          element.roomName = element.roomId != -1 ? this.rooms.find(x=>x.id == element.roomId).roomName:"Select room"

        });

        this.view = CalendarView.Month;
        this.ref.detectChanges();
    });
    })
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  saveEvent(eventToSave: CalendarEvent) {
    this.calendarService.save(eventToSave).subscribe(data => {
      console.log(data);
      this.calendarService.findByUserId().subscribe(eventsData => {
        this.events = eventsData;
        this.events.forEach(element => {
          element.start = new Date(element.start);
          element.end = new Date(element.end);
          element.color = colors.red,
          element.draggable = true,
          element.resizable = {
            beforeStart: true,
            afterEnd: true
          };
          element.roomName = element.roomId != -1 ? this.rooms.find(x=>x.id == element.roomId).roomName:"Select room"
        });

        this.view = CalendarView.Month;
        this.ref.detectChanges();
    });
    });
  }

  selected(room,event){
    console.log(room);
    event.roomId=room.id;
    event.roomName = room.roomName;
    this.ref.detectChanges();
  }

}
