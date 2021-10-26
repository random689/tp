---
layout: page
title: User Guide
---

{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction

NewAddressBook is a **desktop app built for secondary school teachers** handling large classes 
to help **manage their contacts of their students and colleagues** efficiently. NewAddressBook also supports other
features such as keeping track of upcoming meetings, recording the medical histories of your students.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `NewAddressBook.jar` from [here](https://github.com/AY2122S1-CS2103-T16-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for NewAddressBook.

(Comment: msising 3rd student entry could be a potential nitpick)

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how NewAddressBook already contains some sample data.<br>
![Ui](images/Ui.png)

5. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`listStudent`** : Lists all contacts.

    * **`student n/John Doe p/98765432 e/johnd@example.com g/M a/311, 
         Clementi Ave 2, #02-25 f/3E1 em/999 i/Math class t/naughty`** : Adds a student named "John Doe" to NewAddressBook.

    * **`deleteStudent 3`** : Deletes the 3rd student shown in the student list.

    * **`clearStudent`** : Deletes all displayed students.

    * **`exit`** : Exits the app.

6. Refer to the [Overview](#overview) section for a summary of commands available and the  [Features](#features) section for details of each command.

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** If you face any issues launching NewAddressBook, go to the `data` folder, delete `newaddressbook.json`
and try launching again.
</div>

--------------------------------------------------------------------------------------------------------------------

## Overview

### User Interface (UI)
The following image shows the different parts of NewAddressBook's main application window:

* The **Command Box** is where you type in commands for execution.
* The **Result Box** displays the result of the commands executed. 
* The **Student List** displays the list of students in NewAddressBook.
* The **Teacher List** displays the list of teachers in NewAddressBook.
* The **Data Source** displays information about where the data for NewAddressBook is stored.

#### Individual Student Display
The image below shows the various aspects that describes an individual student in NewAddressBook:

#### Individual Teacher Display
The image below shows the various aspects that describes an individual teacher in NewAddressBook:

#### Meeting Window
In NewAddressBook, meetings are not displayed in the main window. Instead, you will need to open up the Meetings window. 
You can do so by entering the `showMeeting` command  or by clicking on the `Show Meetings` button in the menu bar.
The following image shows the Meetings Window:


### Functionalities Summary

<div markdown="span" class="alert alert-primary">
:information_source: Some commands work only in the <b>Main</b> application window, while others work only in the <b>Meetings</b> window. 
The only command that works on <b>both</b> windows is the `undo` command. The Window column of the table below indicates which window
the command is compatible with.
</div>

Action | Format | Window
--------|---------|----------
**Add student** | `student n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS f/FORM_CLASS g/GENDER i/INVOLVEMENT em/EMERGENCY_NUMBER [t/TAG]…​` | Main
**Clear all students** | `clearStudent` | Main
**Delete a student** | `deleteStudent INDEX` | Main
**Edit a student** | `editStudent INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [f/FORM_CLASS] [g/GENDER] [i/INVOLVEMENT] [em/EMERGENCY_NUMBER] [t/TAG]…​` | Main
**Find a student by name** | `findStudent KEYWORD [MORE_KEYWORDS]` | Main
**Filter a student** | | Main
**Record a student's medical history** | | Main
**List all students** |`listStudents` | Main
**Copy a field from students** | `copyStudent c/FIELD` | Main
**Add teacher** | `teacher n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER o/OFFICE_TABLE_NUMBER i/INVOLVEMENT [t/TAG]…​` | Main
**Clear all teachers** | `clearTeacher` | Main
**Delete a teacher** | `deleteTeacher INDEX` | Main
**Edit a teacher** | `editTeacher INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [o/OFFICE_TABLE_NUMBER] [i/INVOLVEMENT] [t/TAG]…​` | Main
**Find a teacher by name** | `findTeacher KEYWORD [MORE_KEYWORDS]` | Main
**Filter a teacher** | | Main
**List all teachers** | `listTeachers` | Main
**Copy a  field from teachers** | `copy c/FIELD` | Main
**Help** | `help` | Main
**Exit NewAddressBook** | `exit` | Main
**Open meetings window** | `showMeetings` | Main
**Add meeting** | `meet r/TITLE d/DATE_TIME v/VENUE w/ATTENDEE_TYPE` | Meetings
**Remove meetings** | | Meetings
**Close meetings window** | | Meetings
**Undo**| `undo` | Both

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addStudent` command, `NAME` under `n/NAME` represents the student's name that you should provide. 
  For instance, if the student's name is "John Doe", then you should input `n/John Doe` in the `addStudent` command.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. for the `help` command, if you specify `help 123`, it will be interpreted as `help`.

</div>

### Managing Student Contacts

#### Add a student: `student`

Adds a student to NewAddressBook.

Format: `student n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS
f/FORM_CLASS g/GENDER i/INVOLVEMENT em/EMERGENCY_NUMBER [t/TAG]…​`

Parameters:
* `NAME` The name of the student
  * should not be blank
  * should only contain alphanumeric characters and spaces

* `PHONE_NUMBER` The phone number of the student
  * should only contain numbers, and it should be at least 3 digits long 

* `EMAIL` The email of the student
  * should not be blank 
  * should be of the format `local-part@domain`

* `ADDRESS` The address of the student
  * should not be blank
 
* `GENDER` The gender of the student
  * can only be one of  the following: `M` (Male), `F` (Female) or `N` (Non-binary)

* `TAG` A tag associated with the student
  * should only contain alphanumeric characters
  

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

Examples:
- `student n/James p/94629424 e/j77@example.com a/George street, block 123, #01-01 f/3A2 g/M i/Math class em/92696977`
- `student n/Betsy Crowe p/83958294 e/bc33@example.com a/Adams road, block 8, #03-05 f/3C1 g/F i/Dance society em/96122134 t/President t/exco`

#### Edit a student: `editStudent`

Edits an existing student in NewAddressBook.

Format: `editStudent [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]
[f/FORM_CLASS] [g/GENDER] [i/INVOLVEMENT] [em/EMERGENCY_NUMBER] [t/TAG]…​`


* Edits the student at the specified `INDEX`. 
* `INDEX` refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed student list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can remove all the student’s tags by typing `t/` without specifying any tags after it.
</div>

Examples:
*  `editStudent 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `editStudent 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

#### Delete a student : `deleteStudent`

Deletes the specified student from NewAddressBook.

Format: `deleteStudent INDEX`

* Deletes the student at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed student list.

Examples:
* `listStudents` followed by `deleteStudent 2` deletes the 2nd student in the address book.
* `findStudent Betsy` followed by `deleteStudent 1` deletes the 1st student in the results of the `findStudent` command.

#### Clear all student contacts : `clearStudent`

Clears all **currently displayed** students from the address book. If the currently displayed list is empty, the application warns the user that the list is empty and nothing is cleared.

Format: `clearStudent`

#### Locate students by name: `findStudent`

Finds students whose names contain any of the given keywords.

Format: `findStudent KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findStudent John` returns `john` and `John Doe`
* `findStudent alex Yu` returns `Alex Yeoh`, `Bernice Yu`<br>

#### Filter students: `filterStudent`.

One can filter students by:
- tag
- involvement

Format:
- `filterStudent [INVOLVEMENT] [t/TAG]…​`

Filters by involvement first, to filter by tag, add `t/`, followed by tag terms behind

The filter category is not case-sensitive e.g. “student” same as “STUDENT” but "t/" is not the same as "T/"

Filters for involvement/tags containing the user input e.g. searching "nuts" will also contain results with "donuts".

(Comment: this is unclear! This is supposed to be t/tag1 tag2 or t/tag1 t/tag2)

More than 1 filter is allowed e.g.  `INVOLVEMENT_FILTER_CATERGORY [t/] [TAG_FILTER CATERGORY] [TAG_FILTER CATERGORY]`.

Example:
- `filterStudent class t/rep` - will return all students with the involvement containing “class” and tag containing
  “rep”.
- `filterStudent class` - will return all students with the involvement containing “class”.
- `filterStudent t/naughty special` - will return all students with the tag containing “naughty” and "special".

#### List all students : `listStudents`

Shows a list of all students stored in the address book.

Format: `listStudents`

#### Copying fields from students: `copyStudent`.
Copy specified data from students in the last shown student's list to the user's clipboard. The fields that can be copied are:

- email
- phone numbers
- name

Format: `copyStudent [c/FIELD_TO_COPY]`

`FIELD_TO_COPY` can only be one of three strings: `phone`, `email` or `name`.

Example:
* `listStudents` followed by `copyStudent c/email` copies the emails of all students to the user's clipboard.
* `findStudent Betsy` followed by `copyStudent c/phone` copies the phones of students whose name matches Betsy. The definition of "matches" is as per the definition in the `findStudent` command.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the last shown student list to the user, nothing will be copied to the clipboard.
</div>

### Managing medical history of students

#### Modify medical history of a student: `medical`
Format: `medical INDEX [m/MEDICAL_HISTORY]`

* Adds the medical history to the student at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed student list.

How this command works:
1. Adds a medical history to an existing student in NewAddressBook if the student does not have any existing medical history.
2. Overwrites the medical history of a student in NewAddressBook if the student already has an existing medical history.
3. Removes the medical history of a student in NewAddressBook if the student already has an existing medical history
   and an empty `MEDICAL_HISTORY` is given.

Examples:
* `medical 1 m/ADHD` 

#### Viewing the full medical history of a student: `showMedical`

Displays a pop-up window for the user to view the full medical history of the student in NewAddressBook.

Format: `showMedical INDEX`

* View the full medical history of the student at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed student list.

Examples:
* `showMedical 1`

### Managing Teacher Contacts

#### Add a teacher: `teacher`

Adds a teacher to NewAddressBook.

Format: `teacher n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER o/OFFICE_TABLE_NUMBER 
i/INVOLVEMENT [t/TAG]…​`

Parameters:
* `NAME` The name of the teacher
    * should not be blank
    * should only contain alphanumeric characters and spaces

* `PHONE_NUMBER` The phone number of the teacher
    * should only contain numbers, and it should be at least 3 digits long

* `EMAIL` The email of the teacher
    * should not be blank
    * should be of the format `local-part@domain`

* `GENDER` The gender of the teacher
    * can only be one of  the following: `M` (Male), `F` (Female) or `N` (Non-binary)

* `OFFICE_TABLE_NUMBER` The teacher's table number in the school office
  * should only contain numbers, and it should have at least 1 digit

* `TAG` A tag associated with the teacher
    * should only contain alphanumeric characters

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A teacher can have any number of tags (including 0)
</div>

Examples:
- `teacher n/Messi p/94629424 e/j77@example.com o/12 g/M i/Math Department`
- `teacher n/Eden p/83958294 e/bc33@example.com g/N i/Class 3D Co-form t/buddy t/colleague`

#### Edit a teacher: `editTeacher`

Edits an existing teacher in the address book.

Format: `editTeacher [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [o/OFFICE_TABLE_NUMBER] [i/INVOLVEMENT] [t/TAG]…​`


* Edits the teacher at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed teacher list. The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed student list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the teacher will be removed i.e adding of tags is not cumulative.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can remove all the teacher’s tags by typing `t/` without specifying any tags after it.
</div>

Examples:
*  `editTeacher 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st teacher to be `91234567` and `johndoe@example.com` respectively.
*  `editTeacher 2 n/Betsy Crower t/` Edits the name of the 2nd teacher to be `Betsy Crower` and clears all existing tags.

#### Delete a teacher : `deleteTeacher`

Deletes the specified teacher from NewAddressBook.

Format: `deleteTeacher INDEX`

* Deletes the teacher at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed teacher list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed student list.

Examples:
* `listTeacher` followed by `deleteTeacher 2` deletes the 2nd teacher in the address book.
* `findTeacher Betsy` followed by `deleteTeacher 1` deletes the 1st teacher in the results of the `findTeacher` command.

#### Clear all teacher contacts : `clearTeacher`

Clears all teachers from NewAddressBook.

Format: `clearTeacher`

#### Locate teachers by name: `findTeacher`

Finds teachers whose names contain any of the given keywords.

Format: `findTeacher KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Teachers matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findTeacher John` returns `john` and `John Doe`
* `findTeacher alex Yu` returns `Alex Yeoh`, `Bernice Yu`<br>
  ![result for 'find alex yu'](images/findAlexDavidResult.png)

#### Filter teachers: `filterTeacher`
Filters teachers by:
- tag
- involvement

Format:
- `filterTeacher [INVOLVEMENT] [t/][TAG]…​`

Filters by involvement first, to filter by tag, add `t/`, followed by tag terms behind

The filter category is not case-sensitive e.g. “teacher” same as “TEACHER” but "t/" is not the same as "T/"

Filters for involvement/tags containing the user input e.g. searching "nuts" will also contain results with "donuts".

More than 1 filter is allowed e.g.  `INVOLVEMENT_FILTER_CATERGORY [t/] [TAG_FILTER CATERGORY] [TAG_FILTER CATERGORY]`.

Example:
- `filterTeacher Math Dept t/rep` - will return all teachers with the involvement containing “Math Dept” and tag containing
  “rep”.
- `filterTeacher Math Dept` - will return all teachers with the involvement containing “Math Dept”.
- `filterTeacher t/colleague admin` - will return all teachers with the tag containing “colleague” and "admin".

#### List all teachers : `listTeachers`

Shows a list of all teachers stored in NewAddressBook.

Format: `listTeachers`

#### Copying fields from teachers `copyTeacher`
Copy specified data from all teachers shown in the teachers list to the clipboard. The fields that can be copied are:

- email
- phone numbers
- Name

Format: `copyTeacher c/FIELD`

Example:
* `listTeachers` followed by `copyTeacher c/email` copies the emails of all teachers in NewAddressBook to the user's clipboard.
* `findTeacher Betsy` followed by `copyTeacher c/phone` copies the emails of teachers in the results of the `findTeacher` command.

### Managing Meetings
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
NewAddressBook automatically removes meetings that have expired whenever you load the app. 
So you don't have to worry about deleting them!
</div>

#### Add meeting: `meet`
Adds a meeting to NewAddressBook.

Format: `meet r/TITLE d/DATE_TIME v/VENUE w/ATTENDEE_TYPE`

Parameters:
* `TITLE` A brief summary of the meeting. 
* `DATE_TIME` A valid date and time of the meeting. 
  * should be of the format `YYYY-MM-DD HH:mm`
* `VENUE`: The venue of the meeting.
* `ATTENDEE_TYPE`: The type of person(s) you are meeting with.
  * can only be one of the following: `S` (students), `T` (teachers), `P` (parents)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You cannot add meetings in the past.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
NewAddressBook will prevent you from adding a meeting if the new meeting clashes (exact same date and time) 
with an existing meeting.
</div>

Example:
* `meet r/Meeting with Ms.Lee d/2040-07-12 14:30 v/Seminar room 3 w/P`

### General

#### undo the latest change: `undo`

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

### NewAddressBook Data File

#### Saving the data

NewAddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

NewAddressBook data are saved as a JSON file `[JAR file location]/data/newaddressbook.json`. Advanced users are welcome to update data directly by editing the data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, NewAddressBook will discard all data and start with an 
empty data file at the next run. In the event that the modification you make causes NewAddressBook to stop functioning 
properly, please manually remove the data file and launch the app again.
</div>



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NewAddressBook home folder.

--------------------------------------------------------------------------------------------------------------------
