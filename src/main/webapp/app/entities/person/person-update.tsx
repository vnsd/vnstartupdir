import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './person.reducer';
import { IPerson } from 'app/shared/model/person.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPersonUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPersonUpdateState {
  isNew: boolean;
}

export class PersonUpdate extends React.Component<IPersonUpdateProps, IPersonUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
    const { personEntity, loading, updating } = this.props;
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
                  <Label id="permalinkLabel" for="person-permalink">
                    <Translate contentKey="vnstartupdirApp.person.permalink">Permalink</Translate>
                  </Label>
                  <AvField
                    id="person-permalink"
                    type="text"
                    name="permalink"
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
                  <Label id="alsoknownasLabel" for="person-alsoknownas">
                    <Translate contentKey="vnstartupdirApp.person.alsoknownas">Alsoknownas</Translate>
                  </Label>
                  <AvField id="person-alsoknownas" type="text" name="alsoknownas" />
                </AvGroup>
                <AvGroup>
                  <Label id="bioLabel" for="person-bio">
                    <Translate contentKey="vnstartupdirApp.person.bio">Bio</Translate>
                  </Label>
                  <AvField id="person-bio" type="text" name="bio" />
                </AvGroup>
                <AvGroup>
                  <Label id="profileimageidLabel" for="person-profileimageid">
                    <Translate contentKey="vnstartupdirApp.person.profileimageid">Profileimageid</Translate>
                  </Label>
                  <AvField id="person-profileimageid" type="string" className="form-control" name="profileimageid" />
                </AvGroup>
                <AvGroup>
                  <Label id="roleinvestorLabel" check>
                    <AvInput id="person-roleinvestor" type="checkbox" className="form-control" name="roleinvestor" />
                    <Translate contentKey="vnstartupdirApp.person.roleinvestor">Roleinvestor</Translate>
                  </Label>
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
                  <Label id="bornontrustcodeLabel" for="person-bornontrustcode">
                    <Translate contentKey="vnstartupdirApp.person.bornontrustcode">Bornontrustcode</Translate>
                  </Label>
                  <AvField id="person-bornontrustcode" type="string" className="form-control" name="bornontrustcode" />
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
                  <Label id="permalinkaliasesLabel" for="person-permalinkaliases">
                    <Translate contentKey="vnstartupdirApp.person.permalinkaliases">Permalinkaliases</Translate>
                  </Label>
                  <AvField id="person-permalinkaliases" type="text" name="permalinkaliases" />
                </AvGroup>
                <AvGroup>
                  <Label id="genderLabel" for="person-gender">
                    <Translate contentKey="vnstartupdirApp.person.gender">Gender</Translate>
                  </Label>
                  <AvField id="person-gender" type="text" name="gender" />
                </AvGroup>
                <AvGroup>
                  <Label id="rankLabel" for="person-rank">
                    <Translate contentKey="vnstartupdirApp.person.rank">Rank</Translate>
                  </Label>
                  <AvField id="person-rank" type="string" className="form-control" name="rank" />
                </AvGroup>
                <AvGroup>
                  <Label id="primaryaffiliationidLabel" for="person-primaryaffiliationid">
                    <Translate contentKey="vnstartupdirApp.person.primaryaffiliationid">Primaryaffiliationid</Translate>
                  </Label>
                  <AvField id="person-primaryaffiliationid" type="string" className="form-control" name="primaryaffiliationid" />
                </AvGroup>
                <AvGroup>
                  <Label id="primarylocationidLabel" for="person-primarylocationid">
                    <Translate contentKey="vnstartupdirApp.person.primarylocationid">Primarylocationid</Translate>
                  </Label>
                  <AvField id="person-primarylocationid" type="string" className="form-control" name="primarylocationid" />
                </AvGroup>
                <AvGroup>
                  <Label id="primaryimageidLabel" for="person-primaryimageid">
                    <Translate contentKey="vnstartupdirApp.person.primaryimageid">Primaryimageid</Translate>
                  </Label>
                  <AvField id="person-primaryimageid" type="string" className="form-control" name="primaryimageid" />
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
  personEntity: storeState.person.entity,
  loading: storeState.person.loading,
  updating: storeState.person.updating,
  updateSuccess: storeState.person.updateSuccess
});

const mapDispatchToProps = {
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
