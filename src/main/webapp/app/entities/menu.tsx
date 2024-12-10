import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate } from 'react-jhipster';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/departamento">
        <Translate contentKey="global.menu.entities.departamento" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/departamentos-jefes">
        <Translate contentKey="global.menu.entities.departamentosJefes" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/empleado">
        <Translate contentKey="global.menu.entities.empleado" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/informacion-contacto-empleado">
        <Translate contentKey="global.menu.entities.informacionContactoEmpleado" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/informacion-contacto-jefe">
        <Translate contentKey="global.menu.entities.informacionContactoJefe" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/jefes">
        <Translate contentKey="global.menu.entities.jefes" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/presupuesto">
        <Translate contentKey="global.menu.entities.presupuesto" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
