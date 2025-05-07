// src/app/app.config.ts
import { importProvidersFrom } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';
import { APP_ROUTES } from './app.routes';
import { ConfirmationService, MessageService } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';

export const APP_PROVIDERS = [
  importProvidersFrom(BrowserModule),
  provideHttpClient(withInterceptorsFromDi()),
  provideAnimations(),
  provideRouter(APP_ROUTES),
  ConfirmationService,
  MessageService,
  DialogService,
];
