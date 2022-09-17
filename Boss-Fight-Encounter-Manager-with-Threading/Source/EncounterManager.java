import java.util.Scanner;

/**
 * Managing the encounter
 */
public class EncounterManager {
    static private EncounterManager singleObject;
    private Tank tank = new Tank("Tank", 1, 100, 10, 6);
    private DamageDealer damageDealer = new DamageDealer("DamageDealer", 2, 100, 10, 7);
    private Healer healer = new Healer("Healer", 3, 100, 10, 8);
    private EnemyEntity enemy = new EnemyEntity(4, 1000, 10);

    private EncounterManager() {
    }

    ;

    /**
     * Creating a single object for Encounter Manager to make it singleton class
     *
     * @return that object
     */
    public static synchronized EncounterManager getInstance() {
        if (singleObject == null) {
            singleObject = new EncounterManager();
        }
        return singleObject;
    }

    public static void main(String[] args) {
        EncounterManager myEncounterManager = EncounterManager.getInstance();
        myEncounterManager.tank.start();
        myEncounterManager.damageDealer.start();
        myEncounterManager.healer.start();
        myEncounterManager.enemy.start();
    }

    /**
     * Creating a menu for the encounter
     */
    public void menu() {
        Scanner in = new Scanner(System.in);
        System.out.println("-".repeat(41) + "\n WELCOME TO BOSS-FIGHT ENCOUNTER MANAGER\n" + "-".repeat(41));
        int option, entityID = 0;
        do {
            System.out.println("""
                    MENU
                    	1) Register entities
                    	2) Start encounter
                    	3) Exit""");
            System.out.print("> ");
            option = in.nextInt();
            if (option > 3 || option < 1) {
                System.out.println("Invalid option selection! Please re-enter.\n");
            } else {
                char entityOption;
                if (option == 1) {
                    do {
                        System.out.println("""
                                SELECT AN ENTITY TO REGISTER
                                	a) Tank
                                	b) Damage Dealer
                                	c) Healer
                                	d) Enemy""");
                        System.out.print("> ");
                        entityOption = in.next().charAt(0);
                        entityID++;
                        switch (entityOption) {
                            case 'a' -> registerTank(entityID);
                            case 'b' -> registerDamageDealer(entityID);
                            case 'c' -> registerHealer(entityID);
                            case 'd' -> spawnEnemy(entityID);
                            case 'A', 'B', 'C', 'D' -> {
                                System.out.println("Selection is case-sensitive! Please re-enter.");
                                entityOption = '0';
                            }
                            default -> {
                                System.out.println("Invalid entity selection! Please re-enter.");
                                entityOption = '0';
                            }
                        }
                        if (entityOption != '0') {
                            char continueReg;
                            do {
                                System.out.println("Would you like to continue the registration process? (y)es/(n)o");
                                System.out.print("> ");
                                continueReg = in.next().charAt(0);
                                if (continueReg != 'y' && continueReg != 'n') {
                                    System.out.println("Invalid answer! Please only write (y)es/(n)o.");
                                    continueReg = '0';
                                }
                                if (continueReg == 'y') {
                                    entityOption = '1';
                                }
                            } while (continueReg == '0');
                        }
                    } while (entityOption == '0' || entityOption == '1');
                }
                if (option == 2) {
                    System.out.println("THE ENCOUNTER HAS STARTED!");
                    do {
                        System.out.println("\n" + "=".repeat(20) + "\nENTITIES'S HP");
                        System.out.printf("""
                                Tank: %d
                                Damage Dealer: %d
                                Healer: %d
                                Enemy: %d
                                """, this.tank.getHealthPoints(), this.damageDealer.getHealthPoints(), this.healer.getHealthPoints(), this.enemy.getHealthPoints());
                        System.out.println("=".repeat(20));
                        System.out.println("""
                                a) Player attack
                                b) Player heal
                                c) Enemy attack
                                d) Enemy group-wide attack
                                e) Stop the encounter""".indent(4));
                        System.out.print("> ");
                        entityOption = in.next().charAt(0);
                        switch (entityOption) {
                            // case 'a' -> playerAttack();
                            case 'b' -> healPlayer();
                            // case 'c' -> enemyAttack();
                            case 'd' -> groupWideAttack();
                            case 'e' -> entityOption = '0';
                            case 'A', 'B', 'C', 'D', 'E' -> {
                                System.out.println("Selection is case-sensitive! Please re-enter.");
                                entityOption = '0';
                            }
                            default -> {
                                System.out.println("Invalid entity selection! Please re-enter.");
                                entityOption = '0';
                            }
                        }
                    } while (entityOption != '0' && enemyIsAlive() && playersAreAlive());
                    if (!enemyIsAlive()) {
                        System.out.println("The enemy is dead. The encounter has ended.");
                        System.out.println("If you want to continue playing the game, you should start by registering entities!\n");
                    }

                    if (!playersAreAlive()) {
                        System.out.println("All players are dead. The encounter has ended.");
                        System.out.println("If you want to continue playing the game, you should start by registering entities!\n");
                    }
                }
                if (option == 3) {
                    System.out.println("\nThanks for playing the game." + " BYE!");
                    break;
                }
            }
        } while ((option <= 3 && option >= 1));
    }

    /**
     * Creating a tank entity
     *
     * @param id unique identifier for tank entity
     */
    public void registerTank(int id) {
        Scanner in = new Scanner(System.in);
        int healthPoints, baseDamage, defense;

        System.out.println("TANK'S" + "\nHealth points:");
        healthPoints = in.nextInt();
        System.out.println("Base damage:");
        baseDamage = in.nextInt();
        System.out.println("Defense value:");
        defense = in.nextInt();
        this.tank = new Tank("Tank", id, healthPoints, baseDamage, defense);

    }

    /**
     * Creating a damage dealer entity
     *
     * @param id unique identifier for damage dealer entity
     */
    public void registerDamageDealer(int id) {
        Scanner in = new Scanner(System.in);
        int healthPoints, baseDamage, intelligence;
        System.out.println("DAMAGE DEALER'S" + "\nHalth points:");
        healthPoints = in.nextInt();
        System.out.println("Base damage:");
        baseDamage = in.nextInt();
        System.out.println("Intelligence value:");
        intelligence = in.nextInt();
        this.damageDealer = new DamageDealer("Damage Dealer", id, healthPoints, baseDamage, intelligence);
    }

    /**
     * Creating a healer entity
     *
     * @param id unique identifier for healer entity
     */
    public void registerHealer(int id) {
        Scanner in = new Scanner(System.in);
        int healthPoints, baseDamage, mind;
        System.out.println("HEALER's" + "\nHealth points:");
        healthPoints = in.nextInt();
        System.out.println("Base damage:");
        baseDamage = in.nextInt();
        System.out.println("Mind value:");
        mind = in.nextInt();
        this.healer = new Healer("Healer", id, healthPoints, baseDamage, mind);
    }

    /**
     * Creating an enemy entity
     *
     * @param id unique identifier for enemy entity
     */
    public void spawnEnemy(int id) {
        Scanner in = new Scanner(System.in);
        int healthPoints, baseDamage;
        System.out.println("ENEMY'S" + "\nHealth points:");
        healthPoints = in.nextInt();
        System.out.println("Base damage:");
        baseDamage = in.nextInt();
        this.enemy = new EnemyEntity(id, healthPoints, baseDamage);
    }

    /**
     * The tank player or damage dealer attack the enemy boss
     *
     * @param role is to distinguish the attacking player type
     */
    public synchronized void playerAttack(char role) {
        if (role == 't') {
            if (tank.getHealthPoints() == 0) {
                System.out.println("Tank is already dead!");
            } else {
                System.out.printf("Tank attacked the enemy (%d damage attack)", tank.dealDamage());
                this.enemy.takeDamage(tank.dealDamage());
            }
        }
        if (role == 'd') {
            if (damageDealer.getHealthPoints() == 0) {
                System.out.println("Damage dealer is already dead!");
            } else {
                System.out.printf("Tank attacked the enemy (%d damage attack)", damageDealer.dealDamage());
                this.enemy.takeDamage(damageDealer.dealDamage());
            }
        }
        displayEntities();
    }

    /**
     * Checking whether enemy is dead or alive
     *
     * @return true if the enemy is still alive, otherwise returns false
     */
    public boolean enemyIsAlive() {
        return this.enemy.getHealthPoints() > 0;
    }

    /**
     * Checking whether all the players are alive or not
     *
     * @return true if all the players are alive, otherwise returns false
     */
    public boolean playersAreAlive() {
        return (this.tank.getHealthPoints() > 0 || this.damageDealer.getHealthPoints() > 0 || this.healer.getHealthPoints() > 0);
    }

    /**
     * Player who has the smallest health point gets healed up to 100 based on the healer's mind value
     */
    public synchronized void healPlayer() {
        Player player = tank;
        int heal = healer.heal();
        int healPoint, prevHP;

        if (damageDealer.getHealthPoints() > 0 && damageDealer.getHealthPoints() <= player.getHealthPoints() && damageDealer.getHealthPoints() <= healer.getHealthPoints() ) {
            player = damageDealer;
        }
        else if (healer.getHealthPoints() > 0 && healer.getHealthPoints() <= player.getHealthPoints() && healer.getHealthPoints() <= damageDealer.getHealthPoints() ) {
            player = healer;
        }

        if (player.getRole().equals("Tank")) {
            healPoint = heal + tank.getHealthPoints();
            prevHP = tank.getHealthPoints();
            if (prevHP == 0) {
                System.out.println("Tank is already dead!");
            } else {
                if (prevHP == 100) {
                    System.out.print("Tank cannot get more heal!");
                } else if (healPoint > 100) {
                    tank.setHealthPoints(100);
                    System.out.printf("The tank was healed by %d HP", 100 - prevHP);
                } else {
                    tank.setHealthPoints(healPoint);
                    System.out.printf("The tank was healed by %d HP", heal);
                }
            }
        } else if (player.getRole().equals("DamageDealer")) {
            healPoint = heal + damageDealer.getHealthPoints();
            prevHP = damageDealer.getHealthPoints();
            if (prevHP == 0) {
                System.out.println("Damage dealer is already dead!");
            } else {
                if (prevHP == 100) {
                    System.out.print("Damage dealer cannot get more heal!");
                } else if (healPoint > 100) {
                    damageDealer.setHealthPoints(100);
                    System.out.printf("The damage dealer was healed by %d HP", 100 - prevHP);
                } else {
                    damageDealer.setHealthPoints(healPoint);
                    System.out.printf("The damage dealer was healed by %d HP", heal);
                }
            }
        } else {
            healPoint = heal + healer.getHealthPoints();
            prevHP = healer.getHealthPoints();
            if (prevHP == 0) {
                System.out.println("Healer is already dead!");
            } else {
                if (prevHP == 100) {
                    System.out.print("Healer cannot get more heal!");
                } else if (healPoint > 100) {
                    healer.setHealthPoints(100);
                    System.out.printf("The healer was healed by %d HP", 100 - prevHP);
                } else {
                    healer.setHealthPoints(healPoint);
                    System.out.printf("The healer was healed by %d HP", heal);
                }
            }
        }
        displayEntities();
    }

    /**
     * Enemy boss attacks to the tank player
     */
    public synchronized void enemyAttack(char role) {
        int healthPoint;
        if (role == 't') {
            healthPoint = tank.getHealthPoints();
            if (healthPoint == 0) {
                System.out.println("Tank is already dead!");
            } else {
                System.out.printf("Enemy attacked the tank (%d damage attack)", tank.dealDamage());
                this.tank.takeDamage(enemy.dealDamage());
            }
        }
        displayEntities();
    }

    /**
     * Enemy boss attacks all players
     */
    public synchronized void groupWideAttack() {
        int damageReceived = enemy.dealDamage();
        this.tank.takeDamage(damageReceived);
        this.damageDealer.takeDamage(damageReceived);
        this.healer.takeDamage(damageReceived);
        System.out.printf("Enemy attacked all players (%d damage attack)", damageReceived);
        displayEntities();
    }

    /**
     * Displays the up-to-date health points of all players and enemy boss
     */
    public void displayEntities() {
        System.out.println("\n" + "=".repeat(20) + "\nENTITIES'S HP");
        System.out.printf("""
                Tank: %d
                Damage Dealer: %d
                Healer: %d
                Enemy: %d
                """, this.tank.getHealthPoints(), this.damageDealer.getHealthPoints(), this.healer.getHealthPoints(), this.enemy.getHealthPoints());
        System.out.print("=".repeat(20) + "\n");
        if (!enemyIsAlive()) {
            System.out.print("The enemy is dead. The encounter has ended.");
        }
        if (!playersAreAlive()) {
            System.out.print("All players are dead. The encounter has ended.");
        }
    }
}