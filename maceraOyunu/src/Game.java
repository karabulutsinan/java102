import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hoşgeldiniz ! ");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " zorlu adaya hoşgeldiniz !");
        System.out.println("Burada yaşananların hepsi gerçek !!!");
        System.out.println("Lütfen oyuna başlamak için karakter seçiniz ! ");
        player.selectChar();

        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("############## Bölgeler ##############");
            System.out.println();
            System.out.println("1 - Güvenli Ev --> Burada düşman yok :)");
            System.out.println("2 - Eşya Dükkanı --> Silah veya Zırh alabilirsiniz.");
            System.out.println("3 - Mağara --> Ödül <yemek> , dikkatli ol karşına zombi çıkabilir ! ");
            System.out.println("4 - Orman --> Ödül <odun> ,dikkatli ol karşına vampir çıkabilir ! ");
            System.out.println("5 - Nehir --> Ödül <su>  , dikkatli ol ayı çıkabilir ! ");
            System.out.println("6 - Maden --> Ödül <zırh veya silah>  , dikkatli ol yılan çıkabilir ! ");

            System.out.println("0 - Çıkış Yap --> Oyunu sonlandır.");


            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    if (player.checkAwards(new Cave(player).getAwardId())) {
                        System.out.println("Buradaki ödülleri daha önce kazandınız ! ");
                        continue;
                    }
                    break;
                case 4:
                    location = new Forest(player);
                    if (player.checkAwards(new Forest(player).getAwardId())) {
                        System.out.println("Buradaki ödülleri daha önce kazandınız ! ");
                        continue;
                    }
                    break;
                case 5:
                    location = new River(player);
                    if (player.checkAwards(new River(player).getAwardId())) {
                        System.out.println("Buradaki ödülleri daha önce kazandınız ! ");
                        continue;
                    }
                    break;
                case 6 :
                    location= new Mine(player);
                    if (player.checkAwards(new Mine(player).getAwardId())) {
                        System.out.println("Buradaki ödülleri daha önce kazandınız ! ");
                        continue;
                    }
                    break;

                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz !");
            }
            if (location == null) {
                System.out.println("Bu zorlu adadan çabuk vazgeçtin kralım :P");
                break;
            }
            if (!location.onLocation()) {
                if ((player.getInventory().getAwards()[0] + player.getInventory().getAwards()[1] + player.getInventory().getAwards()[2] + player.getInventory().getAwards()[3]) == 4) {
                    break;
                }
                System.out.println("Game Over!");
                break;
            }
        }
    }
}
