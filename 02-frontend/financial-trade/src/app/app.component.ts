import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'financial-trade';
  
  @ViewChild("mainContent")
  private mainContentDiv! : ElementRef<HTMLElement>;

  constructor(){}

  ngOnInit(): void {

  }

  public onActivate(_event: any): void {
    if (this.mainContentDiv) {
      (this.mainContentDiv.nativeElement as HTMLElement).scrollTop = 0;
    }
  }
}
