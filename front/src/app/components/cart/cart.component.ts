import {Component, OnInit} from '@angular/core';
import {Product} from "../../products/data-access/product.model";
import {CartService} from "../../services/cart.service";


@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit{

  cartProducts: Product[] = [];
  loading = true;
  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.getCartProducts();
  }

  getCartProducts(): void {
    this.loading = true;
    this.cartService.getCart().subscribe({
      next: (products) => {
        this.cartProducts = products;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement du panier', err);
        this.loading = false;
      }
    });
  }

  removeFromCart(productId: number): void {
    this.cartService.removeFromCart(productId).subscribe({
      next: () => {
        this.cartProducts = this.cartProducts.filter(p => p.id !== productId);
      },
      error: (err) => {
        console.error('Erreur lors de la suppression du produit', err);
      }
    });
  }
}

