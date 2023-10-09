import java.util.ArrayList;
import java.util.Random;


public abstract class BattleLoc extends Location {
    private Monster monster;
    private String award;
    private int awardId;
    private int maxMonster;

    public BattleLoc(Player player, String name, Monster monster, String award, int awardId, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.awardId=awardId;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int mnstrNumber = this.randomMonsterNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + mnstrNumber + " tane " + this.getMonster().getName() + " yaşıyor !");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.next() + input.nextLine();//Bir sonraki scanner sıfırlamış oluruz !!!
        selectCase =selectCase.toUpperCase();
        if (selectCase.equals("S") && (combat(mnstrNumber))) {
            System.out.println(this.getName() + " tüm düşmanları yendiniz !");
            this.getPlayer().getInventory().getAwards()[this.awardId] = 1;//Gelen index değerlerini hepsini 1 e eşitliyorum...
            return true;
        }
        if (this.getPlayer().getHealth() == 0) {
            System.out.println("Öldünüz.");
            return false;
        }

        return true;
    }

    public boolean combat(int mnstrNumber) {

        for (int i = 1; i <= mnstrNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getOrjinalHealth());
            playarStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals((("V")))) {

                    if(Math.random()<=0.5) {
                        System.out.println("Siz vurdunuz ! ");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                    else {
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();

                        }
                    }

                } else {
                    return false;
                }
            }

            if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz ! ");
               // if(this.getMonster().getName()=="Yılan"){

               // }else{

                    this.monster.reward(this.getPlayer());
                    System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
               // }


            } else {
                return false;
            }

        }
        return true;
    }

    public void monsterStats(int i) {
        System.out.println(i + "." + this.getMonster().getName() + " Değerleri");
        System.out.println("--------------------------------");
        System.out.println("Sağlık : " + this.getMonster().getHealth());
        System.out.println("Hasar : " + this.getMonster().getDamage());
        System.out.println("Ödül : " + this.getMonster().getAward());
        System.out.println();
    }
    public void playarStats() {
        System.out.println("Oyuncu değerleri");
        System.out.println("--------------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();
    }
    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " Canı : " + this.getMonster().getHealth());
        System.out.println("-----------------");
    }


    public int randomMonsterNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }
}
