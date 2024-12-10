import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getDepartamentos } from 'app/entities/departamento/departamento.reducer';
import { getEntities as getJefes } from 'app/entities/jefes/jefes.reducer';
import { createEntity, getEntity, reset, updateEntity } from './departamentos-jefes.reducer';

export const DepartamentosJefesUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const departamentos = useAppSelector(state => state.departamento.entities);
  const jefes = useAppSelector(state => state.jefes.entities);
  const departamentosJefesEntity = useAppSelector(state => state.departamentosJefes.entity);
  const loading = useAppSelector(state => state.departamentosJefes.loading);
  const updating = useAppSelector(state => state.departamentosJefes.updating);
  const updateSuccess = useAppSelector(state => state.departamentosJefes.updateSuccess);

  const handleClose = () => {
    navigate(`/departamentos-jefes${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDepartamentos({}));
    dispatch(getJefes({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...departamentosJefesEntity,
      ...values,
      departamento: departamentos.find(it => it.id.toString() === values.departamento?.toString()),
      jefe: jefes.find(it => it.id.toString() === values.jefe?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...departamentosJefesEntity,
          departamento: departamentosJefesEntity?.departamento?.id,
          jefe: departamentosJefesEntity?.jefe?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dundermifflinApp.departamentosJefes.home.createOrEditLabel" data-cy="DepartamentosJefesCreateUpdateHeading">
            <Translate contentKey="dundermifflinApp.departamentosJefes.home.createOrEditLabel">
              Create or edit a DepartamentosJefes
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="departamentos-jefes-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                id="departamentos-jefes-departamento"
                name="departamento"
                data-cy="departamento"
                label={translate('dundermifflinApp.departamentosJefes.departamento')}
                type="select"
              >
                <option value="" key="0" />
                {departamentos
                  ? departamentos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="departamentos-jefes-jefe"
                name="jefe"
                data-cy="jefe"
                label={translate('dundermifflinApp.departamentosJefes.jefe')}
                type="select"
              >
                <option value="" key="0" />
                {jefes
                  ? jefes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/departamentos-jefes" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DepartamentosJefesUpdate;
