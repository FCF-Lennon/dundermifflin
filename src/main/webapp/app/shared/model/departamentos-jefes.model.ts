import { IDepartamento } from 'app/shared/model/departamento.model';
import { IJefes } from 'app/shared/model/jefes.model';

export interface IDepartamentosJefes {
  id?: number;
  departamento?: IDepartamento | null;
  jefe?: IJefes | null;
}

export const defaultValue: Readonly<IDepartamentosJefes> = {};
