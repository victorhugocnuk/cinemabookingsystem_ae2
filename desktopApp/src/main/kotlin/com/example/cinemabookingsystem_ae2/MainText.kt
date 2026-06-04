package com.example.cinemabookingsystem_ae2

/**
 * Integration test entry point.
 * This class is designed to verify the core logic of the BookingService
 * without triggering the graphical user interface (GUI) components,
 * which helps avoid hardware-accelerated rendering errors (e.g., DirectX).
 */
fun main() {
    println("--- SYSTEM INTEGRATION TEST STARTING ---")

    // Phase 1: Dependency Initialization
    // We instantiate the service layer to perform business operations.
    val bookingService = BookingService()

    // Phase 2: Scenario Simulation
    // Defining parameters for a test case: Booking a seat for a specific movie.
    val movieId = "M-101"
    val seatId = "A1"

    println("Attempting to book Seat: $seatId for Movie: $movieId")

    // Executing the service logic
    val isBookingSuccessful = bookingService.processBooking(movieId, seatId)

    // Phase 3: Assertion and Verification
    // Checking if the service correctly handles the request and returns a success status.
    if (isBookingSuccessful) {
        println("VERIFICATION RESULT: PASSED")
        println("Details: Reservation successfully processed for $movieId, Seat: $seatId.")
    } else {
        println("VERIFICATION RESULT: FAILED")
        println("Details: The system failed to process the reservation.")
    }

    println("--- SYSTEM INTEGRATION TEST COMPLETED ---")
}