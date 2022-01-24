import { IProfesor } from 'app/entities/profesor/profesor.model';
import { IEstudiante } from 'app/entities/estudiante/estudiante.model';
import { ICurso } from 'app/entities/curso/curso.model';

export interface IAsignatura {
  id?: number;
  nombre?: string;
  id_profesor?: IProfesor | null;
  id_estudiantes?: IEstudiante[] | null;
  id_curso?: ICurso | null;
}

export class Asignatura implements IAsignatura {
  constructor(
    public id?: number,
    public nombre?: string,
    public id_profesor?: IProfesor | null,
    public id_estudiantes?: IEstudiante[] | null,
    public id_curso?: ICurso | null
  ) {}
}

export function getAsignaturaIdentifier(asignatura: IAsignatura): number | undefined {
  return asignatura.id;
}
