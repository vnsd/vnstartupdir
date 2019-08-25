import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './company.reducer';
import { ICompany } from 'app/shared/model/company.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICompanyUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICompanyUpdateState {
  isNew: boolean;
  createdById: string;
  updatedById: string;
  assignedToId: string;
}

export class CompanyUpdate extends React.Component<ICompanyUpdateProps, ICompanyUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      createdById: '0',
      updatedById: '0',
      assignedToId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (!this.state.isNew) {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getUsers();
  }

  saveEntity = (event, errors, values) => {
    values.foundedon = convertDateTimeToServer(values.foundedon);
    values.closedon = convertDateTimeToServer(values.closedon);
    values.createdat = convertDateTimeToServer(values.createdat);
    values.updatedat = convertDateTimeToServer(values.updatedat);

    if (errors.length === 0) {
      const { companyEntity } = this.props;
      const entity = {
        ...companyEntity,
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
    this.props.history.push('/entity/company');
  };

  render() {
    const { companyEntity, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vnstartupdirApp.company.home.createOrEditLabel">
              <Translate contentKey="vnstartupdirApp.company.home.createOrEditLabel">Create or edit a Company</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : companyEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="company-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="company-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="uuidLabel" for="company-uuid">
                    <Translate contentKey="vnstartupdirApp.company.uuid">Uuid</Translate>
                  </Label>
                  <AvField
                    id="company-uuid"
                    type="text"
                    name="uuid"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeLabel" for="company-type">
                    <Translate contentKey="vnstartupdirApp.company.type">Type</Translate>
                  </Label>
                  <AvField
                    id="company-type"
                    type="text"
                    name="type"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="company-name">
                    <Translate contentKey="vnstartupdirApp.company.name">Name</Translate>
                  </Label>
                  <AvField
                    id="company-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="shortdescriptionLabel" for="company-shortdescription">
                    <Translate contentKey="vnstartupdirApp.company.shortdescription">Shortdescription</Translate>
                  </Label>
                  <AvField id="company-shortdescription" type="text" name="shortdescription" />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="company-description">
                    <Translate contentKey="vnstartupdirApp.company.description">Description</Translate>
                  </Label>
                  <AvField id="company-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="foundedonLabel" for="company-foundedon">
                    <Translate contentKey="vnstartupdirApp.company.foundedon">Foundedon</Translate>
                  </Label>
                  <AvInput
                    id="company-foundedon"
                    type="datetime-local"
                    className="form-control"
                    name="foundedon"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.companyEntity.foundedon)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="closedonLabel" for="company-closedon">
                    <Translate contentKey="vnstartupdirApp.company.closedon">Closedon</Translate>
                  </Label>
                  <AvInput
                    id="company-closedon"
                    type="datetime-local"
                    className="form-control"
                    name="closedon"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.companyEntity.closedon)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numemployeesminLabel" for="company-numemployeesmin">
                    <Translate contentKey="vnstartupdirApp.company.numemployeesmin">Numemployeesmin</Translate>
                  </Label>
                  <AvField id="company-numemployeesmin" type="string" className="form-control" name="numemployeesmin" />
                </AvGroup>
                <AvGroup>
                  <Label id="numemployeesmaxLabel" for="company-numemployeesmax">
                    <Translate contentKey="vnstartupdirApp.company.numemployeesmax">Numemployeesmax</Translate>
                  </Label>
                  <AvField id="company-numemployeesmax" type="string" className="form-control" name="numemployeesmax" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalfundingusdLabel" for="company-totalfundingusd">
                    <Translate contentKey="vnstartupdirApp.company.totalfundingusd">Totalfundingusd</Translate>
                  </Label>
                  <AvField id="company-totalfundingusd" type="string" className="form-control" name="totalfundingusd" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalfundingvndLabel" for="company-totalfundingvnd">
                    <Translate contentKey="vnstartupdirApp.company.totalfundingvnd">Totalfundingvnd</Translate>
                  </Label>
                  <AvField id="company-totalfundingvnd" type="string" className="form-control" name="totalfundingvnd" />
                </AvGroup>
                <AvGroup>
                  <Label id="numberofinvestmentsLabel" for="company-numberofinvestments">
                    <Translate contentKey="vnstartupdirApp.company.numberofinvestments">Numberofinvestments</Translate>
                  </Label>
                  <AvField id="company-numberofinvestments" type="string" className="form-control" name="numberofinvestments" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdatLabel" for="company-createdat">
                    <Translate contentKey="vnstartupdirApp.company.createdat">Createdat</Translate>
                  </Label>
                  <AvInput
                    id="company-createdat"
                    type="datetime-local"
                    className="form-control"
                    name="createdat"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.companyEntity.createdat)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedatLabel" for="company-updatedat">
                    <Translate contentKey="vnstartupdirApp.company.updatedat">Updatedat</Translate>
                  </Label>
                  <AvInput
                    id="company-updatedat"
                    type="datetime-local"
                    className="form-control"
                    name="updatedat"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.companyEntity.updatedat)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="contactemailLabel" for="company-contactemail">
                    <Translate contentKey="vnstartupdirApp.company.contactemail">Contactemail</Translate>
                  </Label>
                  <AvField id="company-contactemail" type="text" name="contactemail" />
                </AvGroup>
                <AvGroup>
                  <Label id="phonenumberLabel" for="company-phonenumber">
                    <Translate contentKey="vnstartupdirApp.company.phonenumber">Phonenumber</Translate>
                  </Label>
                  <AvField id="company-phonenumber" type="text" name="phonenumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="homepageurlLabel" for="company-homepageurl">
                    <Translate contentKey="vnstartupdirApp.company.homepageurl">Homepageurl</Translate>
                  </Label>
                  <AvField id="company-homepageurl" type="text" name="homepageurl" />
                </AvGroup>
                <AvGroup>
                  <Label id="facebookurlLabel" for="company-facebookurl">
                    <Translate contentKey="vnstartupdirApp.company.facebookurl">Facebookurl</Translate>
                  </Label>
                  <AvField id="company-facebookurl" type="text" name="facebookurl" />
                </AvGroup>
                <AvGroup>
                  <Label id="twitterurlLabel" for="company-twitterurl">
                    <Translate contentKey="vnstartupdirApp.company.twitterurl">Twitterurl</Translate>
                  </Label>
                  <AvField id="company-twitterurl" type="text" name="twitterurl" />
                </AvGroup>
                <AvGroup>
                  <Label id="linkedinurlLabel" for="company-linkedinurl">
                    <Translate contentKey="vnstartupdirApp.company.linkedinurl">Linkedinurl</Translate>
                  </Label>
                  <AvField id="company-linkedinurl" type="text" name="linkedinurl" />
                </AvGroup>
                <AvGroup>
                  <Label id="citynameLabel" for="company-cityname">
                    <Translate contentKey="vnstartupdirApp.company.cityname">Cityname</Translate>
                  </Label>
                  <AvField id="company-cityname" type="text" name="cityname" />
                </AvGroup>
                <AvGroup>
                  <Label id="regionnameLabel" for="company-regionname">
                    <Translate contentKey="vnstartupdirApp.company.regionname">Regionname</Translate>
                  </Label>
                  <AvField id="company-regionname" type="text" name="regionname" />
                </AvGroup>
                <AvGroup>
                  <Label id="countrycodeLabel" for="company-countrycode">
                    <Translate contentKey="vnstartupdirApp.company.countrycode">Countrycode</Translate>
                  </Label>
                  <AvField id="company-countrycode" type="text" name="countrycode" />
                </AvGroup>
                <AvGroup>
                  <Label for="company-createdBy">
                    <Translate contentKey="vnstartupdirApp.company.createdBy">Created By</Translate>
                  </Label>
                  <AvInput id="company-createdBy" type="select" className="form-control" name="createdById">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="company-updatedBy">
                    <Translate contentKey="vnstartupdirApp.company.updatedBy">Updated By</Translate>
                  </Label>
                  <AvInput id="company-updatedBy" type="select" className="form-control" name="updatedById">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="company-assignedTo">
                    <Translate contentKey="vnstartupdirApp.company.assignedTo">Assigned To</Translate>
                  </Label>
                  <AvInput id="company-assignedTo" type="select" className="form-control" name="assignedToId">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/company" replace color="info">
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
  users: storeState.userManagement.users,
  companyEntity: storeState.company.entity,
  loading: storeState.company.loading,
  updating: storeState.company.updating,
  updateSuccess: storeState.company.updateSuccess
});

const mapDispatchToProps = {
  getUsers,
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
)(CompanyUpdate);
