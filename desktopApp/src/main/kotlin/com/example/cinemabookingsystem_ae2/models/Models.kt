package com.example.cinemabookingsystem_ae2.models

/**
 * Represents a Movie/Film in our system.
 * This class is adapted from our group work in AE1.
 * We added the 'id' field so we can map it to our SQLite database as a Primary Key.
 */
data class Film(
    val id: Int = 0,               // ID managed automatically by SQLite (Primary Key)
    val title: String,             // Title of the movie (needed for Task a search)
    val genre: String,             // Genre of the movie (needed for Task a search)
    val basePrice: Double,         // Base price of a ticket before discounts (Task b)
    var totalTicketsSold: Int = 0  // Counter for the admin report (Task f)
)

/**
 * Represents a scheduled screening of a film.
 * A screening is linked to a specific movie, has an auditorium number, date, and time.
 */
data class Screening(
    val id: Int = 0,               // ID managed by SQLite
    val filmId: Int,               // Foreign Key linking this screening to a specific Film ID
    val hallNumber: Int,           // The cinema hall/room number (Task b)
    val date: String,              // Date of the show (Format: YYYY-MM-DD)
    val startTime: String,         // Start time of the show (Format: HH:MM)
    var totalTakings: Double = 0.0 // Keeps track of total money made in this session (Task d)
)

/**
 * Represents a single seat in the cinema room.
 * Seats are dynamically checked and updated during bookings.
 */
data class Seat(
    val id: Int = 0,               // ID managed by SQLite
    val screeningId: Int,          // ID of the screening this seat belongs to
    val seatNumber: String,        // Seat identifier (e.g., "A1", "B5")
    var isAvailable: Boolean = true // True if free, False if booked (Task c)
)

/**
 * Represents a discount offer controlled by the administrator (Task h).
 */
data class SpecialOffer(
    val offerName: String,          // "Morning_Discount" or "Group_Discount"
    val discountPercentage: Double, // Percentage value (e.g., 20.0 for 20%)
    var isEnabled: Boolean = true   // If the admin turned it on or off via the GUI
)