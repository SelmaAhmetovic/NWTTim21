import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-blank-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss'],

  animations: [routerTransition()]
})
export class UserProfilePageComponent implements OnInit {
  constructor() {}

  ngOnInit() {}
}
