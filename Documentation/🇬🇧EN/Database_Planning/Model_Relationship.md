# Relational Model

## Introduction

Using the Entity-Relationship model, we create the Relational Model, which is then normalized if necessary. Finally, it is used as a guide to develop the SQL database.

## Modeling

> **Important**  
> Only the domain of attributes that have been **added** or **modified** compared to the previous model are specified here. Unchanged attributes are omitted to improve readability.  
> Also, newly added fields are highlighted.  
> Underlined fields are potential keys for their relation.

- **Universities** = { <u>Name_U</u>, <u>**Id_U**</u> }
    - Dom(Id_U) = **INT**

- **Faculties** = { <u>Name_F, **Belong_Id_U**</u>, <u>**Id_F**</u> }
    - Dom(Id_F) = **INT**
    - FK = Belong_Id_U → Universities(Id_U)

- **Degrees** = { Name_C, EnrollmentDate, Duration, Plan, IntermediateTitle, <u>**Id_C**</u>, **Offer_Id_F** }
    - Dom(Id_C) = **INT**
    - FK = Offer_Id_F → Faculties(Id_F)

- **Subjects** = { Year, Trimester, Status, ApprovalDate, Grade, RegularizationDate, <u>**Id_S**</u>, <u>Name_S, **Correspond_Id_C**</u>, **Need_Id_S** }
    - Dom(Id_S) = **INT**
    - FK = Correspond_Id_C → Degrees(Id_C)
    - FK = Need_Id_S → Subjects(Id_S)

> **[!Note]**  
> Calculated attributes have been removed from the relational model because they are derived from other attributes and do not need to be stored.

## Normalization

> **[!Tip]**  
> **Boyce-Codd Normal Form (BCNF):** For every functional dependency (x → y) in the database schema, x must be a superkey.

### List of Dependencies

- **Universities** (BCNF ✔️)
    - Name_U → Id_U
    - Id_U → Name_U

- **Faculties** (BCNF ✔️)
    - Id_F → { Name_F, Belong_Id_U }
    - { Name_F, Belong_Id_U } → Id_F

- **Degrees** (BCNF ✔️)
    - Id_C → { Name_C, EnrollmentDate, Duration, Plan, IntermediateTitle, Offer_Id_F }

- **Subjects** (BCNF ✔️)
    - Id_S → { Name_S, Year, Trimester, Status, ApprovalDate, Grade, RegularizationDate, Correspond_Id_C, Need_Id_S }
    - { Name_S, Correspond_Id_C } → { Year, Trimester, Status, ApprovalDate, Grade, RegularizationDate, Id_S, Need_Id_S }

After analyzing each functional dependency in the database schema, we see that it meets BCNF requirements (and therefore 1NF, 2NF, and 3NF). With that, the design phase is complete and we can proceed with creating the SQL model.

## Indexing

**Are additional indexes needed?**

To answer this, we should at least estimate which queries will be most frequent and in what proportion, which will help determine if extra indexes can improve performance.
