import { Routes } from '@angular/router';
import { PostProductComponent } from './components/post-product/post-product.component';
import { GetProductsComponent } from './components/get-products/get-products.component';

export const routes: Routes = [
    {path: "product", component: PostProductComponent},
    {path: "products", component: GetProductsComponent}
];
