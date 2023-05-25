/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nindy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import nindy.model.Peminjaman;

/**
 *
 * @author hp
 */
public class PeminjamanDaoImpl implements PeminjamanDao {

    
    @Override
    public void insert(Connection con, Peminjaman peminjaman) throws Exception{
        String sql = "insert into peminjaman values (?,?,?,?)";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setString(1, peminjaman.getKodeanggota());
        ps.setString(2, peminjaman.getKodebuku());
        ps.setString(3, peminjaman.getTglpinjam());
        ps.setString(4, peminjaman.getTglkembali());
        ps.executeUpdate();
    }
    
    @Override
    public void update (Connection con, Peminjaman peminjaman) throws Exception {
        String sql = "Update peminjaman set tglkembali=? where kodeanggota = ? and kodebuku = ? and tglpinjam = ?";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setString(1, peminjaman.getTglkembali());
        ps.setString(2, peminjaman.getKodeanggota());
        ps.setString(3, peminjaman.getTglpinjam());
        ps.setString(4, peminjaman.getTglpinjam());
        ps.executeUpdate();
    }
    
    public void delete (Connection con, Peminjaman peminjaman) throws Exception {
        String sql = "delete from peminjaman " + "where kodeAnggota = ?";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setString(1, peminjaman.getKodeanggota());
	ps.setString(2, peminjaman.getKodebuku());
	ps.setString(1, peminjaman.getTglpinjam());
        ps.executeUpdate();  
    }
    
    public Peminjaman getPeminjaman(Connection con, String kode) throws Exception {
        String sql = "select * from peminjaman set tglkembali=? where kodeanggota = ? and kodebuku = ? and tglpinjam = ?";
        PreparedStatement ps= con.prepareStatement(sql);
         ps.setString(1, kode);
         ResultSet rs = ps.executeQuery();
         Peminjaman peminjaman = null;
         if(rs.next()){
            peminjaman = new Peminjaman();
            peminjaman.setKodeanggota(rs.getString(1));
            peminjaman.setKodebuku(rs.getString(2));
            peminjaman.setTglpinjam(rs.getString(3));
            peminjaman.setTglkembali(rs.getString(4));
         }
         return peminjaman;    
    }
    
    public List<Peminjaman> getAllPeminjaman(Connection con) throws Exception{
        String sql = "select * from peminjaman";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Peminjaman> list = new ArrayList();
        Peminjaman peminjaman = null;
        while (rs.next()){
            peminjaman = new Peminjaman ();
            peminjaman.setKodeanggota(rs.getString(1));
            peminjaman.setKodebuku(rs.getString(2));
            peminjaman.setTglpinjam(rs.getString(3));
            peminjaman.setTglkembali(rs.getString(4));
            list.add(peminjaman);
        }
        return list;
        
    }

    @Override
    public List<Peminjaman> getAllPeminajaman(Connection con) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
