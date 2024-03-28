# Employee Analysis Program

This program is designed to analyze the organizational structure of a company and identify potential improvements. It focuses on two main aspects:

1. **Salary Analysis**: Ensuring that every manager earns within a certain range compared to the average salary of their direct subordinates.
2. **Reporting Line Length**: Identifying employees with too long reporting lines, i.e., those who have more than four managers between them and the CEO.

## How It Works

The program reads employee data from a CSV file.

After reading the data, the program performs the following analyses:

1. **Salary Analysis**:
    - For each manager, it calculates the average salary of their direct subordinates.
    - It checks if the manager's salary falls within a certain range relative to the average salary of their subordinates:
    - The manager's salary should be at least 20% more than the average salary but not more than 50% more.
    - It prints out any managers who earn less or more than they should.

2. **Reporting Line Length**:
    - It calculates the number of managers in the reporting line for each employee.
    - It identifies employees with more than four managers between them and the CEO.
    - It prints out any employees with too long reporting lines.

## How to Use

- Prepare a CSV file with employee data following the specified format.
- Run the program using the command: `java -jar analyze-employees.jar [filename.csv]`. If no filename is provided, the program will use the default file `data.csv`.
- View the analysis results printed in the console.


