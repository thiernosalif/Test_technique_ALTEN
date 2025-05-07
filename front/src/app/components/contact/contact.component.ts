import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})

export class ContactComponent {

  successMessage: string | null = null;

  contactForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    message: ['', [Validators.required, Validators.maxLength(300)]],
  });

  constructor(private fb: FormBuilder) {}

  onSubmit() {
    if (this.contactForm.valid) {
      console.log(this.contactForm.value);

      this.successMessage = 'Demande de contact envoyée avec succès.';
      this.contactForm.reset();
    }
  }

}

