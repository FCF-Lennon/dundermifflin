import departamento from 'app/entities/departamento/departamento.reducer';
import departamentosJefes from 'app/entities/departamentos-jefes/departamentos-jefes.reducer';
import empleado from 'app/entities/empleado/empleado.reducer';
import informacionContactoEmpleado from 'app/entities/informacion-contacto-empleado/informacion-contacto-empleado.reducer';
import informacionContactoJefe from 'app/entities/informacion-contacto-jefe/informacion-contacto-jefe.reducer';
import jefes from 'app/entities/jefes/jefes.reducer';
import presupuesto from 'app/entities/presupuesto/presupuesto.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  departamento,
  departamentosJefes,
  empleado,
  informacionContactoEmpleado,
  informacionContactoJefe,
  jefes,
  presupuesto,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
