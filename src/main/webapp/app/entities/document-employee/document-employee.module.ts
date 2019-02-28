import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KintroSharedModule } from 'app/shared';
import {
    DocumentEmployeeComponent,
    DocumentEmployeeDetailComponent,
    DocumentEmployeeUpdateComponent,
    DocumentEmployeeDeletePopupComponent,
    DocumentEmployeeDeleteDialogComponent,
    documentEmployeeRoute,
    documentEmployeePopupRoute
} from './';

const ENTITY_STATES = [...documentEmployeeRoute, ...documentEmployeePopupRoute];

@NgModule({
    imports: [KintroSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DocumentEmployeeComponent,
        DocumentEmployeeDetailComponent,
        DocumentEmployeeUpdateComponent,
        DocumentEmployeeDeleteDialogComponent,
        DocumentEmployeeDeletePopupComponent
    ],
    entryComponents: [
        DocumentEmployeeComponent,
        DocumentEmployeeUpdateComponent,
        DocumentEmployeeDeleteDialogComponent,
        DocumentEmployeeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KintroDocumentEmployeeModule {}
