import java.util.Random;

public class Snake extends Monster {
    public Snake() {
        super(4, "Yılan", snakeDamage(), 12, 4);
    }

    private static int snakeDamage() {
        Random r = new Random();
        int max = 6;
        int min = 3;
        return r.nextInt((max - min) + min);

    }
    @Override
    public void reward (Player player) {

        Random r = new Random();
        int a = r.nextInt(100);

        if(a<=15){
            Armor[] allArmors = Armor.armors();
            Armor randomArmor;

            a = r.nextInt(100);

            if(a<=20){
                randomArmor=allArmors[2];
            }
            else if(a<=50){
                randomArmor=allArmors[1];
            }
            else{
                randomArmor=allArmors[0];
            }
            System.out.println(randomArmor.getName()+" zırhını kazandınız. Envanterinize eklendi.");
            player.getInventory().setArmor(randomArmor);
        }
        else if (a<=30){

            Weapon[] allWeapons = Weapon.weapons();
            Weapon randomWeapon;
            a = r.nextInt(100);

            if(a<=20){
                randomWeapon=allWeapons[2];
            }
            else if(a<=50){
                randomWeapon=allWeapons[1];
            }
            else{
                randomWeapon=allWeapons[0];

            }
            System.out.println(randomWeapon.getName()+" silahını kazandınız. Envanterinize eklendi.");
            player.getInventory().setWeapon(randomWeapon);

        }
        else if (a<=55){
            Money[] allMoney = Money.moneys();
            Money randomMoney ;
            a = r.nextInt(100);

            if(a<=20){
                randomMoney=allMoney[2];
            }
            else if(a<=50){
                randomMoney=allMoney[1];
            }
            else{
                randomMoney=allMoney[0];

            }
            System.out.println(randomMoney.getMoney()+" para kazandınız. Envanterinize eklendi.");
            player.setMoney(player.getMoney() + randomMoney.getMoney());
        }
        else{
            System.out.println("Hiçbir şey kazanmadınız . ");
        }
    }

}
