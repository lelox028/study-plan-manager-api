# Diagrama

![Imagen Diagrama Entidad Relacion](/Documentation/🇪🇸ES/Planificacion_Base_de_Datos/Imagenes/Note%207%20Jul%202024.png)

## Propiedades de las relaciones:
- Pertenecen: total y suryectiva.
- Tienen: total y suryectiva.
- Corresponden: total y suryectiva
- Necesitan: No total y No suryectiva
----------------------------------------------------------------------------------------------
# Definiciones
## Entidades:
- Universidades: (x/x es una Universidad)
    - Dom(Nombre_U) = **Alfanumerico+**
- Facultades: (x/x es una Facultad)
    - Dom(Nombre_F) = **Alfanumerico+**
- Carreras: (x/x es una carrera o plan de estudio)
    - Dom(Nombre_C) = **Alfanumerico+**
    - Dom(FechaInscripcion) = **Alfanumerico+**
    - Dom(Duracion) = **INT**
    - Dom(Plan) = **Alfanumerico+**
    - Dom(CalificacionPromedio) = **FLOAT**
    - Dom(Progreso) = **FLOAT**
    - Dom(TituloIntermedio) = **Alfanumerico\***
    - Dom(CalificacionPromedioIntermedio) = **FLOAT**
    - Dom(ProgresoIntermedio) = **FLOAT**
- Materias: (x/x es una materia)
    - Dom(Nombre_M) = **Alfanumerico+**
    - Dom(Año) = **INT**
    - Dom(Cuatrimestre) = **{1,2,"Anual"}**
    - Dom(Estado) = **{Pendiente y cursable, Pendiente y bloqueada, Cursando, Regular, Aprobado, Aprobado con promocion}**
    - Dom(FechaAprobacion) = **Alfanumerico\***
    - Dom(Calificacion) = **Float**
    - Dom(FechaRegularizacion) = **Alfanumerico\***
## Relaciones:
- Pertenecen: (**(x,y)**/ **x** es Facultad, **y** es Universidad && **x** pertenece a **y**)
- Tienen: (**(x,y)**/ **x** es Facultad, **y** es Carrera && **y** es una carrera o plan ofrecido por **x**).
- Corresponden: (**(x,y)**/ **x** es Materia, **y** es Carrera && **x** es parte de **y**).
- Necesitan: (**(x,y)**/ **x** es Materia, **y** es Materia && **x** Necesita a **y** para ser cursable).
