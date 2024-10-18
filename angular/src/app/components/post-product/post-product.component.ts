import { Component } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';


@Component({
  selector: 'app-post-product',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './post-product.component.html',
  styleUrls: ['./post-product.component.css']  // Assurez-vous de la correction
})
export class PostProductComponent {

  postPostForm!: FormGroup;


  constructor(private productService: ProductService, private fmb: FormBuilder){}

  ngOnInit(){
    this.postPostForm = this.fmb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
      price: [null, [Validators.required]]
    })
  }

  postCustomer(){
    console.log(this.postPostForm.value);
    if (this.postPostForm.valid) {
      this.productService.postCustomer(this.postPostForm.value).subscribe((res) => {
        console.log(res);
      })
    }
  }
}
