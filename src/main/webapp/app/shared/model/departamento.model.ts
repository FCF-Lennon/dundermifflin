import { IEmpleado } from 'app/shared/model/empleado.model';

export interface IDepartamento {
  id?: number;
  nombreDepartamento?: string;
  ubicacionDepartamento?: string;
  empleados?: IEmpleado[] | null;
}

export const defaultValue: Readonly<IDepartamento> = {};
