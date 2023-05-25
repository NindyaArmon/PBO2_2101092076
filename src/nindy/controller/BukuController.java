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
import nindy.dao.BukuDao;
import nindy.dao.BukuDaoImpl;
import nindy.dao.Koneksi;
import nindy.model.Buku;
import nindy.view.FormBuku;

/**
 *
 * @author hp
 */
public class BukuController {
    private FormBuku formBuku;
    private Buku buku;
    private BukuDao bukuDao;
    private Connection con;
    private Koneksi k;
    
     public BukuController(FormBuku formBuku){
        try {
            this.formBuku = formBuku;
            bukuDao = new BukuDaoImpl();
            k = new Koneksi();
            con = k.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void clearForm(){
        formBuku.getTxtKodebuku().setText("");
        formBuku.getTxtJudulbuku().setText("");
        formBuku.getTxtPengarang().setText("");
        formBuku.getTxtPenerbit().setText("");
    }
     
    public void insert(){
        try {
            buku = new Buku();
            buku.setKodebuku(formBuku.getTxtKodebuku().getText());
            buku.setJudulbuku(formBuku.getTxtJudulbuku().getText());
            buku.setPengarang(formBuku.getTxtPengarang().getText());
            buku.setPenerbit(formBuku.getTxtPenerbit().getText());
            bukuDao.insert(con, buku);
            JOptionPane.showMessageDialog(formBuku, "Entri ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
      
    public void update(){
        try {
            buku = new Buku();
            buku.setKodebuku(formBuku.getTxtKodebuku().getText());
            buku.setJudulbuku(formBuku.getTxtJudulbuku().getText());
            buku.setPengarang(formBuku.getTxtPengarang().getText());
            buku.setPenerbit(formBuku.getTxtPenerbit().getText());
            bukuDao.update(con, buku);
            JOptionPane.showMessageDialog(formBuku,"Update ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            bukuDao.delete(con, buku);
            JOptionPane.showMessageDialog(formBuku, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formBuku.getTxtKodebuku().getText();
            buku = bukuDao.getBuku(con,kode);
            if(buku != null){
                formBuku.getTxtJudulbuku().setText(buku.getJudulbuku());
                formBuku.getTxtPengarang().setText(buku.getPengarang());
                formBuku.getTxtPenerbit().setText(buku.getPenerbit());
                
            } else {
                JOptionPane.showMessageDialog(formBuku, "Data Tidak Ditemukan");
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formBuku.getTblAnggota().getModel();
            tabel.setRowCount(0);
            List<Buku> list = bukuDao.getAllBuku(con);
            for (Buku buku1 : list) {
                Object[] row = {
                    buku1.getKodebuku(),
                    buku1.getJudulbuku(),
                    buku1.getPengarang(),
                    buku1.getPenerbit()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
