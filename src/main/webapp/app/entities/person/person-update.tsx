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
import { getEntity, updateEntity, createEntity, reset } from './person.reducer';
import { IPerson } from 'app/shared/model/person.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPersonUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPersonUpdateState {
  isNew: boolean;
  createdById: string;
  updatedById: string;
  assignedToId: string;
}

export class PersonUpdate extends React.Component<IPersonUpdateProps, IPersonUpdateState> {
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
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getUsers();
  }

  saveEntity = (event, errors, values) => {
    values.bornon = convertDateTimeToServer(values.bornon);
    values.diedon = convertDateTimeToServer(values.diedon);
    values.createdat = convertDateTimeToServer(values.createdat);
    values.updatedat = convertDateTimeToServer(values.updatedat);

    if (errors.length === 0) {
      const { personEntity } = this.props;
      const entity = {
        ...personEntity,
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
    this.props.history.push('/entity/person');
  };

  render() {
    const { personEntity, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vnstartupdirApp.person.home.createOrEditLabel">
              <Translate contentKey="vnstartupdirApp.person.home.createOrEditLabel">Create or edit a Person</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : personEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="person-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="person-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="uuidLabel" for="person-uuid">
                    <Translate contentKey="vnstartupdirApp.person.uuid">Uuid</Translate>
                  </Label>
                  <AvField
                    id="person-uuid"
                    type="text"
                    name="uuid"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="firstnameLabel" for="person-firstname">
                    <Translate contentKey="vnstartupdirApp.person.firstname">Firstname</Translate>
                  </Label>
                  <AvField
                    id="person-firstname"
                    type="text"
                    name="firstname"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastnameLabel" for="person-lastname">
                    <Translate contentKey="vnstartupdirApp.person.lastname">Lastname</Translate>
                  </Label>
                  <AvField
                    id="person-lastname"
                    type="text"
                    name="lastname"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bornonLabel" for="person-bornon">
                    <Translate contentKey="vnstartupdirApp.person.bornon">Bornon</Translate>
                  </Label>
                  <AvInput
                    id="person-bornon"
                    type="datetime-local"
                    className="form-control"
                    name="bornon"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.personEntity.bornon)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="diedonLabel" for="person-diedon">
                    <Translate contentKey="vnstartupdirApp.person.diedon">Diedon</Translate>
                  </Label>
                  <AvInput
                    id="person-diedon"
                    type="datetime-local"
                    className="form-control"
                    name="diedon"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.personEntity.diedon)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createdatLabel" for="person-createdat">
                    <Translate contentKey="vnstartupdirApp.person.createdat">Createdat</Translate>
                  </Label>
                  <AvInput
                    id="person-createdat"
                    type="datetime-local"
                    className="form-control"
                    name="createdat"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.personEntity.createdat)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedatLabel" for="person-updatedat">
                    <Translate contentKey="vnstartupdirApp.person.updatedat">Updatedat</Translate>
                  </Label>
                  <AvInput
                    id="person-updatedat"
                    type="datetime-local"
                    className="form-control"
                    name="updatedat"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.personEntity.updatedat)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="genderLabel" for="person-gender">
                    <Translate contentKey="vnstartupdirApp.person.gender">Gender</Translate>
                  </Label>
                  <AvField id="person-gender" type="text" name="gender" />
                </AvGroup>
                <AvGroup>
                  <Label id="titleLabel" for="person-title">
                    <Translate contentKey="vnstartupdirApp.person.title">Title</Translate>
                  </Label>
                  <AvField id="person-title" type="text" name="title" />
                </AvGroup>
                <AvGroup>
                  <Label id="homepageurlLabel" for="person-homepageurl">
                    <Translate contentKey="vnstartupdirApp.person.homepageurl">Homepageurl</Translate>
                  </Label>
                  <AvField id="person-homepageurl" type="text" name="homepageurl" />
                </AvGroup>
                <AvGroup>
                  <Label id="facebookurlLabel" for="person-facebookurl">
                    <Translate contentKey="vnstartupdirApp.person.facebookurl">Facebookurl</Translate>
                  </Label>
                  <AvField id="person-facebookurl" type="text" name="facebookurl" />
                </AvGroup>
                <AvGroup>
                  <Label id="twitterurlLabel" for="person-twitterurl">
                    <Translate contentKey="vnstartupdirApp.person.twitterurl">Twitterurl</Translate>
                  </Label>
                  <AvField id="person-twitterurl" type="text" name="twitterurl" />
                </AvGroup>
                <AvGroup>
                  <Label id="linkedinurlLabel" for="person-linkedinurl">
                    <Translate contentKey="vnstartupdirApp.person.linkedinurl">Linkedinurl</Translate>
                  </Label>
                  <AvField id="person-linkedinurl" type="text" name="linkedinurl" />
                </AvGroup>
                <AvGroup>
                  <Label id="citynameLabel" for="person-cityname">
                    <Translate contentKey="vnstartupdirApp.person.cityname">Cityname</Translate>
                  </Label>
                  <AvField id="person-cityname" type="text" name="cityname" />
                </AvGroup>
                <AvGroup>
                  <Label id="regionnameLabel" for="person-regionname">
                    <Translate contentKey="vnstartupdirApp.person.regionname">Regionname</Translate>
                  </Label>
                  <AvField id="person-regionname" type="text" name="regionname" />
                </AvGroup>
                <AvGroup>
                  <Label id="countrycodeLabel" for="person-countrycode">
                    <Translate contentKey="vnstartupdirApp.person.countrycode">Countrycode</Translate>
                  </Label>
                  <AvField id="person-countrycode" type="text" name="countrycode" />
                </AvGroup>
                <AvGroup>
                  <Label for="person-createdBy">
                    <Translate contentKey="vnstartupdirApp.person.createdBy">Created By</Translate>
                  </Label>
                  <AvInput id="person-createdBy" type="select" className="form-control" name="createdById">
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
                  <Label for="person-updatedBy">
                    <Translate contentKey="vnstartupdirApp.person.updatedBy">Updated By</Translate>
                  </Label>
                  <AvInput id="person-updatedBy" type="select" className="form-control" name="updatedById">
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
                  <Label for="person-assignedTo">
                    <Translate contentKey="vnstartupdirApp.person.assignedTo">Assigned To</Translate>
                  </Label>
                  <AvInput id="person-assignedTo" type="select" className="form-control" name="assignedToId">
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
                <Button tag={Link} id="cancel-save" to="/entity/person" replace color="info">
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
  personEntity: storeState.person.entity,
  loading: storeState.person.loading,
  updating: storeState.person.updating,
  updateSuccess: storeState.person.updateSuccess
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
)(PersonUpdate);
