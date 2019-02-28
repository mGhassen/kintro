/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KintroTestModule } from '../../../test.module';
import { DocumentEmployeeUpdateComponent } from 'app/entities/document-employee/document-employee-update.component';
import { DocumentEmployeeService } from 'app/entities/document-employee/document-employee.service';
import { DocumentEmployee } from 'app/shared/model/document-employee.model';

describe('Component Tests', () => {
    describe('DocumentEmployee Management Update Component', () => {
        let comp: DocumentEmployeeUpdateComponent;
        let fixture: ComponentFixture<DocumentEmployeeUpdateComponent>;
        let service: DocumentEmployeeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KintroTestModule],
                declarations: [DocumentEmployeeUpdateComponent]
            })
                .overrideTemplate(DocumentEmployeeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DocumentEmployeeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DocumentEmployeeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DocumentEmployee('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.documentEmployee = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DocumentEmployee();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.documentEmployee = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
