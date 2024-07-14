# Modelo Relacional

## Introduccion
Utilizando el modelo de Entidad-Relacion se construye el Modelo Relacional, que posteriormente se normalizara de ser necesario y finalmente se utilizara como guia para el desarrollo de la base de datos SQL.

## Modelado
**nota: Solo se especificara el dominio de aquellos atributos que hayan sido agregados o modificados con respecto al modelo anterior, aquellos que no hayan sufrido modificaciones se omitiran en pos de mejorar la legibilidad.
Ademas, se resaltaran aquellos campos que hayan sido agregados.*

- Universidades = { <u>Nombre_U</u>, <u>**Id_U**</u> }
    - Dom(Id_U) = **INT**
- Facultades = { Nombre_F, <u>**Id_F**</u>, **Pertenecen_Id_U** }
    - Dom(Id_F) = **INT**
    - FK = Pertenecen_Id_U -> Universidades(Id_U)
- Carreras = { Nombre_C, FechaInscripcion, Duracion, Plan, CalifiacionPromedio, Progreso, TituloIntermedio, CalificacionPromedioIntermedio, ProgresoIntermedio <u>**Id_C**</u>, **Dicta_Id_F** }
    - Dom(Id_C) = **INT**
    - FK = Dicta_Id_F -> Facultades(Id_F)
- Materias = { Nombre_M, Año, Cuatrimestre, Estado, Cursable, FechaAprobacion, Calificacion, FechaRegularizacion, <u>**Id_M**</u>, **Corresponden_Id_C**, **Necesitan_Id_M** }
    - Dom(Id_M) = **INT**
    - FK = Corresponden_Id_C -> Carreras(Id_C)
    - FK = Necesitan_Id_M -> Materias(Id_M)