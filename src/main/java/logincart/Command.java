package logincart;

import java.util.List;
import java.util.Scanner;

public class Command {

    public static final String LIST = "list";
    public static final String ADD = "add";
    public static final String DELETE = "del";
    public static final String LOGIN = "login";
    public static final String SAVE = "save";
    public static final String END = "end";
    public static final String USER = "user";
    public static final String LOAD = "load";
        
    private Repository repository;
    private Cart currCart;

    public Command(Repository repo) {
        this.repository = repo;
    }
   
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        //currCart = new Cart("geust");

        while (!stop) {
            System.out.println("> ");
            String input = scanner.nextLine();
            String[] terms = input.split(" ");
            switch (terms[0]) {
                /*case CARTS:
                    System.out.println("List of shopping carts");
                break;*/
                case LIST:
                    System.out.printf("Item in %s's cart\n",
                             currCart);
                    if (currCart.getContents().size() <= 0 ) {
                        System.out.println("your cart is empty");
                    }else {for(int i = 0; i < currCart.getContents().size(); i++) {
                        System.out.printf("%s. %d", (i+1), currCart.getContents().get(i));
                                }    
                            }
                break;

                case ADD:
                int before = currCart.getContents().size();
                for(int i = 1; i < terms.length; i++)
                    currCart.add(terms[i]);
                int addedCount = currCart.getContents().size() - before;
                System.out.printf("Added %d item to %s cart\n",
                        addedCount, currCart.getUsername());
                break;

                case DELETE:
                int idx = Integer.parseInt (terms[1]);
                String item = currCart.delete(idx);
                System.out.printf("Removed $s from %d's cart",
                         item, currCart.getUsername());
                break;

                case LOAD:
                currCart = repository.load(currCart.getUsername());
                System.out.printf("Loaded %s shoppimg cart. There are %s item \n",
                        currCart.getUsername(), currCart.getContents().size());
                break;

                case SAVE:
                repository.save(currCart);
                System.out.println("Save done!");
                break;

                case LOGIN:
                    currCart = new Cart(terms[1]);
                    System.out.printf("%s login ok", terms[1]);
                break;

                case USER:
                    List<String> allCarts = repository.getShoppingCarts();
                    if (repository.getShoppingCarts().size() <= 0){
                            System.out.println("no user profile found");
                    }else{ 
                        for (int i = 0; i < (((Repository) allCarts).getShoppingCarts().size());) {
                            System.out.println(((Repository) allCarts).getShoppingCarts().get(i));
                        }
                    }
                break;

                case END:
                stop = true;
                break;

                default:
                    System.out.printf("Unknow input: %s", terms[0]);
            }
            System.out.println("");
            scanner.close();

        }
        System.out.println("Thank you");
    }
        
}
