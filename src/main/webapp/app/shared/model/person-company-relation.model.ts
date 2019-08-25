import { ICompany } from 'app/shared/model/company.model';
import { IPerson } from 'app/shared/model/person.model';

export interface IPersonCompanyRelation {
  id?: number;
  relationCode?: string;
  company?: ICompany;
  person?: IPerson;
  company?: ICompany;
  person?: IPerson;
}

export const defaultValue: Readonly<IPersonCompanyRelation> = {};
