package com;

import com.javapractice.invoiceService.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for:
 * - RideType
 * - InvoiceGenerator
 * - InvoiceSummary
 * - RideRepository
 * - InvoiceService
 *
 * NOTE: Ride class itself is intentionally NOT tested.
 */
public class InvoiceServiceTest {

    private InvoiceGenerator invoiceGenerator;
    private RideRepository rideRepository;
    private InvoiceService invoiceService;

    @Before
    public void setup() {
        invoiceGenerator = new InvoiceGenerator();
        rideRepository = new RideRepository();
        invoiceService = new InvoiceService(rideRepository);
    }

    // =========================================================================
    // RIDE TYPE TESTS
    // =========================================================================

    @Test
    public void givenNormalRideType_shouldReturnCorrectValues() {
        RideType type = RideType.NORMAL;

        Assert.assertEquals(10.0, type.getCostPerKm(), 0.0);
        Assert.assertEquals(1, type.getCostPerMin());
        Assert.assertEquals(5.0, type.getMinFare(), 0.0);
    }

    @Test
    public void givenPremiumRideType_shouldReturnCorrectValues() {
        RideType type = RideType.PREMIUM;

        Assert.assertEquals(15.0, type.getCostPerKm(), 0.0);
        Assert.assertEquals(2, type.getCostPerMin());
        Assert.assertEquals(20.0, type.getMinFare(), 0.0);
    }

    // =========================================================================
    // INVOICE GENERATOR TESTS (FARE CALCULATIONS)
    // =========================================================================

    @Test
    public void givenNormalRide_whenFareCalculated_shouldReturnCorrectFare() {
        double fare = invoiceGenerator.calculateFare(RideType.NORMAL, 2.0, 5);
        // 2*10 + 5*1 = 25
        Assert.assertEquals(25.0, fare, 0.0);
    }

    @Test
    public void givenPremiumRide_whenFareCalculated_shouldReturnCorrectFare() {
        double fare = invoiceGenerator.calculateFare(RideType.PREMIUM, 2.0, 5);
        // 2*15 + 5*2 = 40
        Assert.assertEquals(40.0, fare, 0.0);
    }

    @Test
    public void givenRideBelowNormalMinFare_shouldReturnMinimumFare() {
        double fare = invoiceGenerator.calculateFare(RideType.NORMAL, 0.1, 1);
        Assert.assertEquals(5.0, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_whenSummed_shouldReturnInvoiceSummary() {
        List<Ride> rides = Arrays.asList(
                new Ride(2.0, 5, RideType.NORMAL),   // 25
                new Ride(0.1, 1, RideType.NORMAL)    // 5
        );

        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);

        Assert.assertEquals(new InvoiceSummary(2, 30.0), summary);
    }

    // =========================================================================
    // RIDE REPOSITORY TESTS
    // =========================================================================

    @Test
    public void givenUserId_whenRidesAdded_shouldRetrieveCorrectList() {
        List<Ride> rides = Arrays.asList(
                new Ride(1.0, 2, RideType.NORMAL)
        );

        rideRepository.addRides(1, rides);

        Assert.assertEquals(rides, rideRepository.getRides(1));
    }

    @Test
    public void givenInvalidUserId_shouldReturnNullList() {
        Assert.assertNull(rideRepository.getRides(999));
    }

    // =========================================================================
    // INVOICE SERVICE TESTS
    // =========================================================================

    @Test
    public void givenValidUserId_shouldReturnCorrectInvoiceSummary() {

        List<Ride> rides = Arrays.asList(
                new Ride(2.0, 5, RideType.NORMAL),  // 25
                new Ride(0.1, 1, RideType.NORMAL)   // 5
        );

        rideRepository.addRides(1, rides);

        InvoiceSummary expected = new InvoiceSummary(2, 30.0);
        InvoiceSummary actual = invoiceService.returnInvoice(1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenInvalidUserId_shouldReturnNullInvoice() {
        InvoiceSummary summary = invoiceService.returnInvoice(404);
        Assert.assertNull(summary);
    }

    // =========================================================================
    // INVOICE SUMMARY TESTS
    // =========================================================================

    @Test
    public void givenEqualInvoiceSummaries_shouldBeEqual() {
        InvoiceSummary a = new InvoiceSummary(2, 30.0);
        InvoiceSummary b = new InvoiceSummary(2, 30.0);

        Assert.assertEquals(a, b);
        Assert.assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void givenDifferentInvoiceSummaries_shouldNotBeEqual() {
        InvoiceSummary a = new InvoiceSummary(2, 30.0);
        InvoiceSummary b = new InvoiceSummary(3, 40.0);

        Assert.assertNotEquals(a, b);
    }
}

