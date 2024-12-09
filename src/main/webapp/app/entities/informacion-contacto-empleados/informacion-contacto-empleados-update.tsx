import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './informacion-contacto-empleados.reducer';

export const InformacionContactoEmpleadosUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const informacionContactoEmpleadosEntity = useAppSelector(state => state.informacionContactoEmpleados.entity);
  const loading = useAppSelector(state => state.informacionContactoEmpleados.loading);
  const updating = useAppSelector(state => state.informacionContactoEmpleados.updating);
  const updateSuccess = useAppSelector(state => state.informacionContactoEmpleados.updateSuccess);

  const handleClose = () => {
    navigate(`/informacion-contacto-empleados${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
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
      ...informacionContactoEmpleadosEntity,
      ...values,
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
          ...informacionContactoEmpleadosEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="dundermifflinApp.informacionContactoEmpleados.home.createOrEditLabel"
            data-cy="InformacionContactoEmpleadosCreateUpdateHeading"
          >
            <Translate contentKey="dundermifflinApp.informacionContactoEmpleados.home.createOrEditLabel">
              Create or edit a InformacionContactoEmpleados
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
                  id="informacion-contacto-empleados-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('dundermifflinApp.informacionContactoEmpleados.telefono')}
                id="informacion-contacto-empleados-telefono"
                name="telefono"
                data-cy="telefono"
                type="text"
                validate={{
                  maxLength: { value: 15, message: translate('entity.validation.maxlength', { max: 15 }) },
                }}
              />
              <ValidatedField
                label={translate('dundermifflinApp.informacionContactoEmpleados.tipoFono')}
                id="informacion-contacto-empleados-tipoFono"
                name="tipoFono"
                data-cy="tipoFono"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/informacion-contacto-empleados"
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

export default InformacionContactoEmpleadosUpdate;
