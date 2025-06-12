/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author haika
 */
public class Enemy extends Entity implements Actions{
    private String nama;
    private Enemytype typeMusuh;
    private Enemies Musuh;
    private int pilih;
    private int maxHP;
    private int maxdef;
    private int maxatk;
    private Random random = new Random();
    private int damage;
    
    public Enemy (Enemies Musuh){
        super(Musuh.getHp(), Musuh.getDef(), Musuh.getAtk());
        if(Musuh == Enemies.Dragoon){
            this.Musuh = Musuh;
            this.maxHP = Musuh.getHp();
        }else{
            this.Musuh = Musuh;
            this.maxHP = Musuh.getHp();
            setTypeMusuh();
            Buff();
        }
    }
    
    
    public void Buff() {
        this.setHP(getMusuh().getHp()+ typeMusuh.getHp());
        this.setDefense(getMusuh().getDef()+ typeMusuh.getDef());
        this.setAttack_point(getMusuh().getAtk()+ typeMusuh.getAtk());
    }
    
    public enum Enemies{
        Slime( generateRandom(50,80), 80, 80),
        Goblin( generateRandom(70, 120), 100, 150),
        Rock_Giant(generateRandom(340,400),300,85),
        Dragoon( 1000, 300, 200);
        
        private int hp;
        private int def;
        private int atk;
        
        Enemies(int hp, int def, int atk){
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
    
    public enum Enemytype{
        Fighter( generateRandom(40,70), 30, 30),
        Elite( generateRandom(100, 150), 70, 90),
        Rogue(generateRandom(15,30),0,200);
        
        private int hp;
        private int def;
        private int atk;
        
        Enemytype(int hp, int def, int atk){
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

    public Enemytype getType() {
        return typeMusuh;
    }

    public void setTypeMusuh() {
        pilih = random.nextInt(1,3);
        if (pilih == 1) {
            typeMusuh = Enemytype.Elite;
        } else if (pilih == 2) {
            typeMusuh = Enemytype.Fighter;
        } else if (pilih == 3) {
            typeMusuh = Enemytype.Rogue;
        }
    }
    
    @Override
    public void attack(Entity Enemy) {
        int reduce = Enemy.getDefense() * 20/100;
        if (super.persepective(this) == false) {
            Enemy.setHP(Enemy.getHP() - (super.getAttack_point() - reduce));
            setDamage(this.getAttack_point() - reduce);
        }
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
     * @return the Musuh
     */
    public Enemies getMusuh() {
        return Musuh;
    }

    public int getMaxdef() {
        return maxdef;
    }

    public int getMaxatk() {
        return maxatk;
    }

    public int getMaxHP() {
        return maxHP;
    }
}
