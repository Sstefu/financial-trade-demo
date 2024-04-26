import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { BankComponent } from './components/bank/bank.component';
import { ExporterComponent } from './components/exporter/exporter.component';
import { ImporterComponent } from './components/importer/importer.component';

@NgModule({
  declarations: [
    AppComponent,
    BankComponent,
    ExporterComponent,
    ImporterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
