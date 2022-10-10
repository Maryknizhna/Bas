import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String[] productName = {"Хлеб", "Крупа", "Молоко"};
        int[] prices = {20, 80, 35};

        File binFile = new File("basket.bin");
        Basket basket = new Basket(productName, prices);

        if (binFile.exists()) {
            Basket.loadFromBinFile(binFile);
        } else {
            basket.printAllProducts();

            while (true) {
                System.out.println("Выберите продукт и кол-во `end`");
                String input = scan.nextLine();
                if ("end".equals(input)) {
                    break;
                }

                String[] productAndCount = input.split(" ");
                int productNum;
                try {
                    productNum = Integer.parseInt(productAndCount[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Number format!!!");
                    continue;
                }

                int productAmount;
                try {
                    productAmount = Integer.parseInt(productAndCount[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Number format!!!");
                    continue;
                }

                if (productAmount > productName.length || productAmount <= 0) {
                    System.out.println("Продукта нет!");
                    continue;
                }

                basket.addToCart(productNum, productAmount);
            }
            basket.saveBin(binFile);
            basket.printCart();
        }
    }
}
