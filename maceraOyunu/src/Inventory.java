public class Inventory {
    public Weapon weapon;
    public Armor armor;
    public Money money;

    private int[] awards = new int[]{-1, -1, -1, -1};



    public Inventory() {
        this.weapon = new Weapon("Yumruk ", -1, 0, 0);
        this.armor = new Armor(-1, "Paçavra", 0, 0);
        //this.money=money;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int[] getAwards() {
        return awards;
    }

    public void setAwards(int[] awards) {
        this.awards = awards;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}

