import { Component, computed, inject, signal } from "@angular/core";
import { MenuItem } from "primeng/api";
import { PanelMenuModule } from 'primeng/panelmenu';
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: "app-panel-menu",
  standalone: true,
  imports: [PanelMenuModule],
  template: `<p-panelMenu [model]="menuItems()" styleClass="w-full" />`
})
export class PanelMenuComponent {
  private authService = inject(AuthService);
  private isLoggedIn = signal(false);

  constructor() {
    this.authService.isLoggedIn$.subscribe(value => this.isLoggedIn.set(value));
  }

  readonly menuItems = computed<MenuItem[]>(() => {
    const items: MenuItem[] = [
      { label: 'Accueil', icon: 'pi pi-home', routerLink: ['/home'] },
      { label: 'Produits', icon: 'pi pi-barcode', routerLink: ['/products/list'] },
      { label: 'Contact', icon: 'pi pi-users', routerLink: ['/contact'] },
    ];

    if (!this.isLoggedIn()) {
      items.push(
        { label: 'Connexion', icon: 'pi pi-sign-in', routerLink: ['/login'] },
        { label: 'Inscription', icon: 'pi pi-user-plus', routerLink: ['/register'] }
      );
    }

    return items;
  });
}
