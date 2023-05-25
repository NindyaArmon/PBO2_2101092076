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
import nindy.dao.PeminjamanDao;
import nindy.dao.PeminjamanDaoImpl;
import nindy.dao.Koneksi;
import nindy.model.Peminjaman;
import nindy.view.FormPeminjaman;

/**
 *
 * @author hp
 */
public class PeminjamanController {
    private FormPeminjaman formPeminjaman;
    private Peminjaman peminjaman;
    private PeminjamanDao peminjamanDao;
    private Connection con;
    private Koneksi k;
    
    public PeminjamanController(FormPeminjaman formPeminjaman){
        try {
            this.formPeminjaman = formPeminjaman;
            peminjamanDao = new PeminjamanDaoImpl();
            k = new Koneksi();
            con = k.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        formPeminjaman.getTxtKodeanggota().setText("");
        formPeminjaman.getTxtKodebuku().setText("");
        formPeminjaman.getTxtTglpinjam().setText("");
        formPeminjaman.getTxtTglkembali().setText("");
    }
    
    public void insert(){
        try {
            peminjaman = new Peminjaman();
            peminjaman.setKodeanggota(formPeminjaman.getTxtKodeanggota().getText());
            peminjaman.setKodebuku(formPeminjaman.getTxtKodebuku().getText());
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglpinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglkembali().getText());
            peminjamanDao.insert(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Entri ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
  
    public void update(){
        try {
            peminjaman = new Peminjaman();
            peminjaman.setTglkembali(formPeminjaman.getTxtTglkembali().getText());
            peminjamanDao.update(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman,"Update ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            peminjamanDao.delete(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formPeminjaman.getTxtKodeanggota().getText();
            peminjaman = peminjamanDao.getPeminjaman(con,kode);
            if(peminjaman != null){
                formPeminjaman.getTxtTglkembali().setText(peminjaman.getTglkembali());
            } else {
                JOptionPane.showMessageDialog(formPeminjaman, "Data Tidak Ditemukan");
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPeminjaman.getTblPeminjaman().getModel();
            tabel.setRowCount(0);
            List<Peminjaman> list = peminjamanDao.getAllPeminjaman(con);
            for (Peminjaman peminjaman1 : list) {
                Object[] row = {
                    peminjaman1.getKodeanggota(),
                    peminjaman1.getKodebuku(),
                    peminjaman1.getTglpinjam(),
                    peminjaman1.getTglkembali()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
