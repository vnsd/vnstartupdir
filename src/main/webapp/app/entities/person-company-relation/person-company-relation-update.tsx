import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICompany } from 'app/shared/model/company.model';
import { getEntities as getCompanies } from 'app/entities/company/company.reducer';
import { IPerson } from 'app/shared/model/person.model';
import { getEntities as getPeople } from 'app/entities/person/person.reducer';
import { getEntity, updateEntity, createEntity, reset } from './person-company-relation.reducer';
import { IPersonCompanyRelation } from 'app/shared/model/person-company-relation.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPersonCompanyRelationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPersonCompanyRelationUpdateState {
  isNew: boolean;
  companyId: string;
  personId: string;
}

export class PersonCompanyRelationUpdate extends React.Component<IPersonCompanyRelationUpdateProps, IPersonCompanyRelationUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      companyId: '0',
      personId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getCompanies();
    this.props.getPeople();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { personCompanyRelationEntity } = this.props;
      const entity = {
        ...personCompanyRelationEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/person-company-relation');
  };

  render() {
    const { personCompanyRelationEntity, companies, people, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vnstartupdirApp.personCompanyRelation.home.createOrEditLabel">
              <Translate contentKey="vnstartupdirApp.personCompanyRelation.home.createOrEditLabel">
                Create or edit a PersonCompanyRelation
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : personCompanyRelationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="person-company-relation-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="person-company-relation-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="relationCodeLabel" for="person-company-relation-relationCode">
                    <Translate contentKey="vnstartupdirApp.personCompanyRelation.relationCode">Relation Code</Translate>
                  </Label>
                  <AvField id="person-company-relation-relationCode" type="text" name="relationCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="person-company-relation-company">
                    <Translate contentKey="vnstartupdirApp.personCompanyRelation.company">Company</Translate>
                  </Label>
                  <AvInput id="person-company-relation-company" type="select" className="form-control" name="company.id">
                    <option value="" key="0" />
                    {companies
                      ? companies.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.name}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="person-company-relation-person">
                    <Translate contentKey="vnstartupdirApp.personCompanyRelation.person">Person</Translate>
                  </Label>
                  <AvInput id="person-company-relation-person" type="select" className="form-control" name="person.id">
                    <option value="" key="0" />
                    {people
                      ? people.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.firstname} {otherEntity.lastname}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/person-company-relation" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  companies: storeState.company.entities,
  people: storeState.person.entities,
  personCompanyRelationEntity: storeState.personCompanyRelation.entity,
  loading: storeState.personCompanyRelation.loading,
  updating: storeState.personCompanyRelation.updating,
  updateSuccess: storeState.personCompanyRelation.updateSuccess
});

const mapDispatchToProps = {
  getCompanies,
  getPeople,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PersonCompanyRelationUpdate);
