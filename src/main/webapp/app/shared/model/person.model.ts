import { Moment } from 'moment';

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
}

export const defaultValue: Readonly<IPerson> = {
  roleinvestor: false
};
