# Diagram

![Entity-Relationship Diagram](/Documentation/🇪🇸ES/Planificacion_Base_de_Datos/Imagenes/Note%207%20Jul%202024.png)

## Relationship Properties

- **Belong To:** total and surjective.
- **Offer/Provide:** total and surjective.
- **Correspond:** total and surjective.
- **Need:** not total and not surjective.

---

# Definitions

## Entities

- **Universities** (x/x is a University)
  - Dom(Name_U) = **Alphanumeric+**

- **Faculties** (x/x is a Faculty)
  - Dom(Name_F) = **Alphanumeric+**

- **Degrees** (x/x is a degree or study plan)
  - Dom(DegreeName) = **Alphanumeric+**
  - Dom(EnrollmentDate) = **Alphanumeric+**
  - Dom(Duration) = **INT**
  - Dom(Plan) = **Alphanumeric+**
  - Dom(AverageGrade) = **FLOAT**
  - Dom(Progress) = **FLOAT**
  - Dom(IntermediateTitle) = **Alphanumeric\***
  - Dom(IntermediateAverageGrade) = **FLOAT**
  - Dom(IntermediateProgress) = **FLOAT**

- **Subjects** (x/x is a subject)
  - Dom(SubjectName) = **Alphanumeric+**
  - Dom(Year) = **INT**
  - Dom(Term) = **{1, 2, "Yearly"}**
  - Dom(Status) = **{Pending, In progress, Regular, Approved, Promoted}**
  - Dom(Available) = **{True, False}**
  - Dom(ApprovalDate) = **Alphanumeric\***
  - Dom(Grade) = **FLOAT**
  - Dom(RegularizationDate) = **Alphanumeric\***

## Relationships

- **Belong To:**  
  { (x, y) / x ∈ Faculties, y ∈ Universities ∧ x belongs to y }

- **Offer/Provide:**  
  { (x, y) / x ∈ Faculties, y ∈ Degrees ∧ y is offered by x }

- **Correspond:**  
  { (x, y) / x ∈ Subjects, y ∈ Degrees ∧ x is part of y }

- **Need:**  
  { (x, y) / x ∈ Subjects, y ∈ Subjects ∧ x needs y in order to be available }
