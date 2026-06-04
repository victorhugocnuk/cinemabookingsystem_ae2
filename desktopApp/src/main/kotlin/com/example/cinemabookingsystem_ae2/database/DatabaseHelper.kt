package com.example.cinemabookingsystem_ae2.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseHelper {
    private const val DB_URL = "jdbc:sqlite:cinema_booking.db"
    private var connection: Connection? = null

    fun getConnection(): Connection {
        if (connection == null || connection!!.isClosed) {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection(DB_URL)
        }
        return connection!!
    }

    fun initializeDatabase() {
        val conn = getConnection()
        val statement = conn.createStatement()

        try {
            statement.execute("PRAGMA foreign_keys = ON;")

            // 1. Films Table
            statement.execute("""
                CREATE TABLE IF NOT EXISTS films (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    genre TEXT NOT NULL,
                    base_price REAL NOT NULL
                );
            """.trimIndent())

            // 2. Screenings Table
            statement.execute("""
                CREATE TABLE IF NOT EXISTS screenings (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    film_id INTEGER,
                    hall_number INTEGER NOT NULL,
                    screening_date TEXT NOT NULL,
                    start_time TEXT NOT NULL,
                    FOREIGN KEY(film_id) REFERENCES films(id) ON DELETE CASCADE
                );
            """.trimIndent())

            // 3. Seats Table
            statement.execute("""
                CREATE TABLE IF NOT EXISTS seats (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    screening_id INTEGER,
                    seat_number TEXT NOT NULL,
                    is_available INTEGER DEFAULT 1,
                    FOREIGN KEY(screening_id) REFERENCES screenings(id) ON DELETE CASCADE
                );
            """.trimIndent())

            // 4. Initial Data Seeding (Only if empty)
            val rs = statement.executeQuery("SELECT COUNT(*) FROM films;")
            if (rs.next() && rs.getInt(1) == 0) {
                // Insert Sample Film
                statement.execute("INSERT INTO films (title, genre, base_price) VALUES ('Inception', 'Sci-Fi', 10.0);")

                // Insert Sample Screening
                statement.execute("INSERT INTO screenings (film_id, hall_number, screening_date, start_time) VALUES (1, 1, '2026-06-10', '10:00');")

                // Insert Sample Seats
                statement.execute("INSERT INTO seats (screening_id, seat_number, is_available) VALUES (1, 'A1', 1);")

                println("[DATABASE] Seeding complete! Sample data inserted.")
            }

            println("[DATABASE] Initialization verified.")
        } catch (e: SQLException) {
            System.err.println("Error initializing database: ${e.message}")
        } finally {
            statement.close()
        }
    }
}


