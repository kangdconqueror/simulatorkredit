package com.simulatorkredit.simulatorkredit.model;

public class SimulasiKreditRequest {
    private double totalPinjaman;
    private int jangkaWaktuKredit;
    private double sukuBunga;

    // Getters and Setters
    public double getTotalPinjaman() {
        return totalPinjaman;
    }

    public void setTotalPinjaman(double totalPinjaman) {
        this.totalPinjaman = totalPinjaman;
    }

    public int getJangkaWaktuKredit() {
        return jangkaWaktuKredit;
    }

    public void setJangkaWaktuKredit(int jangkaWaktuKredit) {
        this.jangkaWaktuKredit = jangkaWaktuKredit;
    }

    public double getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(double sukuBunga) {
        this.sukuBunga = sukuBunga;
    }
}
