/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nindy.model;

/**
 *
 * @author hp
 */
public class Anggota {
    private String kodeAnggota;
    private String namaAnggota;
    private String alamat;
    private String jenisKelamin;
    
    public  Anggota (){

    }

    public Anggota(String kodeanggota, String namaanggota, String alamat, String jeniskelamin) {
        this.kodeAnggota = kodeanggota;
        this.namaAnggota = namaanggota;
        this.alamat = alamat;
        this.jenisKelamin = jeniskelamin;
    }

    public String getKodeAnggota() {
        return kodeAnggota;
    }

    public void setKodeAnggota(String kodeAnggota) {
        this.kodeAnggota = kodeAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}