## Project File Responsibilities

| File Name | Purpose / Description |
|----------|------------------------|
| **DBConnection.java** | Connects the project to the MySQL database using JDBC. Contains the `getConnection()` method used by all servlets. |
| **register.jsp** | Registration page where the user enters name, email, and password. Sends the form data to `RegisterServlet`. |
| **RegisterServlet.java** | Handles registration logic. Inserts new user details into the `users` table. Redirects to `login.jsp` after successful registration. |
| **login.jsp** | Login page where user enters email and password. Sends data to `LoginServlet`. |
| **LoginServlet.java** | Validates login credentials. If valid, creates a session and stores user details (`userId`, `userName`). Redirects to `tasks.jsp`. |
| **tasks.jsp** | Dashboard page displayed after login. Shows the logged-in user’s tasks. Provides options to Add, Edit, and Delete tasks. |
| **LogoutServlet.java** | Logs the user out by invalidating the session. Redirects back to `login.jsp`. |
| **addTask.jsp** | Form page to create a new task. Sends task data to `AddTaskServlet`. |
| **AddTaskServlet.java** | Adds a new task to the `todos` table for the logged-in user. Redirects back to `tasks.jsp` after saving. |
| **edittask.jsp** | Loads a task using its ID and displays its current values inside an editable form. |
| **EditTaskServlet.java** | Updates the task details (title, description, status) in the database. Redirects back to `tasks.jsp`. |
| **DeleteTaskServlet.java** | Deletes a task from the database using its ID. Redirects back to `tasks.jsp` after deletion. |



---

### Application Flow 

           ┌────────────────┐
           │ register.jsp   │
           └──────┬─────────┘
                  │ (form submit)
                  ▼
         ┌─────────────────────┐
         │ RegisterServlet      │
         │ Inserts into users   │
         └─────────┬───────────┘
                   │ redirect
                   ▼
           ┌────────────────┐
           │ login.jsp      │
           └──────┬─────────┘
                  │ (login submit)
                  ▼
         ┌─────────────────────┐
         │ LoginServlet        │
         │ Validates user      │
         └───── ─┬─────────────┘
          valid  │     │ invalid
                 │     ▼
                 │   login.jsp (error)
                 ▼
        ┌───────────────────────┐
        │ tasks.jsp (Dashboard) │
        └─────┬─────┬─────┬─────┘
              │     │     │
              │     │     │
     Add Task │ Edit Task │ Delete Task
              │     │     │
              ▼     ▼     ▼
     addTask.jsp   edittask.jsp   DeleteTaskServlet
              │     │                │
              ▼     ▼                ▼
  AddTaskServlet   EditTaskServlet
              │     │                │
              └─────┴─────┬──────────┘
                          ▼
                       tasks.jsp

              Logout → LogoutServlet → login.jsp

## Application Flow

1. **User Registration**
   - User opens `register.jsp`
   - Fills Name, Email, Password
   - Form is submitted to **RegisterServlet**
   - Data is inserted into `users` table
   - On success → User is redirected to `login.jsp`

2. **User Login**
   - User visits `login.jsp`
   - Enters Email & Password
   - Form is submitted to **LoginServlet**
   - Credentials are validated using `users` table
   - If login is correct:
     - A user session is created (`userId`, `userName`)
     - User is redirected to `tasks.jsp`
   - If login fails:
     - Error message is displayed

3. **Dashboard (Task List)**
   - `tasks.jsp` loads tasks for the logged-in user using `userId`
   - Displays all tasks from the `todos` table
   - User can:
     - Add a Task
     - Edit a Task
     - Delete a Task
     - Logout

4. **Add New Task**
   - User clicks **Add Task** → `addTask.jsp`
   - Fills Task Title and Description
   - Form sends data to **AddTaskServlet**
   - New task is inserted into `todos` table with default status **Pending**
   - Returns to `tasks.jsp`

5. **Edit Task**
   - User clicks **Edit** → `edittask.jsp`
   - Task details are loaded using task `id`
   - User modifies title, description, or status
   - Form sends data to **EditTaskServlet**
   - Task record is updated in `todos` table
   - Returns to `tasks.jsp`

6. **Delete Task**
   - User clicks **Delete** → calls **DeleteTaskServlet** with task `id`
   - Task is removed from `todos` table
   - Page reloads `tasks.jsp`

7. **Logout**
   - User clicks **Logout**
   - **LogoutServlet** ends the session
   - User is redirected back to `login.jsp`
