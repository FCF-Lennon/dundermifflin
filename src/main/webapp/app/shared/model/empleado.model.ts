import { IDepartamento } from 'app/shared/model/departamento.model';

export interface IEmpleado {
  id?: number;
  nombreEmpleado?: string;
  apellidoEmpleado?: string;
  correo?: string;
  departamentos?: IDepartamento[] | null;
}

export const defaultValue: Readonly<IEmpleado> = {};
