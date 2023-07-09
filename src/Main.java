import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Thread {
    public static final String PERSON_URL = "c:\\io\\turgutozaluniversitesi\\person.txt";
    public static final String SECRET_URL = "c:\\io\\turgutozaluniversitesi\\secret.txt";
    // Kullanıcı bilgilerini al
    public String kullaniciBilgileriniAl() {
        Scanner klavye = new Scanner(System.in);
        String username, password, email;

        System.out.println("Kullanıcı Adı giriniz: ");
        username = klavye.nextLine();

        System.out.println("Şifre giriniz: ");
        password = klavye.nextLine();

        System.out.println("E-posta giriniz: ");
        email = klavye.nextLine();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Kullanıcı Adı: ").append(username).append("\n");
        stringBuilder.append("Şifre: ").append(password).append("\n");
        stringBuilder.append("E-posta: ").append(email);

        return stringBuilder.toString();
    }

    // Kullanıcı bilgilerini dosyaya kaydet
    public void kullaniciBilgileriniDosyayaKaydet() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PERSON_URL, false))) {
            String kullaniciBilgileri = kullaniciBilgileriniAl();
            bufferedWriter.write(kullaniciBilgileri);
            bufferedWriter.flush();
            System.out.println("Kullanıcı bilgileri " + PERSON_URL + " dosyasına kaydedildi.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Gizli bilgileri al
    public String gizliBilgiyiAl() {
        Scanner klavye = new Scanner(System.in);
        String gizliBilgi;

        System.out.println("Gizli Bilgi giriniz: ");
        gizliBilgi = klavye.nextLine();

        return gizliBilgi;
    }

    // Gizli bilgileri dosyaya kaydet
    public void gizliBilgiyiDosyayaKaydet() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(SECRET_URL, false))) {
            String gizliBilgi = gizliBilgiyiAl();
            bufferedWriter.write(gizliBilgi);
            bufferedWriter.flush();
            System.out.println("Gizli bilgiler " + SECRET_URL + " dosyasına kaydedildi.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. Thread: Kullanıcı bilgilerini dosyaya kaydet
        Main thread1 = new Main();
        thread1.kullaniciBilgileriniDosyayaKaydet();

        // 2. Thread: Gizli bilgileri dosyaya kaydet
        Main thread2 = new Main();
        thread2.gizliBilgiyiDosyayaKaydet();

        // Thread'leri başlat
        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        System.out.println("Program tamamlandı.");
    }
}