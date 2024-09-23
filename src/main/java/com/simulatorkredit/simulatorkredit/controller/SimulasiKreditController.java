package com.simulatorkredit.simulatorkredit.controller;

import com.simulatorkredit.simulatorkredit.model.SimulasiKreditRequest;
import com.simulatorkredit.simulatorkredit.model.SimulasiKreditResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulasi")
public class SimulasiKreditController {

    @PostMapping("/totalpinjaman")
    public SimulasiKreditResponse hitungSimulasiKredit(@RequestBody SimulasiKreditRequest request) {
        double totalPinjaman = request.getTotalPinjaman();
        double sukuBungaBulan = request.getSukuBunga();
        int jangkaWaktu = request.getJangkaWaktuKredit();

        // Menghitung suku bunga per tahun menggunakan metode rate
        Double sukuBungaPerTahun = rate(jangkaWaktu,
                -((totalPinjaman / jangkaWaktu) + totalPinjaman * sukuBungaBulan / 100),
                totalPinjaman,
                0,
                0,
                0.1); // Menambahkan parameter guess, jika diperlukan

        if (sukuBungaPerTahun == null) {
            throw new IllegalArgumentException("Suku bunga tidak dapat dihitung.");
        }

        sukuBungaPerTahun *= 12 * 100;

        // Menghitung angsuran per bulan
        double angsuranPerBulan = (totalPinjaman * (sukuBungaPerTahun / 100 / 12)) /
                (1 - 1 / Math.pow(1 + sukuBungaPerTahun / 100 / 12, jangkaWaktu));

        // Menghitung manfaat pensiun
        double totalManfaatPensiun = angsuranPerBulan * 1.111;

        // Format response
        SimulasiKreditResponse response = new SimulasiKreditResponse();
        response.setTotalPinjaman(String.format("%,d", (long) totalPinjaman));
        response.setJangkaWaktuKredit(jangkaWaktu);
        response.setSukuBungaPerTahun(String.format("%.4f", sukuBungaPerTahun));
        response.setSimulasiTotalManfaatPensiun(String.format("%,d", (long) totalManfaatPensiun));
        response.setSimulasiAngsuranPerBulan(String.format("%,d", (long) angsuranPerBulan));

        return response;
    }

    // Metode untuk menghitung suku bunga
    private Double rate(int nper, double pmt, double pv, double fv, int type, double guess) {
        // Set default values for missing parameters
        fv = (fv != 0) ? fv : 0;
        type = (type != 0) ? type : 0;

        // Set limits for possible guesses
        double lowLimit = 0;
        double highLimit = 1;

        // Define tolerance
        double tolerance = Math.abs(0.00000005 * pmt);

        // Try up to 40 times to find a solution
        for (int i = 0; i < 40; i++) {
            double balance = pv; // Reset balance

            // Calculate the balance at the end of the loan
            for (int j = 0; j < nper; j++) {
                if (type == 0) {
                    balance = balance * (1 + guess) + pmt; // Interests applied before payment
                } else {
                    balance = (balance + pmt) * (1 + guess); // Payments applied before interests
                }
            }

            // Return guess if balance is within tolerance
            if (Math.abs(balance + fv) < tolerance) {
                return guess;
            } else if (balance + fv > 0) {
                highLimit = guess; // Current guess was too big
            } else {
                lowLimit = guess; // Current guess was too small
            }

            // Calculate new guess
            guess = (highLimit + lowLimit) / 2;
        }

        return null; // If no acceptable result was found
    }
}
