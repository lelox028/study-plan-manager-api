USE PlanManager;

INSERT INTO
    Universidades (id_U, Nombre_U)
VALUES (1, "UNSL"),
    (2, "Siglo 21");

INSERT INTO
    Facultades (
        id_F,
        Nombre_F,
        Pertenecen_Id_U
    )
VALUES (1, "FCMyN", 1),
    (2, "Default", 2);

INSERT INTO
    Carreras (
        `Nombre_C`,
        `FechaInscripcion`,
        `Duracion`,
        `Plan`,
        `TituloIntermedio`,
        `Dicta_Id_F`
    )
VALUES (
        'Ingeniería en Sistemas',
        '2024-01-15',
        5,
        'https://example.com/plan_sistemas.pdf',
        'Analista de Sistemas',
        1
    );

INSERT INTO
    PlanManager.Materias (
        Nombre_M,
        Anio,
        Cuatrimestre,
        Estado,
        FechaAprobacion,
        Calificacion,
        FechaRegularizacion,
        RequeridaPorTituloIntermedio,
        Corresponden_Id_C
    )
VALUES (
        'Calculo I',
        1,
        '1er Cuatrimestre',
        'Aprobado',
        '2024-03-01',
        7.5,
        '2024-03-01',
        TRUE,
        1
    ),
    (
        'Calculo II',
        1,
        '2do Cuatrimestre',
        'Pendiente',
        NULL,
        NULL,
        NULL,
        TRUE,
        1
    ),
    (
        'Calculo III',
        2,
        'Anual',
        'Pendiente',
        NULL,
        NULL,
        NULL,
        FALSE,
        1
    );

INSERT INTO
    PlanManager.Correlativas (
        Materias_Id_Bloqueada,
        Materias_Id_Correlativa
    )
VALUES (2, 1),
    (3, 2);