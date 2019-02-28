/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KintroTestModule } from '../../../test.module';
import { DocumentEmployeeDetailComponent } from 'app/entities/document-employee/document-employee-detail.component';
import { DocumentEmployee } from 'app/shared/model/document-employee.model';

describe('Component Tests', () => {
    describe('DocumentEmployee Management Detail Component', () => {
        let comp: DocumentEmployeeDetailComponent;
        let fixture: ComponentFixture<DocumentEmployeeDetailComponent>;
        const route = ({ data: of({ documentEmployee: new DocumentEmployee('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KintroTestModule],
                declarations: [DocumentEmployeeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DocumentEmployeeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DocumentEmployeeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.documentEmployee).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
