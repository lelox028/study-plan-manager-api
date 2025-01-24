# Conceptual Description

## Reality

The idea is to build an application that allows organizing a degree (study) plan, considering all the subjects in it and arranging them according to various filters and views. These views will give the user different helpful perspectives on the current state of their studies.

Initially, there will be no functionality to create users or synchronize data with the cloud, so all configurations and loaded data will be stored locally. The system should be able to distinguish between different universities (or entities) where the student might be enrolled. For each university, only its name needs to be stored. It is also known that university offerings are divided into faculties, for which we want to record the faculty name and the university they belong to.

For each degree (or study plan), the following information should be kept:

- Name of the degree.
- Enrollment date.
- Duration of the degree (in semesters/trimesters).
- Degree plan (link or PDF) **(optional)**.
- List of subjects.
- Overall grade average (GPA).
- Percentage of completion.
- Intermediate degree title **(optional)**.
- Intermediate degree GPA **(optional)**.
- Percentage of completion for the intermediate title **(optional)**.

For the subjects, the following information should be kept:

- Subject name.
- The year it corresponds to.
- The semester/trimester it corresponds to.
- Current status (which could be):
  - Pending and blocked.
  - Pending and available to be taken.
  - In progress.
  - Regularized.
  - Approved (without promotion).
  - Approved (promoted).
- Prerequisite subjects (necessary to approve it).
- Subjects blocked by this subject.
- Date of approval **(if approved)**.
- Date of regularization **(if regularized)**.
- Grade **(if approved)**.

With this information, the system should allow the user to manage multiple study plans, corresponding to different (or the same) universities or educational entities. It should also offer useful insights for each plan, such as a complete list of subjects, a list of subjects available to be taken, subjects by a specific year/semester, percentage of completion for a program/year/semester, the current average for the plan (or per year/semester), and details on blocked subjects (including why they are blocked).
