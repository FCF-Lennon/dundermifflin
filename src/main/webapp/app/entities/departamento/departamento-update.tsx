import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getEmpleados } from 'app/entities/empleado/empleado.reducer';
import { createEntity, getEntity, reset, updateEntity } from './departamento.reducer';

export const DepartamentoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const empleados = useAppSelector(state => state.empleado.entities);
  const departamentoEntity = useAppSelector(state => state.departamento.entity);
  const loading = useAppSelector(state => state.departamento.loading);
  const updating = useAppSelector(state => state.departamento.updating);
  const updateSuccess = useAppSelector(state => state.departamento.updateSuccess);

  const handleClose = () => {
    navigate(`/departamento${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getEmpleados({}));
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
      ...departamentoEntity,
      ...values,
      empleados: mapIdList(values.empleados),
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
          ...departamentoEntity,
          empleados: departamentoEntity?.empleados?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dundermifflinApp.departamento.home.createOrEditLabel" data-cy="DepartamentoCreateUpdateHeading">
            <Translate contentKey="dundermifflinApp.departamento.home.createOrEditLabel">Create or edit a Departamento</Translate>
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
                  id="departamento-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('dundermifflinApp.departamento.nombreDepartamento')}
                id="departamento-nombreDepartamento"
                name="nombreDepartamento"
                data-cy="nombreDepartamento"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('dundermifflinApp.departamento.ubicacionDepartamento')}
                id="departamento-ubicacionDepartamento"
                name="ubicacionDepartamento"
                data-cy="ubicacionDepartamento"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('dundermifflinApp.departamento.empleados')}
                id="departamento-empleados"
                data-cy="empleados"
                type="select"
                multiple
                name="empleados"
              >
                <option value="" key="0" />
                {empleados
                  ? empleados.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/departamento" replace color="info">
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

export default DepartamentoUpdate;
