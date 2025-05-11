import { Component, OnInit, inject, signal } from "@angular/core";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import {ProductService} from "../../../services/product.service";
import {CartService} from "../../../services/cart.service";
import {MessageService} from "primeng/api";

const emptyProduct: Product = {
  id: null,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent],
})
export class ProductListComponent implements OnInit {

  constructor(
    private messageService: MessageService
  ) {}

  private readonly productsService = inject(ProductService);
  public readonly products = this.productsService.products;

  private readonly cartService = inject(CartService);
  public readonly carts = this.cartService;

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  ngOnInit() {
    this.productsService.getAll().subscribe();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }


  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  addToCart(product: Product): void {
   /* if (!product || !product.id) {
      this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Produit invalide' });
      return;
    }*/

    this.carts.addToCart(product).subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Ajouté au panier', detail: `${product.name} a été ajouté.` });
      },
      error: (err) => {
        this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Impossible d\'ajouter le produit au panier' });
        console.error(err);
      }
    });
  }
}
