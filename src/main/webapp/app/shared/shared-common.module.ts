import { NgModule } from '@angular/core';

import { KintroSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [KintroSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [KintroSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class KintroSharedCommonModule {}
