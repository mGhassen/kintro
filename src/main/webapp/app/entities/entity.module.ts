import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'employee',
                loadChildren: './employee/employee.module#KintroEmployeeModule'
            },
            {
                path: 'job',
                loadChildren: './job/job.module#KintroJobModule'
            },
            {
                path: 'document-employee',
                loadChildren: './document-employee/document-employee.module#KintroDocumentEmployeeModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KintroEntityModule {}
