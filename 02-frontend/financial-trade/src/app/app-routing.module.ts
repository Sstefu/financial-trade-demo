import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BankComponent } from './components/bank/bank.component';
import { ExporterComponent } from './components/exporter/exporter.component';
import { ImporterComponent } from './components/importer/importer.component';

const routes: Routes = [
  {path: 'importer', component: ImporterComponent},
  {path: 'exporter', component: ExporterComponent},
  {path: 'bank', component: BankComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
