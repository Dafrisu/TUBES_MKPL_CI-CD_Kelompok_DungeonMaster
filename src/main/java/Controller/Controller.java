/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Database.JDBC;
import Model.*;
import VIew.GUI;
import VIew.Main_Menu;
import VIew.ScoreBoard;
import java.awt.Color;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author haika
 */
public class Controller {
    public static Model model;
    private static GUI view;
    private Main_Menu menu;
    private ScoreBoard SCBD;
    
    public Controller(Model model, GUI view, Main_Menu menu, ScoreBoard SCBD){
        this.model = model;
        this.view = view;
        this.menu = menu;
        this.SCBD = SCBD;
        init();
        view.getAttack_button().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                attackbutton();
            }
        });
        view.getOpsi1().addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Opsi1ActionPerformed();
            }
            
        });
        view.getOpsi2().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Opsi2ActionPerformed();
            }
            
        });
        view.getBoxdialog1().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                BoxDialogPerformed();
            }
            
        });
        view.getOkbutton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                OKButton();
            }
        
        });
        view.getButton_assasin().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAssassin();
            }
        });
        view.getButton_guardian().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonGuardian();
            }
        });
        view.getButton_necromancer().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonNecromancer();
            }

        });
        view.getButton_archer().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonArcher();
            }
            
        });
        view.getYesclass().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                yesclassbutton();
            }
            
        });
        view.getNoclass().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                noclassbutton();
            }
        });
        view.getWinorlose().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                winorlosebox();
            }
        });
        view.getStage().addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                statestagechange();
            }
        });
        view.getHPMusuh().addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                hpmusuhstatechange();
            }
            
        });
        view.getRestart_onmenang().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                RestartWin();
            }
            
        });
        view.getRestart_onkalah().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                RestartLose();
            }
        });
        view.getMainMenu_onmenang().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuWin();
            }
        });
        view.getMainMenu_onkalah().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuLose();
            }
            
        });
        
        // ------ ACtion performed Main menu-------//
        
        menu.getPlay_button().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                mousePlayenter();
            }
        });
        menu.getPlay_button().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent e){
                mousePlayexit();
            }
        });
        menu.getScoreBoard_button().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                mouseScoreenter();
            }
        });
        menu.getScoreBoard_button().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent e){
                mouseScoreexit();
            }
        });
        menu.getExit_Button().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                mouseExitEnter();
            }
        });
        menu.getExit_Button().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent e){
                mouseExitexit();
            }
        });
        menu.getPlay_button().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Playclicked();
            }
        });
        menu.getScoreBoard_button().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreBoard.model.setRowCount(0);
                model.database.showtablescore();
                //isitabelscore();
                Scoreclicked();
            }
        });
        menu.getExit_Button().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Exitclicked();
            }
        });
        
        // ---------- Bagian JDialog ScoreBoard ----------- //
        
        SCBD.getClose().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                closeScore();
                
            }
        });
    }
    
    
    public void init(){
        getView().getNamaPlayer().setVisible(false);
        getView().getAttack_button().setVisible(false);
        model.npc = new NPC(10,10,10);
        
        getView().getWinorlose().setVisible(false);
        getView().getListdamage().setModel(getView().model);
    }
    
    // button untuk menyerang musuh
    public void attackbutton(){
        // player attack enemy kemudian set bar HP musuh sesuai damage yang diterima
        model.player.attack(model.enemy);
        getView().getHPMusuh().setValue(model.enemy.getHP());

        // memasukan data damage ke model list di GUI
        getView().model.addElement(model.enemy.getMusuh().name() + " Terkena Serangan " + model.player.getDamage() + " Damage");
        
        // jika enemy belum mati, player terkena serangan
        if(model.enemy.getHP() >= 0){
            model.enemy.attack(model.player);
            getView().model.addElement(model.player.getNama() + " Terkena Serangan " + model.enemy.getDamage() + " Damage");
            ChangeAttr();
        }
        
        // jika player mati, ganti tab panel ke panel lose
        if (model.player.getHP() <=0){
            // set attribut Hp musuh jadi 0 dan ubah attributnya di GUI
            model.player.setHP(0);
            ChangeAttr();
            getView().setWin(winlose(model.player));
            if(!view.isWin()){
                getView().getAttack_button().setVisible(false);
                timerlose();
            }
        }
        
        // jika enemy mati, set button attack invisible, lalu masuk ke fungsi timerwin(delay untuk stage selanjutnya)
        if (model.enemy.getHP() < 0){
            // absorbHP musuh dan ditambahkan ke HP player
            view.absorbHP = getView().absorbHP + model.enemy.getMaxHP()*10/100;
            model.player.setMaxHP();
            
            // memasukan info absorb ke model list 
            getView().model.addElement("Absorb HP musuh sebanyak "+  (model.enemy.getMaxHP()*10/100) );
            model.player.setHP(model.player.getMaxHP());
            ChangeAttr();
            getView().setWin(winlose(model.enemy));
            if(getView().isWin()){
                getView().getAttack_button().setVisible(false);
                timerwin(); 
            }
        }
    }
    
    // Pengecekan jika Entity kalah, jika musuh kalah return win, jika player kalah return !win
    public boolean winlose(Entity e){
        boolean win = false;
        
        // jika entity adalah enemy, dan enemy Hp <0, return win
        if(e instanceof Enemy){
            if(e.getHP() <=0){
                win = true;
            }
        }
        
        // jika entity adalah player, dan player Hp < 0, return !win
        if(e instanceof Player){
            if(e.getHP() <= 0){
                win = false;
            }
        }
        return win;
    }
    
    // set NPC dialog di GUI
    public void NPCInteraction(){
        
        String[] textNPC = new String[5];
        System.out.println(textNPC.length);
        textNPC = model.npc.getDialogue();
        try {
            if (getView().getIdxDialogue() == textNPC.length -1) {
                throw new Exception("Sudah max idx");
            }
            if (getView().getIdxDialogue()<textNPC.length){
                getView().getLabelbox().setText(textNPC[getView().getIdxDialogue()]);
                getView().setIdxDialogue(getView().getIdxDialogue() + 1);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void delay(ActionListener e){
        Timer timer = new Timer(1000, e);
        timer.setRepeats(false);
        timer.start();
    }
    
    // delay untuk menampilkan border menang
    public void timerwin(){
        Timer timer = new Timer(1000, new ActionListener() { // Delay 1 detik (1000 milidetik)
            @Override
            public void actionPerformed(ActionEvent e) {
                // Proses yang akan dilakukan setelah delay
                    getView().getWinorlose().setVisible(true);
                    getView().getWinorlose().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    getView().getAfterbattle().setText("Anda Berhasil Mengalahkan Musuh");
            }
        });
        timer.setRepeats(false); // Setel agar timer hanya berjalan satu kali
        timer.start(); // Memulai timer
    }
    
    // delay untuk menampilkan border kalah
    public void timerlose(){
        Timer timer = new Timer(1000, new ActionListener() { // Delay 1 detik (1000 milidetik)
            @Override
            public void actionPerformed(ActionEvent e) {
                // Proses yang akan dilakukan setelah delay
                    getView().getWinorlose().setVisible(true);
                    getView().getWinorlose().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    getView().getAfterbattle().setText("Telah dikalahkan oleh musuh....");
            }
        });
        timer.setRepeats(false); // Setel agar timer hanya berjalan satu kali
        timer.start(); // Memulai timer
    }
    // Timer untuk delay sebelum ganti stage
    public void Timer(){
        // timer ganti stage jika menang
        if(getView().isWin()){
            Timer timer = new Timer(1000, new ActionListener() { // Delay 1 detik (1000 milidetik)
            @Override
            public void actionPerformed(ActionEvent e) {
                // Proses yang akan dilakukan setelah delay
                    getView().getAlur().run(model.enemy);
                if( getView().getAlur().getCurrentInteraction() == getView().getAlur().getMAX_INTERACTIONS()){
                        getView().getStage().setSelectedIndex(3);
                    int fullScore = Model.getScores() + model.player.getMaxHP() + model.player.getMaxDef() + model.player.getMaxAtk();
                    model.setFullScore(fullScore);
                        getView().getScoreonwin().setText("Score : " + model.getFullScore());
                    model.database.addscoretodatabase();
                        getView().getAttack_button().setVisible(false);
                }
                if (getView().getAlur().getCurrentInteraction() < getView().getAlur().getMAX_INTERACTIONS()) {
                    if (getView().getAlur().isBattle() == true) {
                            getView().getStage().setSelectedIndex(3);
                            getView().getStage().setSelectedIndex(2);
                            getView().getAlur().setCurrentInteraction(getView().getAlur().getCurrentInteraction() + 1);
                    } else {
                            getView().getStage().setSelectedIndex(3);
                            getView().getStage().setSelectedIndex(5);
                        setOpsiName();
                            getView().getOpsi1().setVisible(false);
                            getView().getOpsi2().setVisible(false);
                            getView().getAlur().setCurrentInteraction(getView().getAlur().getCurrentInteraction() + 1);
                    }
                } else {
                        getView().getStage().setSelectedIndex(3);
                }    
                    Model.scores100();
                    getView().getWinorlose().setVisible(false);
                    getView().getLabelbox().setText("|");
                    getView().setIdxDialogue(0);
                }
        });
        timer.setRepeats(false); // Setel agar timer hanya berjalan satu kali
        timer.start(); // Memulai timer
        }else{
            Timer timer = new Timer(1000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(model.enemy.getMusuh() != Enemy.Enemies.Dragoon){
                        getView().getStage().setSelectedIndex(6);
                        int fullScore = Model.getScores() + model.player.getMaxHP() + model.player.getMaxDef() + model.player.getMaxAtk();
                        model.setFullScore(fullScore);
                        getView().getScoreonlose().setText("Score : " + model.getFullScore());
                        getView().getDeadmessage().setText("Player Dibunuh Oleh " + model.enemy.getMusuh() +" ("+model.enemy.getType()+")");
                        model.database.addscoretodatabase();
                    }else{
                        getView().getStage().setSelectedIndex(6);
                        int fullScore = Model.getScores() + model.player.getMaxHP() + model.player.getMaxDef() + model.player.getMaxAtk();
                        model.setFullScore(fullScore);
                        getView().getScoreonlose().setText("Score : " + model.getFullScore());
                        getView().getDeadmessage().setText("Player Dibunuh Oleh " + model.enemy.getMusuh() + "(Last Boss)");
                        model.database.addscoretodatabase();
                    }
                    
                }
                
            });
            timer.setRepeats(false); // Setel agar timer hanya berjalan satu kali
            timer.start(); // Memulai timer
        }
         // error still
    }
    public void setOpsiName(){
        if (model.npc.getNpctype().equals("Merchant")) {
            getView().getOpsi1().setText(model.npc.reward1.getNama());
            getView().getOpsi2().setText(model.npc.reward2.getNama());
        } else {
            getView().getOpsi1().setText("Terima");
            getView().getOpsi2().setText("Tolak");
        }
    }
    // memilih opsi pertama ketika interaksi dengan NPC
    private void Opsi1ActionPerformed() {                                   
        int pilih;
        pilih = 1;
        model.npc.pilihReward(model.player,pilih);
        model.player.setMaxHP();
        ChangeAttr();
        model.npc = new NPC(10,10,10);
        model.npc.reward1.EquipmentInit(model.player);
        model.npc.reward2.EquipmentInit(model.player);
        model.npc.setReward();
        Timer();
    }                                     

    // memilih opsi kedua ketika interaksi dengan NPC
    private void Opsi2ActionPerformed() {                                    
        int pilih;
        pilih = 2;
        if(model.npc.getNpctype().equals("Merchant")){
            model.npc.pilihReward(model.player, pilih);
            model.player.setMaxHP();
            ChangeAttr();
            model.npc = new NPC(10, 10, 10);
            model.npc.reward1.EquipmentInit(model.player);
            model.npc.reward2.EquipmentInit(model.player);
            model.npc.setReward();
            Timer(); 
        }else{
            Timer();
        }
    }
    
    //menampilkan semua dialog npc di box panel
    private void BoxDialogPerformed(){
        ArrayList<String> textNPC = new ArrayList<>();
        textNPC = model.npc.getListDialog();
        System.out.println(textNPC.size());
        try {
            if(getView().getIdxDialogue() == textNPC.size() - 1){
                getView().getOpsi1().setVisible(true);
                getView().getOpsi2().setVisible(true);
            }

            if (getView().getIdxDialogue() == textNPC.size()) {
                throw new Exception("Mencapai akhir dialog");
            }
            if (getView().getIdxDialogue()<textNPC.size() ){
                getView().getLabelbox().setText(textNPC.get(getView().getIdxDialogue()));
                getView().setIdxDialogue(getView().getIdxDialogue() + 1);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(getView(), e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //mengkonfirm nama player
    private void OKButton(){
        if (!view.getGetNamePlayer().getText().equals("")){
            String nama = getView().getGetNamePlayer().getText();
            model.player = new Player(nama, 50,30,30);
            getView().getNamaPlayer().setText(model.player.getNama());
            getView().getNamaPlayer().setVisible(true);
            getView().getValueHP().setText(model.player.getHP() +"");
            getView().getValueDef().setText(model.player.getDefense() + "");
            getView().getValueAtk().setText(model.player.getAttack_point() + "");
            getView().getStage().setSelectedIndex(1);
        }
    }
    
    //memilih class assassin
    public void buttonAssassin(){
        getView().setClassN(1);
        model.player.tipeClass(getView().getClassN());
        getView().getChoosenClass().setText(model.player.getPlayerClass().toString());
        getView().getStage().setSelectedIndex(4);
    }
    
    //memilih class guardian
    public void buttonGuardian(){
        getView().setClassN(2);
        model.player.tipeClass(getView().getClassN());
        getView().getChoosenClass().setText(model.player.getPlayerClass().toString());
        getView().getStage().setSelectedIndex(4);
    }
    
    //memilih class necromancer
    public void buttonNecromancer(){
        getView().setClassN(3); 
        model.player.tipeClass(getView().getClassN());
        getView().getChoosenClass().setText(model.player.getPlayerClass().toString());
        getView().getStage().setSelectedIndex(4);
    }
    
    //memilih class archer
    public void buttonArcher(){
        getView().setClassN(4);
        model.player.tipeClass(getView().getClassN());
        getView().getChoosenClass().setText(model.player.getPlayerClass().toString());
        getView().getStage().setSelectedIndex(4);
        
    }
    
    //mengganti semua attribute di label HP, def, dan atk di GUI
    public void ChangeAttr(){
        ChangeHP();
        ChangeDef();
        ChangeAtk();
    }
    
    // setlabel HP di GUI
    public void ChangeHP(){
        getView().getValueHP().setText(model.player.getHP() + "");
    }
    
    //set label Def di GUI
    public void ChangeDef(){
        getView().getValueDef().setText(model.player.getDefense() +"");
    }
    
    //set label Atk di GUI
    public void ChangeAtk(){
        getView().getValueAtk().setText(model.player.getAttack_point() + "");
    }
    
    //confirm class yang dipilih
    public void yesclassbutton(){
        getView().getEquipment().EquipmentInit(model.player);
        getView().getEquipment().EquipmentsetforClass(model.player);
        model.player.setBaseHP(model.player.getHP());
        model.player.setBasedef(model.player.getDefense());
        model.player.setBaseatk(model.player.getAttack_point());
        model.player.setMaxHP();
        ChangeAttr();
        model.npc.reward1.EquipmentInit(model.player);
        model.npc.reward2.EquipmentInit(model.player);
        model.npc.setReward();
        setOpsiName();
        getView().getAlur().run(model.enemy);
        if (getView().getAlur().isBattle() == true){
            getView().getStage().setSelectedIndex(2);
        }else{
            getView().getStage().setSelectedIndex(5);
        }
        getView().getAlur().setCurrentInteraction(getView().getAlur().getCurrentInteraction() + 1);
    }
    public void noclassbutton(){
        getView().getStage().setSelectedIndex(1);
    }
    public void winorlosebox(){
        Timer();
    }
    public void statestagechange(){
        if (getView().getAlur().isBattle() == true){
            getView().getAttack_button().setVisible(true);
            if(getView().isWin()){
                model.enemy = Stage.EnemyGoing();
            }
            if (model.enemy.getMusuh() != Enemy.Enemies.Dragoon) {
                getView().getEnemyLabel().setText(model.enemy.getMusuh().toString() + " (" + model.enemy.getType() + ")");
            } else {
                getView().getEnemyLabel().setText(model.enemy.getMusuh().toString());
            }
            getView().getHPMusuh().setMaximum(model.enemy.getHP());
            getView().getHPMusuh().setValue(model.enemy.getHP());
        }else{
            getView().getAttack_button().setVisible(false);
        }

        if(!view.isWin()){
            getView().getAttack_button().setVisible(false);
        }
    }
    public void hpmusuhstatechange(){
        
        getView().getHPMusuh().setValue(model.enemy.getHP());
//        if(percentagehp <= 100 && percentagehp >=50){
//            //do logic
//            view.getHPMusuh().setBackground(Color.green);
//        }else{
//            view.getHPMusuh().setBackground(Color.red);
//        }
    }
    
    // melakukan restart di panel win
    public void RestartWin(){
        GUI.absorbHP = 0;
        GUI.absorbdef = 0;
        GUI.absorbatk = 0;
        getView().getWinorlose().setVisible(false);
        getView().getAlur().setCurrentInteraction(0);
        getView().getStage().setSelectedIndex(0);
        getView().getGetNamePlayer().setText("");
        getView().getNamaPlayer().setText("Player");
        getView().model.clear();
        Model.setScores(0);
    }
    
    // melakukan restart di panel lose
    public void RestartLose(){
        GUI.absorbHP = 0;
        GUI.absorbdef = 0;
        GUI.absorbatk = 0;
        getView().getWinorlose().setVisible(false);
        getView().setWin(true);
        getView().getAlur().setCurrentInteraction(0);
        getView().getStage().setSelectedIndex(0);
        getView().getGetNamePlayer().setText("");
        getView().getNamaPlayer().setText("Player");
        getView().model.clear();
        Model.setScores(0);
    }
    
    // kembali ke menu di panel win
    public void MenuWin(){
        getView().getWinorlose().setVisible(false);
        getView().getAlur().setCurrentInteraction(0);
        getView().setVisible(false);
        getView().getStage().setSelectedIndex(0);
        menu.setVisible(true);
        getView().getGetNamePlayer().setText("");
        getView().getNamaPlayer().setText("Player");
        getView().model.clear();
        Model.setScores(0);
    }
    
    // kembali ke menu di panel lose
    public void MenuLose(){
        getView().getWinorlose().setVisible(false);
        getView().getAlur().setCurrentInteraction(0);
        getView().setVisible(false);
        getView().setWin(true);
        getView().getStage().setSelectedIndex(0);
        menu.setVisible(true);
        getView().getGetNamePlayer().setText("");
        getView().getNamaPlayer().setText("Player");
        getView().model.clear();
        Model.setScores(0);
    }
    
    // ------------- Bagian GUI Main Menu--------//
    
    // memasukan score player di database ke scoreboard
    
    // pelengkap GUI
    public void mousePlayenter(){
        menu.getPlay_button().setBackground(Color.GRAY);
    }
    public void mouseScoreenter(){
        menu.getScoreBoard_button().setBackground(Color.GRAY);
    }
    public void mouseExitEnter(){
        menu.getExit_Button().setBackground(Color.GRAY);
    }
    public void mousePlayexit(){
        menu.getPlay_button().setBackground(Color.white);
    }
    public void mouseScoreexit(){
        menu.getScoreBoard_button().setBackground(Color.white);
    }
    public void mouseExitexit(){
        menu.getExit_Button().setBackground(Color.white);
    }
    
    // masuk ke game ketika klik play
    public void Playclicked(){
        this.view = getView();
        menu.setVisible(false);
        getView().setVisible(true);
    }
    
    // menampilkan dialog scoreboard
    public void Scoreclicked(){
        SCBD.setVisible(true);
    }
    
    // exit game di menu
    public void Exitclicked(){
        int pilih = JOptionPane.showConfirmDialog(getView(), 
                "Apakah Anda Yakin ingin Keluar??", 
                "Konfirmasi", 
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        if(pilih == JOptionPane.OK_OPTION){
            menu.dispose();
        }
    }
    
    // ---------- Bagian JDialog ScoreBoard ----------- //
    
    // tutup dialog scoreboard
    public void closeScore(){
        SCBD.dispose();
    }

    public static GUI getView() {
        return view;
    }
}
