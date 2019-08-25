import { Moment } from 'moment';
import { IPersonCompanyRelation } from 'app/shared/model/person-company-relation.model';

export interface IPerson {
  id?: number;
  uuid?: string;
  permalink?: string;
  firstname?: string;
  lastname?: string;
  alsoknownas?: string;
  bio?: string;
  profileimageid?: number;
  roleinvestor?: boolean;
  bornon?: Moment;
  bornontrustcode?: number;
  diedon?: Moment;
  createdat?: Moment;
  updatedat?: Moment;
  permalinkaliases?: string;
  gender?: string;
  rank?: number;
  primaryaffiliationid?: number;
  primarylocationid?: number;
  primaryimageid?: number;
  title?: string;
  homepageurl?: string;
  facebookurl?: string;
  twitterurl?: string;
  linkedinurl?: string;
  cityname?: string;
  regionname?: string;
  countrycode?: string;
  companies?: IPersonCompanyRelation[];
}

export const defaultValue: Readonly<IPerson> = {
  roleinvestor: false
};
