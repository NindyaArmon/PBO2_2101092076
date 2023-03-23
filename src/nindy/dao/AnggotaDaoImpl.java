/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nindy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import nindy.model.Anggota;

/**
 *
 * @author hp
 */
public class AnggotaDaoImpl implements AnggotaDao {
    
    public void insert(Connection con, Anggota anggota) throws Exception{
        String sql = "insret into anggota values ?,?,?,?";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setString(1, anggota.getKodeAnggota());
        ps.setString(2, anggota.getNamaAnggota());
        ps.setString(3, anggota.getAlamat());
        ps.setString(4, anggota.getJenisKelamin());
        ps.executeUpdate();
    }
    
}
