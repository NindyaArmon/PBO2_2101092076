/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nindy.dao;

import java.sql.Connection;
import java.util.List;
import nindy.model.Peminjaman;


/**
 *
 * @author hp
 */
public interface PeminjamanDao {
    public void insert(Connection con, Peminjaman peminjaman) throws Exception;
    public void update(Connection con, Peminjaman peminjaman) throws Exception;
    public void delete(Connection con, Peminjaman peminjaman) throws Exception;
    public Peminjaman getPeminjaman(Connection con, String kode) throws Exception;
    public List<Peminjaman> getAllPeminajaman(Connection con) throws Exception;

}
