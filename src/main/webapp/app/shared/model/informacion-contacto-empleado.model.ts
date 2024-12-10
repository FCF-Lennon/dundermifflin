import { IEmpleado } from 'app/shared/model/empleado.model';

export interface IInformacionContactoEmpleado {
  id?: number;
  telefono?: string;
  empleado?: IEmpleado | null;
}

export const defaultValue: Readonly<IInformacionContactoEmpleado> = {};
