package database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * PHASE 1: Connection Setup
 * I am using the Singleton pattern here. This ensures that my application
 * maintains only one active connection to the SQLite database, which is
 * more efficient and avoids file-locking issues.
 */
object DatabaseHelper {
    // I am defining the path to my local database file
    private const val DB_URL = "jdbc:sqlite:cinema_booking.db"
    private var connection: Connection? = null

    /**
     * This method initializes the connection.
     * I used a try-catch block to ensure that if the database file is missing,
     * the program won't just crash without explanation.
     */
    fun getConnection(): Connection {
        try {
            if (connection == null || connection!!.isClosed) {
                Class.forName("org.sqlite.JDBC")
                connection = DriverManager.getConnection(DB_URL)
            }
        } catch (e: Exception) {
            System.err.println("I encountered an error connecting to the DB: ${e.message}")
            throw RuntimeException(e)
        }
        return connection!!
    }
}

/**
 * This is my isolated test.
 * Before building the complex UI, I run this to confirm that the
 * JDBC driver is working and the database file is correctly created.
 */
fun main() {
    println("Testing my database connection...")
    val conn = DatabaseHelper.getConnection()
    if (conn != null) {
        println("Success! The connection is established and the file is created.")
    }
}
```eof

### My Plan for the Report (The "Student's Voice")

For my report, I will document this phase like this:

1.  **Objective:** To establish a persistent data layer for the Cinema Booking System.
2.  **Implementation:** I implemented a `DatabaseHelper` class using the Singleton pattern. This centralizes the database logic.
3.  **Validation:** I created a `main()` function to perform a "smoke test." This confirms the SQLite connection is functional before attempting to execute complex SQL queries.

**My next move:**
1. I'll paste this into `database/DatabaseHelper.kt`.
2. I'll run the `main()` function to see the "Success!" message.
3. I'll take my **Screenshot #1** of this specific output.

Does this first phase look good to you? Once you confirm you've got this running, let me know, and we will move to **Phase 2**, where I'll add the logic to create the tables!