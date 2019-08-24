import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PersonCompanyRelation from './person-company-relation';
import PersonCompanyRelationDetail from './person-company-relation-detail';
import PersonCompanyRelationUpdate from './person-company-relation-update';
import PersonCompanyRelationDeleteDialog from './person-company-relation-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PersonCompanyRelationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PersonCompanyRelationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PersonCompanyRelationDetail} />
      <ErrorBoundaryRoute path={match.url} component={PersonCompanyRelation} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PersonCompanyRelationDeleteDialog} />
  </>
);

export default Routes;
