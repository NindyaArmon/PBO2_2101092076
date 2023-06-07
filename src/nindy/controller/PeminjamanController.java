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
import nindy.dao.BukuDao;
import nindy.dao.BukuDaoImpl;
import nindy.dao.PeminjamanDao;
import nindy.dao.PeminjamanDaoImpl;
import nindy.dao.Koneksi;
import nindy.model.Anggota;
import nindy.model.Buku;
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
    private AnggotaDao anggotaDao;
    private BukuDao bukuDao;
    private Connection con;
    private Koneksi koneksi;
    
     public PeminjamanController(FormPeminjaman formPeminjaman){
        try {
            this.formPeminjaman = formPeminjaman;
            peminjamanDao = new PeminjamanDaoImpl();
            anggotaDao = new AnggotaDaoImpl();
            bukuDao = new BukuDaoImpl();
            Koneksi k = new Koneksi();
            con = k.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        formPeminjaman.getTxtTglpinjam().setText("");
        formPeminjaman.getTxtTglkembali().setText("");
    }
    
    public void isiCombo(){
        try {
            formPeminjaman.getCboKodeanggota().removeAllItems();
            formPeminjaman.getCboKodebuku().removeAllItems();
            List<Anggota> anggotaList = anggotaDao.getAllAnggota(con);
            List<Buku> bukuList = bukuDao.getAllBuku(con);
            for (Anggota anggota : anggotaList){
                formPeminjaman.getCboKodeanggota().addItem(anggota.getKodeAnggota()+"-"+anggota.getNamaAnggota());
            }
            for (Buku buku : bukuList){
                formPeminjaman.getCboKodebuku().addItem(buku.getKodebuku());
            }} catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void insert(){
        try {
            peminjaman = new Peminjaman();
            peminjaman.setKodeanggota(formPeminjaman.getCboKodeanggota().getSelectedItem().toString().split("-")[0]);
            peminjaman.setKodebuku(formPeminjaman.getCboKodebuku().getSelectedItem().toString());
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglpinjam().toString());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglkembali().toString());
            peminjamanDao.insert(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Entri Data OKE");
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

     public void getPeminjaman(){
        try {
            String kodeanggota = formPeminjaman.getTblPeminjaman().getValueAt(formPeminjaman.getTblPeminjaman().getSelectedRow(), 0).toString();
            String kodebuku = formPeminjaman.getTblPeminjaman().getValueAt(formPeminjaman.getTblPeminjaman().getSelectedRow(), 1).toString();
            String tglpinjam = formPeminjaman.getTblPeminjaman().getValueAt(formPeminjaman.getTblPeminjaman().getSelectedRow(), 2).toString();
            peminjaman = peminjamanDao.getPeminjaman(con, kodeanggota, kodebuku, tglpinjam);
            if(peminjaman!=null){
                Anggota anggota = anggotaDao.getAnggota(con, peminjaman.getKodeanggota());
                formPeminjaman.getCboKodeanggota().setSelectedItem(anggota.getKodeAnggota()+"-"+anggota.getNamaAnggota());
                formPeminjaman.getCboKodebuku().setSelectedItem(peminjaman.getKodebuku());
                formPeminjaman.getTxtTglpinjam().setText(peminjaman.getTglpinjam());
                formPeminjaman.getTxtTglkembali().setText(peminjaman.getTglkembali());
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(){
        try {
            peminjaman = new Peminjaman();
            peminjaman.setKodeanggota(formPeminjaman.getCboKodeanggota().getSelectedItem().toString().split("-")[0]);
            peminjaman.setKodebuku(formPeminjaman.getCboKodebuku().getSelectedItem().toString());
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglpinjam().toString());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglkembali().toString());
            peminjamanDao.update(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Update Data OKE");
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void delete(){
        try {
            peminjamanDao.delete(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Delete OK");
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        
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
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
