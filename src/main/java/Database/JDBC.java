/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import static Controller.Controller.getView;
import static Controller.Controller.model;
import Model.Enemy;
import VIew.ScoreBoard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author haika
 */
public class JDBC {
    public static final String url = "jdbc:mysql://localhost:3306/Database_akun";
    public static final String user = "root";
    public static final String pass = "haikal123";
    public static Connection connect;
    public static ResultSet RS;
    public static Statement stmt;
    
    public void connectJDBC(){
        try{
            connect = DriverManager.getConnection(url, user, pass);
        }catch(Exception e){
            JOptionPane.showMessageDialog(Controller.Controller.getView(), "Gagal Connect", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void addscoretodatabase(){
        try{
            connectJDBC();
            stmt = connect.createStatement();
            if(model.enemy.getMusuh() == Enemy.Enemies.Dragoon){
                // buat statement insert kemudian execute
                String sql = "insert into score(nama_user,last_equipment,score,last_enemies) values('" + model.player.getNama() + "','" + model.player.wear.getNama() + "', "
                        + model.getFullScore() + ",'" + model.enemy.getMusuh().toString() + "(Last Boss)" + "'" + ")";
                stmt.executeUpdate(sql);
            }else{
                // buat statement insert kemudian execute
                String sql = "insert into score(nama_user,last_equipment,score,last_enemies) values('" + model.player.getNama() + "','" + model.player.wear.getNama() + "', "
                        + model.getFullScore() + ",'" + model.enemy.getMusuh().toString() + "(" + model.enemy.getType().toString() + ")" + "'" + ")";
                stmt.executeUpdate(sql);
            }
            disconnectJDBC();
            stmt.close();
            JOptionPane.showMessageDialog(getView(), "Berhasil Menambahkan score", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // panel/dialog jika gagal memasukan data ke database
            JOptionPane.showMessageDialog(getView(), e.getMessage());
        }
            
    }
    
    public void showtablescore(){
        try{
            connectJDBC();
            stmt = connect.createStatement();
            RS = stmt.executeQuery("select * from score");
            int id;
            String nama;
            String equipment;
            int score;
            String enemy;
            while (RS.next()) {
                id = RS.getInt("id");
                nama = RS.getString("nama_user");
                equipment = RS.getString("last_equipment");
                score = RS.getInt("score");
                enemy = RS.getString("last_enemies");
                ScoreBoard.addscoredata(id, nama, equipment, score, enemy);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void disconnectJDBC(){
        try{
            connect.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(Controller.Controller.getView(), "Gagal disconnect", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
