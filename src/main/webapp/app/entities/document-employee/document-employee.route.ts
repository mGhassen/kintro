import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DocumentEmployee } from 'app/shared/model/document-employee.model';
import { DocumentEmployeeService } from './document-employee.service';
import { DocumentEmployeeComponent } from './document-employee.component';
import { DocumentEmployeeDetailComponent } from './document-employee-detail.component';
import { DocumentEmployeeUpdateComponent } from './document-employee-update.component';
import { DocumentEmployeeDeletePopupComponent } from './document-employee-delete-dialog.component';
import { IDocumentEmployee } from 'app/shared/model/document-employee.model';

@Injectable({ providedIn: 'root' })
export class DocumentEmployeeResolve implements Resolve<IDocumentEmployee> {
    constructor(private service: DocumentEmployeeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDocumentEmployee> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DocumentEmployee>) => response.ok),
                map((documentEmployee: HttpResponse<DocumentEmployee>) => documentEmployee.body)
            );
        }
        return of(new DocumentEmployee());
    }
}

export const documentEmployeeRoute: Routes = [
    {
        path: '',
        component: DocumentEmployeeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentEmployees'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DocumentEmployeeDetailComponent,
        resolve: {
            documentEmployee: DocumentEmployeeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentEmployees'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DocumentEmployeeUpdateComponent,
        resolve: {
            documentEmployee: DocumentEmployeeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentEmployees'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DocumentEmployeeUpdateComponent,
        resolve: {
            documentEmployee: DocumentEmployeeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentEmployees'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const documentEmployeePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DocumentEmployeeDeletePopupComponent,
        resolve: {
            documentEmployee: DocumentEmployeeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentEmployees'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
