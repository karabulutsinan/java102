public class Money {
    private int money;

    public Money(int money) {
        this.money = money;
    }

    public static Money[] moneys(){
        Money[] moneyList=new Money[3];
        moneyList[0]= new Money(1);
        moneyList[1]= new Money(5);
        moneyList[2]= new Money(10);
        return moneyList;

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
