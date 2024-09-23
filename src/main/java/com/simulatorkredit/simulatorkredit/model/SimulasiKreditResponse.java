package com.simulatorkredit.simulatorkredit.model;

public class SimulasiKreditResponse {
    private String totalPinjaman;
    private int jangkaWaktuKredit;
    private String sukuBungaPerTahun;
    private String simulasiTotalManfaatPensiun;
    private String simulasiAngsuranPerBulan;

    // Getters and Setters
    public String getTotalPinjaman() {
        return totalPinjaman;
    }

    public void setTotalPinjaman(String totalPinjaman) {
        this.totalPinjaman = totalPinjaman;
    }

    public int getJangkaWaktuKredit() {
        return jangkaWaktuKredit;
    }

    public void setJangkaWaktuKredit(int jangkaWaktuKredit) {
        this.jangkaWaktuKredit = jangkaWaktuKredit;
    }

    public String getSukuBungaPerTahun() {
        return sukuBungaPerTahun;
    }

    public void setSukuBungaPerTahun(String sukuBungaPerTahun) {
        this.sukuBungaPerTahun = sukuBungaPerTahun;
    }

    public String getSimulasiTotalManfaatPensiun() {
        return simulasiTotalManfaatPensiun;
    }

    public void setSimulasiTotalManfaatPensiun(String simulasiTotalManfaatPensiun) {
        this.simulasiTotalManfaatPensiun = simulasiTotalManfaatPensiun;
    }

    public String getSimulasiAngsuranPerBulan() {
        return simulasiAngsuranPerBulan;
    }

    public void setSimulasiAngsuranPerBulan(String simulasiAngsuranPerBulan) {
        this.simulasiAngsuranPerBulan = simulasiAngsuranPerBulan;
    }
}
