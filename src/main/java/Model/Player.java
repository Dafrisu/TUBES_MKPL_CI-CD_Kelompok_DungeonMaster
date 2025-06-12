/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author haika
 */
import VIew.GUI;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Entity implements Class, Actions{
    private String nama;
    private String skills;
    private ClassSet Class;
    private int baseHP = 50;
    private int basedef = 30;
    private int baseatk = 30;
    private Random random = new Random();
    private int MaxHP;
    private int MaxDef;
    private int MaxAtk;
    public Equipment wear = new Equipment();
    private int damage;
    public Player(String nama,int HP, int def, int atk ){
        super(HP,def,atk);
        this.nama = nama;
        this.MaxHP = HP;
    }
    public void MergeStat() {
        super.setHP(baseHP + Class.getHp());
        super.setAttack_point(basedef + Class.getAtk());
        super.setDefense(baseatk + Class.getDef());
//        baseHP = super.getHP();
//        baseatk = super.getAttack_point() ;
//        basedef = super.getDefense() ;

    }
    
    public enum ClassSet{
        Assassin( generateRandom(100,125), 45, 200),
        Guardian( generateRandom(175, 250), 300, 70),
        Necromancer(generateRandom(150,200),60,100),
        Archer( generateRandom(60,80), 50, 250);
        
        private int hp;
        private int def;
        private int atk;
        
        ClassSet(int hp, int def, int atk){
            this.hp = hp;
            this.def = def;
            this.atk = atk;
        }

        public int getHp() {
            return hp;
        }

        public int getDef() {
            return def;
        }

        public int getAtk() {
            return atk;
        }
        private static int generateRandom(int min, int max) {
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        }
    }
   
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public void tipeClass(int choose) {
        if (choose == 1){
            this.setPlayerClass(ClassSet.Assassin);
        }else if (choose == 2){
            this.setPlayerClass(ClassSet.Guardian);
        }else if(choose == 3){
            this.setPlayerClass(ClassSet.Necromancer);
        }else if(choose == 4){
            this.setPlayerClass(ClassSet.Archer);
        }else{
            System.out.println("input invalid");
        }
    }

    @Override
    public void Skillset() {
        if (this.getPlayerClass().toString().equals("Assassin")){
            this.setSkills("Assassin_Skillset");
        }else if (this.getPlayerClass().toString().equals("Guardian")){
            this.setSkills("Guardian_Skillset");
        }else if(this.getPlayerClass().toString().equals("Necromancer")){
            this.setSkills("Necromancer_Skillset");
        }else if(this.getPlayerClass().toString().equals("Archer")){
            this.setSkills("Archer_Skillset");
        }else{
            System.out.println("Player belum punya Class");
        }
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    

    
    public void CheckStats(){
        System.out.println("HP\t:" + this.getHP());
        System.out.println("Def\t:" + this.getDefense());
        System.out.println("Attack\t:" + this.getAttack_point());
    }
    public void checkEquipment(){
        System.out.println("Tipe Equipment\t:" + this.wear.getTipe());
        System.out.println("Nama Set\t\t:" + this.wear.getNama());
        System.out.println("HP\t\t:" + this.wear.getEquipment_HP());
        System.out.println("Def\t\t:" + this.wear.getEquipment_def());
        System.out.println("Attack\t\t:" + this.wear.getEquipment_att());
    }
    @Override
    public void attack(Entity a) {
        int reduce = a.getDefense() * 20/100;
        
        if (super.persepective(this) == true){
            a.setHP(a.getHP() - (this.getAttack_point() - reduce));
            setDamage(this.getAttack_point() - reduce);
        }
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public void setMaxHP() {
        this.MaxHP = this.baseHP + wear.getEquipment_HP() + (GUI.absorbHP);
    }

    public int getBaseHP() {
        return baseHP;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the Class
     */
    public ClassSet getPlayerClass() {
        return Class;
    }

    /**
     * @param Class the Class to set
     */
    public void setPlayerClass(ClassSet Class) {
        this.Class = Class;
        MergeStat();
    }  

    /**
     * @param baseHP the baseHP to set
     */
    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    /**
     * @return the basedef
     */
    public int getBasedef() {
        return basedef;
    }

    /**
     * @param basedef the basedef to set
     */
    public void setBasedef(int basedef) {
        this.basedef = basedef;
    }

    /**
     * @return the baseatk
     */
    public int getBaseatk() {
        return baseatk;
    }

    /**
     * @param baseatk the baseatk to set
     */
    public void setBaseatk(int baseatk) {
        this.baseatk = baseatk;
    }

    /**
     * @return the MaxDef
     */
    public int getMaxDef() {
        return MaxDef;
    }

    /**
     * @return the MaxAtk
     */
    public int getMaxAtk() {
        return MaxAtk;
    }

    /**
     * @param MaxDef the MaxDef to set
     */
    public void setMaxDef(int MaxDef) {
        this.MaxDef = basedef + wear.getEquipment_def();
    }

    /**
     * @param MaxAtk the MaxAtk to set
     */
    public void setMaxAtk(int MaxAtk) {
        this.MaxAtk = baseatk + wear.getEquipment_att();
    }
    
}
