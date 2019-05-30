import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LanguageTranslationModule } from './shared/modules/language-translation/language-translation.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthGuard } from './shared';
<<<<<<< HEAD
import { FormsModule } from '@angular/forms';
=======
import {UserProfilePageService} from "./shared/services";
>>>>>>> branch 'master' of https://github.com/SelmaAhmetovic/nwt-team21.git


import { httpInterceptorProviders } from '../app/layout/auth/auth-interceptor';
@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        LanguageTranslationModule,
        AppRoutingModule,
        FormsModule
    ],
    declarations: [AppComponent],
<<<<<<< HEAD
    providers: [AuthGuard, httpInterceptorProviders],
=======
    providers: [AuthGuard, UserProfilePageService],
>>>>>>> branch 'master' of https://github.com/SelmaAhmetovic/nwt-team21.git
    bootstrap: [AppComponent]
})
export class AppModule {}
