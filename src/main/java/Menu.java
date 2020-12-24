import dao.SalesDao;
import model.Sales;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Menu {
    private SalesDao salesDao;

    Menu(SalesDao salesDao) {
        this.salesDao = salesDao;
    }

    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";

    private void deleteSales() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID to delete sales record: ");
        Long id = scanner.nextLong();
        Boolean res = salesDao.deleteSales(id);
        if (res) {
            System.out.println(GREEN+"Successfully deleted sales records"+RESET);
        } else {
            System.out.println(RED+"Error in deleting sales records"+RESET);
        }
    }

    private void updateSales() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID: ");
        Long id = scanner.nextLong();

        System.out.print("Enter amount: ");
        int amount = scanner.nextInt();

        Boolean res = salesDao.updateSales(amount, id);
        if (res) {
            System.out.println(GREEN+"Successfully updated sales records"+RESET);
        } else {
            System.out.println(RED+"Error in updating sales records"+RESET);
        }
    }

    private void getSalesList() {
        try {
            List<Sales> salesList = salesDao.getSales();
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.printf("%-12s%-28s%-28s%-28s%-28s\n","ID","Name","Item","Date","Amount");
            System.out.println("----------------------------------------------------------------------------------------------");

            salesList.forEach(s -> {
                System.out.printf("%-12s",s.getId());
                System.out.printf("%-28s",s.getName());
                System.out.printf("%-28s",s.getItem());
                System.out.printf("%-28s",s.getDate());
                System.out.printf("%-28s",s.getAmount());
                System.out.println();
            });
            System.out.println("----------------------------------------------------------------------------------------------");

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void saveSales() {
        Sales sales = new Sales();
        Scanner scanner = new Scanner(System.in);
        String name,item;
        Date date = new Date();
        int amount;
        String info;

        do {
            System.out.print("Enter name: ");
            name = scanner.nextLine();

            System.out.print("Enter item name: ");
            item = scanner.nextLine();

            System.out.print("Enter amount: ");
            amount = scanner.nextInt();

            System.out.print("All information are correct (yes/no)?: ");
            scanner.nextLine(); // we have to do this because, nextInt() will not store 'new line' and thats why nextline autostore 'new line'
            info = scanner.nextLine();

        } while(!info.equalsIgnoreCase("yes"));

        if (!name.isEmpty() && !item.isEmpty() && amount > 0) {
            sales.setName(name);
            sales.setItem(item);
            sales.setDate(date);
            sales.setAmount(amount);

            salesDao.saveSales(sales);
        } else {
            System.out.println(RED + "--- Record not saved, Please try again ---" + RESET);
        }
    }

    void startMenu() {

        int menuOption;

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter menu options");
            System.out.println("1. Sales list");
            System.out.println("2. Save sales");
            System.out.println("3. Delete sales record (id)");
            System.out.println("4. Update sales record (id, amount)");
            System.out.println("0. Exit");
            System.out.print("options: ");

            menuOption = scanner.nextInt();
            switch (menuOption) {
                case 1:
                    getSalesList();
                    break;
                case 2:
                    saveSales();
                    break;
                case 3:
                    deleteSales();
                    break;
                case 4:
                    updateSales();
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Invalid option, try again");
            }
        }
    }
}
