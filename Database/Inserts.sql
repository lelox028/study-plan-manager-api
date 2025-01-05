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


-- datos de prueba para mi carrera actual:
INSERT INTO `PlanManager`.`Carreras` 
(`Nombre_C`, `FechaInscripcion`, `Duracion`, `Plan`, `TituloIntermedio`, `Dicta_Id_F`) 
VALUES 
('Licenciatura en Informática', '2024-08-01', 5, NULL, 'Analista Universitario', 2);

-- Materias del primer cuatrimestre
INSERT INTO `PlanManager`.`Materias` 
(`Nombre_M`, `Anio`, `Cuatrimestre`, `Estado`, `RequeridaPorTituloIntermedio`, `Corresponden_Id_C`) 
VALUES 
('Álgebra y Geometría', 1, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Análisis Matemático', 1, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Programación Lógica', 1, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Sistemas de Información', 1, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Idioma Extranjero I', 1, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Lógica Simbólica', 1, '1er Cuatrimestre', 'Pendiente', b'1', 2);

-- Materias del segundo cuatrimestre
INSERT INTO `PlanManager`.`Materias` 
(`Nombre_M`, `Anio`, `Cuatrimestre`, `Estado`, `RequeridaPorTituloIntermedio`, `Corresponden_Id_C`) 
VALUES 
('Arquitectura del Computador', 1, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Cálculo Avanzado', 1, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Matemática Discreta', 1, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Programación Orientada a Objetos', 1, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Idioma Extranjero II', 1, '2do Cuatrimestre', 'Pendiente', b'1', 2);

-- Materias del tercer cuatrimestre
INSERT INTO `PlanManager`.`Materias` 
(`Nombre_M`, `Anio`, `Cuatrimestre`, `Estado`, `RequeridaPorTituloIntermedio`, `Corresponden_Id_C`) 
VALUES 
('Estadística y Probabilidad', 2, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Algoritmos y Estructura de Datos I', 2, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Grupo y Liderazgo', 2, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Idioma Extranjero III', 2, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Lenguajes Formales y Computabilidad', 2, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Práctica Solidaria', 2, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Taller de Algoritmos y Estructura de Datos I', 2, '1er Cuatrimestre', 'Pendiente', b'1', 2);

-- Materias del cuarto cuatrimestre
INSERT INTO `PlanManager`.`Materias` 
(`Nombre_M`, `Anio`, `Cuatrimestre`, `Estado`, `RequeridaPorTituloIntermedio`, `Corresponden_Id_C`) 
VALUES 
('Base de Datos I', 2, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Administración', 2, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Algoritmos y Estructura de Datos II', 2, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Idioma Extranjero IV', 2, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Taller de Algoritmos y Estructuras de Datos II', 2, '2do Cuatrimestre', 'Pendiente', b'1', 2);

-- Materias del quinto cuatrimestre
INSERT INTO `PlanManager`.`Materias` 
(`Nombre_M`, `Anio`, `Cuatrimestre`, `Estado`, `RequeridaPorTituloIntermedio`, `Corresponden_Id_C`) 
VALUES 
('Análisis y Diseño de Software', 3, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Idioma Extranjero V', 3, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Principios de Economía', 3, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Pruebas de Sistemas', 3, '1er Cuatrimestre', 'Pendiente', b'1', 2),
('Sistemas Operativos', 3, '1er Cuatrimestre', 'Pendiente', b'1', 2);

-- Materias del sexto cuatrimestre
INSERT INTO `PlanManager`.`Materias` 
(`Nombre_M`, `Anio`, `Cuatrimestre`, `Estado`, `RequeridaPorTituloIntermedio`, `Corresponden_Id_C`) 
VALUES 
('Ingeniería de Software', 3, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Algoritmos Concurrentes y Paralelos', 3, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Comunicaciones', 3, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Desarrollo Emprendedor', 3, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Idioma Extranjero VI', 3, '2do Cuatrimestre', 'Pendiente', b'1', 2),
('Seminario de Práctica de Informática', 3, '2do Cuatrimestre', 'Pendiente', b'1', 2);
