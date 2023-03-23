/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nindy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import nindy.model.Anggota;

/**
 *
 * @author hp
 */
public interface AnggotaDao {
    void insert(Connection con, Anggota anggota) throws Exception;
}
