import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getJefes } from 'app/entities/jefes/jefes.reducer';
import { createEntity, getEntity, reset, updateEntity } from './informacion-contacto-jefe.reducer';

export const InformacionContactoJefeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const jefes = useAppSelector(state => state.jefes.entities);
  const informacionContactoJefeEntity = useAppSelector(state => state.informacionContactoJefe.entity);
  const loading = useAppSelector(state => state.informacionContactoJefe.loading);
  const updating = useAppSelector(state => state.informacionContactoJefe.updating);
  const updateSuccess = useAppSelector(state => state.informacionContactoJefe.updateSuccess);

  const handleClose = () => {
    navigate(`/informacion-contacto-jefe${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

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
      ...informacionContactoJefeEntity,
      ...values,
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
          ...informacionContactoJefeEntity,
          jefe: informacionContactoJefeEntity?.jefe?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dundermifflinApp.informacionContactoJefe.home.createOrEditLabel" data-cy="InformacionContactoJefeCreateUpdateHeading">
            <Translate contentKey="dundermifflinApp.informacionContactoJefe.home.createOrEditLabel">
              Create or edit a InformacionContactoJefe
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
                  id="informacion-contacto-jefe-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('dundermifflinApp.informacionContactoJefe.telefono')}
                id="informacion-contacto-jefe-telefono"
                name="telefono"
                data-cy="telefono"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('dundermifflinApp.informacionContactoJefe.tipoFono')}
                id="informacion-contacto-jefe-tipoFono"
                name="tipoFono"
                data-cy="tipoFono"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="informacion-contacto-jefe-jefe"
                name="jefe"
                data-cy="jefe"
                label={translate('dundermifflinApp.informacionContactoJefe.jefe')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/informacion-contacto-jefe" replace color="info">
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

export default InformacionContactoJefeUpdate;
