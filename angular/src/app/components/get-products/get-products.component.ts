import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './get-products.component.html',
  styleUrl: './get-products.component.css'
})
export class GetProductsComponent {

  products: any[] = [];

  constructor(private productService: ProductService){}

  ngOnInit(){
    this.getProducts();
  }

  getProducts(){
    this.productService.getAllProducts().subscribe((res => {
      console.log(res)
      this.products =  res;
    }))
  }
}
