public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        /*
        System.out.println(getPlayer().getInventory().getAwards()[0]);
        System.out.println(getPlayer().getInventory().getAwards()[1]);
        System.out.println(getPlayer().getInventory().getAwards()[2]);
        System.out.println(getPlayer().getInventory().getAwards()[3]);
        */

        if ((getPlayer().getInventory().getAwards()[0] + getPlayer().getInventory().getAwards()[1] + getPlayer().getInventory().getAwards()[2] + getPlayer().getInventory().getAwards()[3]) == 4) {
            System.out.println("Kazandınız !");
            return false;
        }
        System.out.println("Güvenli evdesiniz . ");
        System.out.println("Canınız yenilendi . ");
        this.getPlayer().setHealth(this.getPlayer().getOrjinalHealth());
        return true;


    }
}
