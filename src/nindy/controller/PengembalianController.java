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
import nindy.dao.Koneksi;
import nindy.dao.PeminjamanDao;
import nindy.dao.PeminjamanDaoImpl;
import nindy.dao.PengembalianDao;
import nindy.dao.PengembalianDaoImpl;
import nindy.model.Anggota;
import nindy.model.Peminjaman;
import nindy.model.Pengembalian;
import nindy.view.FormPengembalian;

/**
 *
 * @author hp
 */
public class PengembalianController {
    FormPengembalian formPengembalian;
    AnggotaDao anggotaDao;
    BukuDao bukuDao;
    PeminjamanDao peminjamanDao;
    PengembalianDao pengembalianDao;
    Pengembalian pengembalian;
    Connection con;
    
    public PengembalianController(FormPengembalian formPengembalian) {
        try {
            this.formPengembalian = formPengembalian;
            anggotaDao = new AnggotaDaoImpl();
            bukuDao = new BukuDaoImpl();
            peminjamanDao = new PeminjamanDaoImpl();
            pengembalianDao = new PengembalianDaoImpl();
            Koneksi k = new Koneksi();
            con = k.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void insert(){
        try {
            pengembalian = new Pengembalian();
            pengembalian.setKodeanggota(formPengembalian.getTxtTglpinjam().getText());
            pengembalian.setKodebuku(formPengembalian.getTxtTglpinjam().getText());
            pengembalian.setTglpinjam(formPengembalian.getTxtTglpinjam().getText());
            pengembalian.setTglkembali(formPengembalian.getTxtTglpinjam().getText());
            pengembalianDao.insert(con, pengembalian);
            JOptionPane.showMessageDialog(formPengembalian, "Entri Data OKE");
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            pengembalian.setKodeanggota(formPengembalian.getTxtTglpinjam().getText());
            pengembalian.setKodebuku(formPengembalian.getTxtTglpinjam().getText());
            pengembalian.setTglpinjam(formPengembalian.getTxtTglpinjam().getText());
            pengembalian.setTglkembali(formPengembalian.getTxtTglpinjam().getText());
            pengembalian.setTgldikembalikan(formPengembalian.getTxtTglpinjam().getText());
            pengembalianDao.update(con, pengembalian);
            JOptionPane.showMessageDialog(formPengembalian, "Update Data OKE");
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        formPengembalian.getTxtTglpinjam().setText("");
        formPengembalian.getTxtTglkembali().setText("");
        formPengembalian.getTxtTgldikembalikan().setText("");
        formPengembalian.getTxtTerlambat().setText("");
        formPengembalian.getTxtDenda().setText("");
        formPengembalian.getTxtKodeanggota().setText("");
        formPengembalian.getTxtKodebuku().setText("");
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel)
                    formPengembalian.getTblPengembalian().getModel();
            tabelModel.setRowCount(0);
            List<Pengembalian> list = pengembalianDao.getAllPengembalian(con);
            for (Pengembalian p : list) {
                Anggota anggota = anggotaDao.getAnggota(con, p.getKodeanggota());
                Peminjaman pinjam = peminjamanDao.getPeminjaman(con, p.getKodeanggota(),p.getKodebuku(), p.getTglpinjam());
                Object[] row = {
                    p.getKodeanggota(),
                    anggota.getNamaAnggota(),
                    p.getKodebuku(),
                    pinjam.getTglpinjam(),
                    pinjam.getTglkembali(),
                    p.getTgldikembalikan(),
                    p.getTerlambat(),
                    p.getDenda()
                };
                tabelModel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPengembalian(){
        try {
            String kodeAnggota = formPengembalian.getTblPengembalian().getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 0).toString();
            String kodebuku = formPengembalian.getTblPengembalian().getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 2).toString();
            String tglpinjam = formPengembalian.getTblPengembalian()
                    .getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 3).toString();
            pengembalian = new Pengembalian();
            Peminjaman peminjaman = peminjamanDao.getPeminjaman(con, kodeAnggota, kodebuku, tglpinjam);
            int terlambat = pengembalianDao.getSelisihTanggal(con, pengembalian.getTgldikembalikan(),peminjaman.getTglkembali());
            pengembalian.setTerlambat(terlambat);
            double denda = pengembalian.getDenda();
            formPengembalian.getTxtKodeanggota().setText(kodeAnggota);
            formPengembalian.getTxtKodebuku().setText(kodebuku);
            formPengembalian.getTxtTglpinjam().setText(tglpinjam);
            formPengembalian.getTxtTglkembali().setText(peminjaman.getTglkembali()); 
            formPengembalian.getTxtTgldikembalikan().setText(pengembalian.getTgldikembalikan());
            formPengembalian.getTxtTerlambat().setText(terlambat+""); 
            formPengembalian.getTxtDenda().setText(denda+"");
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
