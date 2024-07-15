# Modelo Relacional

## Introducción
Utilizando el modelo de Entidad-Relación, se construye el Modelo Relacional, que posteriormente se normalizará de ser necesario y finalmente se utilizará como guía para el desarrollo de la base de datos SQL.

## Modelado
> [!Important]  
>Solo se especificará el dominio de aquellos atributos que hayan sido agregados o modificados con respecto al modelo anterior. Aquellos que no hayan sufrido modificaciones se omitirán en pos de mejorar la legibilidad.
Además, se resaltarán aquellos campos que hayan sido agregados.
Los campos subrayados son candidatos a claves de su relacion.

- Universidades = { <u>Nombre_U</u>, <u>**Id_U**</u> }
    - Dom(Id_U) = **INT**

- Facultades = { <u>Nombre_F, **Pertenecen_Id_U**</u>, <u>**Id_F**</u> }
    - Dom(Id_F) = **INT**
    - FK = Pertenecen_Id_U -> Universidades(Id_U)

- Carreras = { Nombre_C, FechaInscripcion, Duracion, Plan, TituloIntermedio, <u>**Id_C**</u>, **Dicta_Id_F** }
    - Dom(Id_C) = **INT**
    - FK = Dicta_Id_F -> Facultades(Id_F)

- Materias = { Año, Cuatrimestre, Estado, FechaAprobacion, Calificacion, FechaRegularizacion, <u>**Id_M**</u>, <u>Nombre_M, **Corresponden_Id_C**</u>, **Necesitan_Id_M** }
    - Dom(Id_M) = **INT**
    - FK = Corresponden_Id_C -> Carreras(Id_C)
    - FK = Necesitan_Id_M -> Materias(Id_M)

> [!Note]
> Los atributos calculados han sido removidos del modelo relacional, dado que al ser obtenidos a partir de otros atributos, no es necesario mantener info sobre ellos

## Normalización

> [!Note]
>**Boyce-Codd Normal Form (BCNF):** Para toda dependencia funcional (x->y) en el esquema de base de datos, x debe ser superclave.

### Listado de Dependencias
- Universidades (BCNF✔️)
    - Nombre_U -> Id_U
    - Id_U -> Nombre_U

- Facultades (BCNF✔️)
    - Id_F -> { Nombre_F, Pertenecen_Id_U }
    - { Nombre_F, Pertenecen_Id_U } -> Id_F

- Carreras (BCNF✔️)
    - Id_C -> { Nombre_C, FechaInscripcion, Duracion, Plan, TituloIntermedio, Dicta_Id_F }

- Materias (BCNF✔️)
    - Id_M -> { Nombre_M, Año, Cuatrimestre, Estado, FechaAprobacion, Calificacion, FechaRegularizacion, Corresponden_Id_C, Necesitan_Id_M }
    - { Nombre_M, Corresponden_Id_C } -> { Año, Cuatrimestre, Estado, FechaAprobacion, Calificacion, FechaRegularizacion, Id_M, Necesitan_Id_M }