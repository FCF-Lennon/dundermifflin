import { IDepartamento } from 'app/shared/model/departamento.model';

export interface IPresupuesto {
  id?: number;
  presupuestoDepartamento?: number;
  departamento?: IDepartamento | null;
}

export const defaultValue: Readonly<IPresupuesto> = {};
