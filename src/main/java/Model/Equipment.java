/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author haika
 */
public class Equipment{
    private int Equipment_att =0;
    private int Equipment_HP =0;
    private int Equipment_def =0;
    private String tipe;
    private String nama;
    public Equipment[] arrEquipment = new Equipment[10];
    
    public Equipment(){
        
    }
    
    public Equipment(String tipe, String nama,int att, int HP, int def) {
        this.Equipment_HP = HP;
        this.Equipment_att = att;
        this.Equipment_def = def;
        this.tipe = tipe;
        this.nama = nama;
    }
    
    public void EquipmentInit(Player a){
         if (a.getPlayerClass() == Player.ClassSet.Assassin){
            this.setTipe("Dagger and Assasin's robe");
            arrEquipment[0] = new Equipment("Sickle and Assasin's robe", "Set1", 50,20,20 );
            arrEquipment[1] = new Equipment("Blink Dagger and Assasin's Cloak", "Set2", 120,40,90 );
            arrEquipment[2] = new Equipment("Corossive Rapier and Stealth Cloak ", "Set3", 180,60,110);
            arrEquipment[3] = new Equipment("Dragon Claw Dagger and Grim Reaper's Cloak", "Set4", 250,80,100);
        }else if (a.getPlayerClass() == Player.ClassSet.Guardian){
            this.setTipe("Shield and Guardians Armor");
            arrEquipment[0] = new Equipment("Wooden Shield and Broken Guardian Armor", "Set1", 10, 50, 50 );
            arrEquipment[1] = new Equipment("Iron Shield and Chainmail Armor", "Set2", 30,100, 80 );
            arrEquipment[2] = new Equipment("Holy Bulwark and Roaring Pyshical Armor", "Set3", 80,180,120);
            arrEquipment[3] = new Equipment("Sun Realm Shield and Dim Tree Spirit's Armor", "Set4", 150,250,200);
        }else if(a.getPlayerClass() == Player.ClassSet.Necromancer){
            this.setTipe("getPlayerClass and robe");
            arrEquipment[0] = new Equipment("Wand and Torn robe", "Set1", 60,50, 20 );
            arrEquipment[1] = new Equipment("Hogwart Wand and Invisible Cloak", "Set2", 100,80, 30);
            arrEquipment[2] = new Equipment("Deathspeaker's Staff and Soul Weaver Robe", "Set3", 150,140,50);
            arrEquipment[3] = new Equipment("Shadowbinder's Rod and Shadowbinder's Robe", "Set4", 220,300,100);
        }else if(a.getPlayerClass() == Player.ClassSet.Archer){
            this.setTipe("Bow and robe");
            arrEquipment[0] = new Equipment("Eagle Eye Bow and Wildwood Tunic", "Set1", 60,10, 30);
            arrEquipment[1] = new Equipment("Lionheart Bow and Ranger's Mantle", "Set2", 120,20, 60);
            arrEquipment[2] = new Equipment("Forest Sentinel Bow and Forest Warden Robe", "Set3", 50,120,200);
            arrEquipment[3] = new Equipment("Hunter's Mark and Swiftstrider Robe", "Set4", 60,150,270);
        }else{
            System.out.println("Player belum memiliki class");
        }
    }
    
    public void EquipmentsetforClass(Player a){
        a.wear = arrEquipment[0];
        a.setAttack_point(a.getAttack_point() + arrEquipment[0].getEquipment_att());
        a.setHP(a.getHP() + arrEquipment[0].getEquipment_HP());
        a.setDefense(a.getDefense()+ arrEquipment[0].getEquipment_def());
    }
    public void changeEquipment(Player a, Equipment item){
        a.setAttack_point(a.getAttack_point() - a.wear.getEquipment_att());
        a.setHP(a.getHP() - a.wear.getEquipment_HP());
        a.setDefense(a.getDefense()- a.wear.getEquipment_def());
        
        a.wear = item;
        a.setAttack_point(a.getAttack_point() + a.wear.getEquipment_att());
        a.setHP(a.getHP() + a.wear.getEquipment_HP());
        a.setDefense(a.getDefense()+ a.wear.getEquipment_def());
    }

    public int getEquipment_att() {
        return Equipment_att;
    }

    public void setEquipment_att(int Equipment_att) {
        this.Equipment_att = Equipment_att;
    }

    public int getEquipment_HP() {
        return Equipment_HP;
    }

    public void setEquipment_HP(int Equipment_HP) {
        this.Equipment_HP = Equipment_HP;
    }

    public int getEquipment_def() {
        return Equipment_def;
    }

    public void setEquipment_def(int Equipment_def) {
        this.Equipment_def = Equipment_def;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
}
