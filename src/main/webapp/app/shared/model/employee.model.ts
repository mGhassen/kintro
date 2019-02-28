import { IJob } from 'app/shared/model/job.model';
import { IDocument } from 'app/shared/model/document.model';

export interface IEmployee {
    id?: string;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    adresse?: string;
    nationalite?: number;
    jobs?: IJob[];
    documents?: IDocument[];
}

export class Employee implements IEmployee {
    constructor(
        public id?: string,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public phoneNumber?: string,
        public adresse?: string,
        public nationalite?: number,
        public jobs?: IJob[],
        public documents?: IDocument[]
    ) {}
}
