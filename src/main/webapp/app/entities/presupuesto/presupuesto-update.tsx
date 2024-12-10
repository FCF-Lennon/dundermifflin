import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getDepartamentos } from 'app/entities/departamento/departamento.reducer';
import { createEntity, getEntity, reset, updateEntity } from './presupuesto.reducer';

export const PresupuestoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const departamentos = useAppSelector(state => state.departamento.entities);
  const presupuestoEntity = useAppSelector(state => state.presupuesto.entity);
  const loading = useAppSelector(state => state.presupuesto.loading);
  const updating = useAppSelector(state => state.presupuesto.updating);
  const updateSuccess = useAppSelector(state => state.presupuesto.updateSuccess);

  const handleClose = () => {
    navigate(`/presupuesto${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDepartamentos({}));
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
    if (values.presupuestoDepartamento !== undefined && typeof values.presupuestoDepartamento !== 'number') {
      values.presupuestoDepartamento = Number(values.presupuestoDepartamento);
    }

    const entity = {
      ...presupuestoEntity,
      ...values,
      departamento: departamentos.find(it => it.id.toString() === values.departamento?.toString()),
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
          ...presupuestoEntity,
          departamento: presupuestoEntity?.departamento?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dundermifflinApp.presupuesto.home.createOrEditLabel" data-cy="PresupuestoCreateUpdateHeading">
            <Translate contentKey="dundermifflinApp.presupuesto.home.createOrEditLabel">Create or edit a Presupuesto</Translate>
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
                  id="presupuesto-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('dundermifflinApp.presupuesto.presupuestoDepartamento')}
                id="presupuesto-presupuestoDepartamento"
                name="presupuestoDepartamento"
                data-cy="presupuestoDepartamento"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                id="presupuesto-departamento"
                name="departamento"
                data-cy="departamento"
                label={translate('dundermifflinApp.presupuesto.departamento')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/presupuesto" replace color="info">
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

export default PresupuestoUpdate;
