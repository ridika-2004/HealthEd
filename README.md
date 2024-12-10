# HealthEd

HealthEd is a comprehensive health workshop management system designed to simplify the organization and participation in educational workshops. It provides features for creating, editing, and managing workshops, along with the ability to add educational resources.

## Features

- **User Management:**
  - Register as a participant or organizer.
  - View upcoming and past workshops.
  - Profile management for users.

- **Organizer Features:**
  - Create, edit, and delete workshops.
  - Add educational resources with structured input.

- **Workshop Management:**
  - Save details such as workshop name, date, speaker, and resources.
  - Input resources dynamically with a user-friendly interface.

- **File Operations:**
  - Save and retrieve workshop details from files.
  - Append, edit, and delete records efficiently.

## Technology Stack

- **Programming Language:** Java
- **Utilities:** File handling for persistent data storage
- **User Interface:** Console-based inputs

## Project Structure

source/ ├── User/ │ ├── User.java │ ├── AbsOrganizer.java │ ├── Organizer.java ├── Utility/ │ ├── DateValidator.java │ ├── FileWriterUtility.java │ ├── IFileWriteUtility.java │ ├── ResourceCollector.java

## How to Use


## How to Use

1. Clone this repository:
   ```bash
   git clone https://github.com/ridika-2004/HealthEd.git

2. Navigate to the project folder:
   ```bash
   cd HealthEd

3. Compile the project:
   ```bash
   javac source/**/*.java

4. Run the application:
   ```bash
   java source.Main
   

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or features you'd like to add.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
