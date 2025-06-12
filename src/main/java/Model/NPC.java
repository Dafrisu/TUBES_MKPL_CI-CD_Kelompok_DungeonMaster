/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author daffa
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NPC extends Entity{
    private String nama;
    private ArrayList<String> listDialog = new ArrayList<>();
    private String[] dialogue = new String [10];
    private String npctype;
    private int idxDialogue;
    private Random intrandom = new Random();
    private int n;
    public Equipment reward1 = new Equipment();
    public Equipment reward2 = new Equipment();
    
    public NPC(int HP, int def, int att){
        super(HP,def,att);
        n = intrandom.nextInt(1,3);
        SetType();
        SetDiag();
    }


    public String getNama() {
        return nama;
    }


    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void DialogueOut(){
        for(int i =0; i<getDialogue().length; i++){
            if ( getDialogue()[i] != null){
                System.out.println(getDialogue()[i]);
            }
        }
    }

    public void SetDiag() {
        switch (this.getNpctype()) {
            case "Merchant":
                this.listDialog.add("Selamat datang petualang, Kamu orang yang beruntung menemukan tokoku");
                this.listDialog.add("Tertarik untuk melihat lihat?");
                this.listDialog.add("Pilih salah satu item ini petualang!");
                break;
            case "Pemberi Hadiah":
                this.listDialog.add("aah... petualang, Terimakasih sudah mau melindungi kota kami");
                this.listDialog.add("Aku memiliki sedikit hadiah untukmu, apakah kamu mau menerimanya?");
                this.listDialog.add("Apakah kamu akan menggunakan item ini petualang?");
                break;
            default:
                break;
        }
    }
    public void setReward(){
        if (this.getNpctype().equals("Merchant")){
            int n1, n2;
            n1 = intrandom.nextInt(0,4);
            n2 = intrandom.nextInt(0,4);
            this.reward1 = this.reward1.arrEquipment[n1];
            this.reward2 = this.reward2.arrEquipment[n2];
            this.listDialog.add("1. " +this.reward1.getTipe()+"("+ this.reward1.getNama()+")"+"\n "+ "2. " +this.reward2.getTipe()+"("+ this.reward2.getNama()+")");
        }else if (this.getNpctype().equals("Pemberi Hadiah")){
            int n1;
            n1 = intrandom.nextInt(0,4);
            this.reward1 = this.reward1.arrEquipment[n1];
            this.listDialog.add("Reward :" +this.reward1.getTipe()+"("+this.reward1.getNama()+")");
        }
    }
    public void pilihReward(Player a,int x){

        if (this.getNpctype().equals("Merchant")){
            if (x == 1){
                a.wear.changeEquipment(a, reward1);
            }else if(x ==2){
                a.wear.changeEquipment(a, reward2);
            }
        }else if (this.getNpctype().equals("Pemberi Hadiah")){
            if (x == 1){
                a.wear.changeEquipment(a, reward1);
            }else if(x ==2){
                System.out.println("Hadiah Ditolak");
            }
        }
    }
    //public int pilih (Player a){
        //Scanner input = new Scanner(System.in);
        //System.out.println("Masukkan Input");
        //int choose = input.nextInt();
        //if(choose == 1){
            //this.tipeReward(a,);
        //}else if (choose == 2){
            //next Interaction
        //}
    //}
    public void SetType(){
        n = this.n;
        if (n == 1){
            this.npctype = "Merchant";
        }else if (n ==2){
            this.npctype = "Pemberi Hadiah";
        }
    }

    public void Dialogue(Player a){
        SetType();
        this.SetDiag();
        this.DialogueOut();
    }

    /**
     * @return the dialogue
     */
    public String[] getDialogue() {
        return dialogue;
    }

    public ArrayList<String> getListDialog() {
        return listDialog;
    }

    public String getNpctype() {
        return npctype;
    }
    
}
