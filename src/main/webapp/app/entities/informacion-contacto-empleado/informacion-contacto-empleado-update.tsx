import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getEmpleados } from 'app/entities/empleado/empleado.reducer';
import { createEntity, getEntity, reset, updateEntity } from './informacion-contacto-empleado.reducer';

export const InformacionContactoEmpleadoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const empleados = useAppSelector(state => state.empleado.entities);
  const informacionContactoEmpleadoEntity = useAppSelector(state => state.informacionContactoEmpleado.entity);
  const loading = useAppSelector(state => state.informacionContactoEmpleado.loading);
  const updating = useAppSelector(state => state.informacionContactoEmpleado.updating);
  const updateSuccess = useAppSelector(state => state.informacionContactoEmpleado.updateSuccess);

  const handleClose = () => {
    navigate(`/informacion-contacto-empleado${location.search}`);
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
      ...informacionContactoEmpleadoEntity,
      ...values,
      empleado: empleados.find(it => it.id.toString() === values.empleado?.toString()),
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
          ...informacionContactoEmpleadoEntity,
          empleado: informacionContactoEmpleadoEntity?.empleado?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="dundermifflinApp.informacionContactoEmpleado.home.createOrEditLabel"
            data-cy="InformacionContactoEmpleadoCreateUpdateHeading"
          >
            <Translate contentKey="dundermifflinApp.informacionContactoEmpleado.home.createOrEditLabel">
              Create or edit a InformacionContactoEmpleado
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
                  id="informacion-contacto-empleado-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('dundermifflinApp.informacionContactoEmpleado.telefono')}
                id="informacion-contacto-empleado-telefono"
                name="telefono"
                data-cy="telefono"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="informacion-contacto-empleado-empleado"
                name="empleado"
                data-cy="empleado"
                label={translate('dundermifflinApp.informacionContactoEmpleado.empleado')}
                type="select"
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
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/informacion-contacto-empleado"
                replace
                color="info"
              >
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

export default InformacionContactoEmpleadoUpdate;
