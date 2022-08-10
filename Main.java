import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static Store store = new Store();
    static Cart cart = new Cart();

    public static void main(String[] args) {

        Item[][] inventory = new Item[][] {
                { new Item("Pepsi", 1.99), new Item("Crush", 1.99), new Item("Cola", 1.99) },
                { new Item("Honey Oats", 3.99), new Item("Fruit Loops", 1.99), new Item("Cheerios", 2.99) },
                { new Item("Milk", 4.99), new Item("Eggs", 0.99), new Item("Cheese", 1.89) },
                { new Item("Pepperoni", 2.99), new Item("Salami", 4.49), new Item("Mortadella", 4.99) },
                { new Item("Celery", 0.99), new Item("Spinach", 0.99), new Item("Coriander", 1.29) },
                { new Item("Shirt", 12.99), new Item("Pants", 24.99), new Item("Sweater", 18.99) },
                { new Item("Phone", 549.99), new Item("Printer", 349.99), new Item("Television", 1099) }
        };





        for (int i = 0; i < inventory.length; i++){
            for (int j = 0; j < inventory[i].length; j++){
                store.setItem(i, j, inventory[i][j]) ;
            }
        }

       manageItems();

    }

    public static void manageItems(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("\n\t******************************JAVA GROCERS******************************\n");
            System.out.println(store);


            System.out.println("Options: \n\ta) Add to cart\n\tb) Remove from cart \n\tc) Checkout");
            String response = scan.nextLine();


            switch (response){
                case "a":
                    System.out.print("\nChoose an aisle number between: 1 – 7: ");
                    int row = scan.hasNextInt() ? scan.nextInt() - 1 : 404;
                    scan.nextLine();
                    System.out.print("Choose an item number between: 1 – 3: ");
                    int column = scan.hasNextInt() ? scan.nextInt() - 1 : 404;
                    scan.nextLine();
                    Item item = store.getItem(row, column);
                    cart.add(item);

                    if (row == 404 || column == 404){
                        continue;
                    } else if (row < 0 || row > 6 || column < 0 || column > 2) {
                        continue;
                    }

                    if (!cart.add(item)){
                        System.out.println(item.getName() + " is already in your shopping cart.");
                    }else{
                        System.out.println(item.getName() + " was added to your shopping cart.");
                    }
                    break;


                case "b":

                    if (cart.isEmpty()) {
                        continue;
                    };

                    System.out.println("Enter the item you'd like to remove: ");
                    String name = scan.nextLine();
                    cart.remove(name);
                    break;


                case "c":

                    if (cart.isEmpty()){
                        continue;
                    }

                    System.out.println(cart.checkout());
                    scan.close();
                    return;

                default: continue;
            }

            System.out.println("\n\nSHOPPING CART\n\n" + cart);
            System.out.println("Enter anything to continue: ");
            scan.nextLine();



        }
    }


}
