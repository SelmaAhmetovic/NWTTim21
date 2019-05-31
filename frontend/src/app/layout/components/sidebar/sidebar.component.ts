import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { TokenStorageService } from '../../auth/token-storage.service';

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
    isActive: boolean;
    collapsed: boolean;
    showMenu: string;
    pushRightClass: string;
    private roles: string[];
    authority: string;
    username: string;


    @Output() collapsedEvent = new EventEmitter<boolean>();

    constructor(private tokenStorage: TokenStorageService, private translate: TranslateService, public router: Router) {
        this.router.events.subscribe(val => {
            if (
                val instanceof NavigationEnd &&
                window.innerWidth <= 992 &&
                this.isToggled()
            ) {
                this.toggleSidebar();
            }
        });
    }

    ngOnInit() {
        console.log('test');
        this.isActive = false;
        this.collapsed = false;
        this.showMenu = '';
        this.pushRightClass = 'push-right';
        this.authority = '';
        this.username = this.tokenStorage.getUsername();
        if (this.tokenStorage.getToken()) {
            this.roles = this.tokenStorage.getAuthorities();
            this.roles.every(role => {
              if (role === 'ROLE_ADMIN') {
                this.authority = 'admin';
                return false;
              } else if (role === 'ROLE_USER') {
                this.authority = 'user';
                return false;
              }
              this.authority = 'user';
              return true;
            });
          }
    }

    canSeeUser() {
        if (this.tokenStorage.getToken()) {
            this.roles = this.tokenStorage.getAuthorities();
            return this.roles.indexOf('ROLE_USER') >= 0;
        }
    }

    canSeeRooms() {
        if (this.tokenStorage.getToken()) {
            this.roles = this.tokenStorage.getAuthorities();
            return this.roles.indexOf('ROLE_ADMIN') >= 0;
        }
    }
    eventCalled() {
        this.isActive = !this.isActive;
    }

    addExpandClass(element: any) {
        if (element === this.showMenu) {
            this.showMenu = '0';
        } else {
            this.showMenu = element;
        }
    }

    toggleCollapsed() {
        this.collapsed = !this.collapsed;
        this.collapsedEvent.emit(this.collapsed);
    }

    isToggled(): boolean {
        const dom: Element = document.querySelector('body');
        return dom.classList.contains(this.pushRightClass);
    }

    toggleSidebar() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle(this.pushRightClass);
    }

    rltAndLtr() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle('rtl');
    }

    changeLang(language: string) {
        this.translate.use(language);
    }

    onLoggedout() {
        localStorage.removeItem('isLoggedin');
        this.tokenStorage.signOut();
    }
}
