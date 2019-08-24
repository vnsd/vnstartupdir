import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPersonCompanyRelation, defaultValue } from 'app/shared/model/person-company-relation.model';

export const ACTION_TYPES = {
  FETCH_PERSONCOMPANYRELATION_LIST: 'personCompanyRelation/FETCH_PERSONCOMPANYRELATION_LIST',
  FETCH_PERSONCOMPANYRELATION: 'personCompanyRelation/FETCH_PERSONCOMPANYRELATION',
  CREATE_PERSONCOMPANYRELATION: 'personCompanyRelation/CREATE_PERSONCOMPANYRELATION',
  UPDATE_PERSONCOMPANYRELATION: 'personCompanyRelation/UPDATE_PERSONCOMPANYRELATION',
  DELETE_PERSONCOMPANYRELATION: 'personCompanyRelation/DELETE_PERSONCOMPANYRELATION',
  RESET: 'personCompanyRelation/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPersonCompanyRelation>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PersonCompanyRelationState = Readonly<typeof initialState>;

// Reducer

export default (state: PersonCompanyRelationState = initialState, action): PersonCompanyRelationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PERSONCOMPANYRELATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PERSONCOMPANYRELATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PERSONCOMPANYRELATION):
    case REQUEST(ACTION_TYPES.UPDATE_PERSONCOMPANYRELATION):
    case REQUEST(ACTION_TYPES.DELETE_PERSONCOMPANYRELATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PERSONCOMPANYRELATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PERSONCOMPANYRELATION):
    case FAILURE(ACTION_TYPES.CREATE_PERSONCOMPANYRELATION):
    case FAILURE(ACTION_TYPES.UPDATE_PERSONCOMPANYRELATION):
    case FAILURE(ACTION_TYPES.DELETE_PERSONCOMPANYRELATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERSONCOMPANYRELATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERSONCOMPANYRELATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PERSONCOMPANYRELATION):
    case SUCCESS(ACTION_TYPES.UPDATE_PERSONCOMPANYRELATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PERSONCOMPANYRELATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/person-company-relations';

// Actions

export const getEntities: ICrudGetAllAction<IPersonCompanyRelation> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PERSONCOMPANYRELATION_LIST,
  payload: axios.get<IPersonCompanyRelation>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPersonCompanyRelation> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PERSONCOMPANYRELATION,
    payload: axios.get<IPersonCompanyRelation>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPersonCompanyRelation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PERSONCOMPANYRELATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPersonCompanyRelation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PERSONCOMPANYRELATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPersonCompanyRelation> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PERSONCOMPANYRELATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
