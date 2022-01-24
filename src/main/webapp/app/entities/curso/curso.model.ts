import { IColegio } from 'app/entities/colegio/colegio.model';

export interface ICurso {
  id?: number;
  salon?: string;
  grado?: number;
  id_colegio?: IColegio | null;
}

export class Curso implements ICurso {
  constructor(public id?: number, public salon?: string, public grado?: number, public id_colegio?: IColegio | null) {}
}

export function getCursoIdentifier(curso: ICurso): number | undefined {
  return curso.id;
}
