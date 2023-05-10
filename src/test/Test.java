package test;
import dbConnect.*;
import manager.*;
import entity.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException {
        baslikYazdir();
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        boolean dongu = true;
        while(dongu == true){   //
            menuYazdir();
            System.out.print("İşlem seçiniz:");
            int secimSayisi = scanner.nextInt(); // menüdeki seçimlerimi belirtmek için kullanıcıdan değer aldım.
            System.out.println("----------------");
            if (secimSayisi >0 && secimSayisi<6) { // eğer kullanıcı menüde belirttiğim işlemlere karşılık gelen 1 2 3 4 5 sayılarından birini girerse bu sayılara göre işlemleri yaptırdım.
                if (secimSayisi == 1) {
                    System.out.println("Lütfen eklemek istediğiniz ürünün Id'sini giriniz: ");
                    long productId = scanner.nextLong();
                    System.out.println("Lütfen eklemek istediğiniz ürünün adını giriniz: ");
                    String productName= scanner.next();
                    System.out.println("Lütfen eklemek istediğiniz ürünün fiyatını giriniz: ");
                    double salesPrice= scanner.nextDouble();
                    Product product = new Product(productId,productName,salesPrice);
                    productManager.insert(product);
                    System.out.println("Ürün eklendi!!");
                } else if (secimSayisi == 2) {
                    System.out.println("Lütfen güncellemek istediğiniz ürünün Id'sini giriniz: ");
                    long productId = scanner.nextLong();
                    System.out.println("Lütfen güncellemek istediğiniz ürünün adını giriniz: ");
                    String productName= scanner.next();
                    System.out.println("Lütfen güncellemek istediğiniz ürünün fiyatını giriniz: ");
                    double salesPrice= scanner.nextDouble();
                    Product product = new Product(productId,productName,salesPrice);
                    productManager.update(product);
                    System.out.println("Ürün güncellendi!!");

                } else if (secimSayisi == 3) {
                    System.out.println("Silmek  istediğiniz ürünün Id'sini giriniz: ");
                    long productIdDel = scanner.nextLong();
                    productManager.delete(productIdDel);
                } else if (secimSayisi==4) {
                    System.out.println("lütfen bulmak istediğiniz ürünün Id'sini giriniz: ");
                    long productId = scanner.nextLong();
                    Product product = productManager.find(productId);
                    System.out.println("Bulunan ürün : "+ product.getProductName());
                } else if (secimSayisi == 5) {
                    productManager.list();
                }
            } else if (secimSayisi == 6) { // Kullanıcı bu değer aralığında 5 değerini yani programı sonlandır seceneğini secer ise dongu değişkenini false yaparak donguyu sonlandırdım ve program sonlandı.
                dongu = false;
            }
            else { // Kullanıcı 1 2 3 4 5 6 sayılarını girmez farklı sayılar girerse uyarı almasını sağladım..
                System.out.println("UYARI: İşlem yapabilmek için 1 ile 5 arasında bir sayı seçilmelidir!");
                System.out.println("Devam etmek için bir tuşa ve sonrasında ENTER tuşuna basınız:");
                String tus = scanner.next();
            }
        }
        DbConnect.conn.close();
    }
    private static void baslikYazdir(){
        System.out.println("****************************************\n*** ÜRÜN STOK BİLGİSİ ***\n****************************************");
    }
    //Menü yazdırmak için kullandığım methodu oluşturdum
    private static void menuYazdir(){
        System.out.println("\nMENÜ:\n(1) Ürün ekle \n(2) Ürün güncelle\n(3) Ürün sil \n(4) Ürün bul\n(5) Tüm ürünleri listele\n(6) Programı sonlandır");//n ile alt satıra geçmesini sağladım .
    }
}
