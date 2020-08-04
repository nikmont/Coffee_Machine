package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static Scanner sc = new Scanner(System.in);

    enum State {
        BUY("buy"),
        FILL("fill"),
        TAKE("take"),
        REMAINING("remaining"),
        EXIT("exit"),
        NaN("nan");

        String name;

        State(String name) {
            this.name = name;
        }
    }

    static State state = State.NaN;
    static boolean isBuy = false;
    static int numAction = 0;

    public static void changeState(String input) {
        for (State st : State.values()) {
            if (st.name.equals(input)) {
                if (st.name.equals("buy")) {
                    isBuy = true;
                }
                state = State.valueOf(input.toUpperCase());
                break;
            }
        }
        if (isBuy) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            changeCoffee(sc.nextLine());
            isBuy = false;
        }
    }

    public static void changeCoffee(String type) {
        if (type.equals("back")) {
            numAction = 4;
        } else numAction = Integer.parseInt(type);
    }

    public static void remainCoffe (int water, int milk, int coffee, int cups, int money) {
        System.out.println("The coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                coffee + " of coffee beans\n" +
                cups + " of disposable cups\n$" +
                money + " of money\n");
    }
    public static void main(String[] args) {

         int water = 400;
         int milk = 540;
         int coffee = 120;
         int cups = 9;
         int money = 550;
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            if (state.name.equals("nan")) {
                changeState(sc.nextLine());
                switch (state) {          //fill остается при пустом вводе//fill остается при пустом вводе
                    case BUY:
                        switch (numAction) {
                            case 1:
                                if (water >= 250 & coffee >= 16 & cups >= 1) {
                                    System.out.println("I have enough resources, making you a coffee!\n");
                                    water -= 250;
                                    coffee -= 16;
                                    --cups;
                                    money += 4;
                                } else System.out.println("Sorry, not enough water\n");
                                break;
                            case 2:
                                if (water >= 350 & milk >= 75 & coffee >= 20 & cups >= 1) {
                                    System.out.println("I have enough resources, making you a coffee!\n");
                                    water -= 350;
                                    milk -= 75;
                                    coffee -= 20;
                                    --cups;
                                    money += 7;
                                } else System.out.println("Sorry, not enough water\n");
                                break;
                            case 3:
                                if (water >= 200 & milk >= 100 & coffee >= 12 & cups >= 1) {
                                    System.out.println("I have enough resources, making you a coffee!\n");
                                    water -= 200;
                                    milk -= 100;
                                    coffee -= 12;
                                    --cups;
                                    money += 6;
                                } else System.out.println("Sorry, not enough water\n");
                                break;
                            case 4:
                                break;
                        }
                        state = State.NaN;
                        break;
                    case FILL:
                        System.out.println("Write how many ml of water do you want to add:");
                        water += sc.nextInt();
                        System.out.println("Write how many ml of milk do you want to add:");
                        milk += sc.nextInt();
                        System.out.println("Write how many grams of coffee beans do you want to add: ");
                        coffee += sc.nextInt();
                        System.out.println("Write how many disposable cups of coffee do you want to add:");
                        cups += sc.nextInt();
                        state = State.NaN;
                        break;
                    case TAKE:
                        System.out.println("\nI gave you $" + money + "\n");
                        money = 0;
                        state = State.NaN;
                        break;
                    case REMAINING:
                        remainCoffe(water, milk, coffee, cups, money);
                        state = State.NaN;
                        break;
                    case EXIT:
                        state = State.NaN;
                        return;
                }
            }
        }
    }
}
