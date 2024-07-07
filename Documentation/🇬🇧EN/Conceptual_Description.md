## Reality

The idea is to create an application that allows organizing the program of a degree, taking into account all its subjects and organizing them according to various filters. Initially, it will not have the capacity to create users or synchronize data with the cloud; configurations and loaded data will be stored locally.

The system must be able to distinguish between different universities where the student may be enrolled. From them, the following information is desired:
- Name of the university.

It is also desired to save information about the faculties to which the degrees belong:
- Name of the Faculty.
- University to which it belongs.

For each degree, the following information should be stored:
- Name of the degree.
- Enrollment date.
- Duration of the degree (in semesters).
- Degree plan (Link or PDF), *optional*.
- List of subjects.
- Average grade.
- Completion percentage.
- Intermediate title.
- Average grade of the intermediate title.
- Completion percentage of the intermediate title.

Regarding the subjects, the following information is required:
- Name of the subject.
- Year it corresponds to.
- Semester it corresponds to.
- Current status:
    - Pending [Blocked, Enrollable].
    - In progress.
    - Regular.
    - Passed [Regular, Promotion].
- Required subjects (prerequisites).
- Subjects blocked by this subject.
- Approval date.
- Grade.
