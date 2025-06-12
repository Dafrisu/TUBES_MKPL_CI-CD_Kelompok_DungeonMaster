/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.Random;
/**
 *
 * @author Mahesa
 */
public class Stage {
    private static int MAX_INTERACTIONS =11;
    private static int currentInteraction = 0;
    private boolean battle;
    
    Random random = new Random(); // Create a Random object
    
    public int getMAX_INTERACTIONS() {
        return MAX_INTERACTIONS;
    }

    public void setMAX_INTERACTIONS(int MAX_INTERACTIONS) {
        this.MAX_INTERACTIONS = MAX_INTERACTIONS;
    }

    public int getCurrentInteraction() {
        return currentInteraction;
    }

    public void setCurrentInteraction(int currentInteraction) {
        this.currentInteraction = currentInteraction;
    }
    
    public boolean isBattle() {
        return battle;
    }

    public void setBattle(boolean battle) {
        this.battle = battle;
    }
    public static Enemy EnemyGoing(){
        Enemy musuh = null;
        if( currentInteraction <3){
            musuh = new Enemy(Enemy.Enemies.Slime);
        }else if (currentInteraction >=3 && currentInteraction < 5){
            musuh = new Enemy(Enemy.Enemies.Goblin);
        }
        if(currentInteraction >=5 && currentInteraction <= 9){
            musuh = new Enemy(Enemy.Enemies.Rock_Giant);
        }else if (currentInteraction == MAX_INTERACTIONS - 1){
            musuh = new Enemy(Enemy.Enemies.Dragoon);
        }
        return musuh;
    }

    public void run(Enemy e) {
        int encounterType = random.nextInt(2)+1; // Generate random number (1 or 2)
        System.out.println(encounterType);
        if ( currentInteraction < MAX_INTERACTIONS - 1){
            
            if (encounterType == 1){
                setBattle(true);
            }
            else if (encounterType == 2){
                setBattle(false);
            }
        }else if(currentInteraction == MAX_INTERACTIONS -1){
            setBattle(true);
        }
        else{
            System.out.println("Udah habis");
            setBattle(false);
        }
    }

}