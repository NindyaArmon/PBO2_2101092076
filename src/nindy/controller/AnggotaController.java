/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nindy.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nindy.dao.AnggotaDao;
import nindy.dao.AnggotaDaoImpl;
import nindy.dao.FormAnggota;
import nindy.dao.Koneksi;
import nindy.model.Anggota;

/**
 *
 * @author hp
 */
public class AnggotaController {
    private FormAnggota formAnggota;
    private Anggota anggota;
    private AnggotaDao anggotaDao;
    private Connection con;
    private Koneksi k;
    
    public AnggotaController(FormAnggota formanggota){
        try {
            this.formAnggota = new FormAnggota();
            anggotaDao = new AnggotaDaoImpl();
            con = new Koneksi().getKoneksi();
            k = new Koneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        formAnggota.getTxtKodeAnggota().setText("");
        formAnggota.getTxtNamaAnggota().setText("");
        formAnggota.getTxtAlamat().setText("");
    }
    
    public void insert(){
        try {
            anggota = new Anggota();
            anggota.setKodeAnggota(formAnggota.getTxtKodeAnggota().getText());
            anggota.setNamaAnggota(formAnggota.getTxtNamaAnggota().getText());
            anggota.setAlamat(formAnggota.getTxtAlamat().getText());
            anggota.setJenisKelamin(formAnggota.getCboJenisKelamin().getSelectedItem().toString());
            anggotaDao.insert(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Entri OK");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            anggota = new Anggota();
            anggota.setKodeAnggota(formAnggota.getTxtKodeAnggota().getText());
            anggota.setNamaAnggota(formAnggota.getTxtNamaAnggota().getText());
            anggota.setAlamat(formAnggota.getTxtAlamat().getText());
            anggota.setJenisKelamin(formAnggota.getCboJenisKelamin().getSelectedItem().toString());
            anggotaDao.update(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Update OK");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            anggotaDao.delete(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Delete OK");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formAnggota.getTxtKodeAnggota().getText();
            anggota = anggotaDao.getAnggota(con, kode);
            if (anggota != null){
                anggota.setNamaAnggota(formAnggota.getTxtNamaAnggota().getText());
                anggota.setAlamat(formAnggota.getTxtAlamat().getText());
                anggota.setJenisKelamin(formAnggota.getCboJenisKelamin().getSelectedItem().toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formAnggota.getTblAnggota().getModel();
            tabel.setRowCount(0);
            List<Anggota> list = anggotaDao.getAllAnggota(con);
            for (Anggota anggota1 : list) {
                Object[] row = {
                    anggota1.getKodeAnggota(),
                    anggota1.getNamaAnggota(),
                    anggota1.getJenisKelamin()
                };
                tabel.addRow(row); 
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboJenisKelamin(){
        formAnggota.getCboJenisKelamin().removeAllItems();
        formAnggota.getCboJenisKelamin().addItem("L");
        formAnggota.getCboJenisKelamin().addItem("P");
    }
    
}
