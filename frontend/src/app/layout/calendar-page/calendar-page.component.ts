import { ChangeDetectionStrategy, ViewChild, TemplateRef, Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { startOfDay, endOfDay, subDays, addDays, endOfMonth, isSameDay, isSameMonth, addHours} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CalendarEventAction, CalendarEventTimesChangedEvent, CalendarView } from 'angular-calendar';
import { CalendarEvent } from './calendar-event';
import { CalendarService } from './calendar-page.service';
import { ActivatedRoute, Router } from '@angular/router';

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

  constructor(private modal: NgbModal, private calendarService: CalendarService, private router: Router, private ref: ChangeDetectorRef) {

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

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];
  activeDayIsOpen = true;
  ngOnInit() {

    this.view = CalendarView.Month;
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

      });

      this.view = CalendarView.Month;
      this.ref.detectChanges();
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
        id: -1,
        // tslint:disable-next-line:radix
        userId: parseInt(localStorage.getItem('userId'))
      }
    ];
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter(event => event !== eventToDelete);
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  saveEvent(eventToSave: CalendarEvent) {
    this.calendarService.save(eventToSave).subscribe(data => {
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

        });

        this.view = CalendarView.Month;
        this.ref.detectChanges();
    });
  };


}
