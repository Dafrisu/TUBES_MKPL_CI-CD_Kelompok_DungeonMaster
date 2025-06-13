/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Haikal
 */
public class PlayerTest {
    private Player player;
    public PlayerTest() {
    }
    
@BeforeEach
    public void setUp() {
        // Initialize a new Player object before each test
        // Initial HP, def, atk for the player's constructor
        player = new Player("TestPlayer", 100, 50, 50);
    }

    @Test
    public void testPlayerInitialization() {
        assertNotNull(player, "Player object should not be null after initialization.");
        assertEquals("TestPlayer", player.getNama(), "Player name should be 'TestPlayer'.");
        assertEquals(100, player.getMaxHP(), "Player initial MaxHP should be 100.");
        assertEquals(50, player.getDefense(), "Player initial Defense should be 50.");
        assertEquals(50, player.getAttack_point(), "Player initial Attack should be 50.");
        assertNotNull(player.wear, "Player should have an Equipment object.");
    }

    @Test
    public void testTipeClassAssassin() {
        player.tipeClass(1); // Choose Assassin
        assertNotNull(player.getPlayerClass(), "Player class should not be null after choosing.");
        assertEquals(Player.ClassSet.Assassin, player.getPlayerClass(), "Player class should be Assassin.");
        // After setting class, MergeStat is called, so check base stats + class stats
        // Assassin (hp: 100-125, def: 45, atk: 200)
        // Player base: HP 50, Def 30, Atk 30
        // Expected HP: 50 + (100 to 125) -> check range
        // Expected Def: 30 + 45 = 75
        // Expected Atk: 30 + 200 = 230
        assertTrue(player.getHP() >= (player.getBaseHP() + 100) && player.getHP() <= (player.getBaseHP() + 125), "Assassin HP should be within range.");
        assertEquals(player.getBasedef() + 45, player.getDefense(), "Assassin defense should be base + 45.");
        assertEquals(player.getBaseatk() + 200, player.getAttack_point(), "Assassin attack should be base + 200.");
    }

    @Test
    public void testTipeClassGuardian() {
        player.tipeClass(2); // Choose Guardian
        assertNotNull(player.getPlayerClass(), "Player class should not be null after choosing.");
        assertEquals(Player.ClassSet.Guardian, player.getPlayerClass(), "Player class should be Guardian.");
        // Guardian (hp: 175-250, def: 300, atk: 70)
        assertTrue(player.getHP() >= (player.getBaseHP() + 175) && player.getHP() <= (player.getBaseHP() + 250), "Guardian HP should be within range.");
        assertEquals(player.getBasedef() + 300, player.getDefense(), "Guardian defense should be base + 300.");
        assertEquals(player.getBaseatk() + 70, player.getAttack_point(), "Guardian attack should be base + 70.");
    }

    @Test
    public void testTipeClassInvalidInput() {
        player.tipeClass(99); // Invalid choice
        assertNull(player.getPlayerClass(), "Player class should be null for invalid input.");
        // Check if stats remained unchanged (or default)
        assertEquals(30, player.getBaseHP(), "HP should remain base (50) for invalid class choice.");
        assertEquals(30, player.getBasedef(), "Defense should remain base (30) for invalid class choice.");
        assertEquals(30, player.getBaseatk(), "Attack should remain base (30) for invalid class choice.");
    }

    @Test
    public void testSkillsetAssassin() {
        player.tipeClass(1); // Set class to Assassin
        player.Skillset();
        assertEquals("Assassin_Skillset", player.getSkills(), "Skills should be 'Assassin_Skillset'.");
    }
    
}
