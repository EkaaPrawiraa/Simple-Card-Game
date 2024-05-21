import java.util.*;


public class Player {
    private int gulden;
    // private List<Card> activeDeck;
    private static final int GRID_SIZE = 10; // Ukuran ladang
    private static final float BEAR_ATTACK_DURATION = 30.0f; // Durasi serangan beruang dalam detik
    private static final float TIMER_INTERVAL = 0.01f; // Interval waktu dalam detik untuk memperbarui timer

    public Player(int gulden) {
        this.gulden = gulden;
        // activeDeck = new ArrayList<>();
    }

    public void cetakLadang() {
        // invent.display("Ladang");
        // System.out.println("\n\n");
        // Set<String> uniqueCodes = new HashSet<>();
        // String code;
        // String name;
        // for (Map.Entry<Integer, Map<Character, Item>> row : invent.getMatrix().entrySet()) {
        //     for (Map.Entry<Character, Item> cell : row.getValue().entrySet()) {
        //         code = cell.getValue().getKode();
        //         name = cell.getValue().getName();
        //         if (!code.equals("   ")) {
        //             if (!uniqueCodes.contains(code)) {
        //                 System.out.println("- " + code + ": " + name);
        //                 uniqueCodes.add(code);
        //             }
        //         }
        //     }
        // }
    }

    public void Shuffle_Cards(){
        // List<Card> TempActiveDeck = new ArrayList<>();
        // // Tampilkan kartu yang ada untuk pemain memilih
        // for (int i = 0; i < 4; i++) {
        //     TempActiveDeck.add(generateRandomCard()); // Fungsi generateRandomCard() menghasilkan kartu random
        //     System.out.println((i + 1) + ". " + card.getName());
        // }

        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Setuju dengan Kartu yang tersedia? (y || n) :");
        // String input = scanner.nextLine();

        // if(input.equals("y")){
        //     for (Card card : TempActiveDeck) {
        //         activeDeck.add(card);
        //     }
        //     TempActiveDeck.clear();
        // } else{
        //     TempActiveDeck.clear();
        //     Shuffle_Cards();
        // }
    }

    public void bear_attack() {
        // Tentukan apakah serangan beruang terjadi pada awal turn
        Random random = new Random();
        boolean isBearAttacking = false;
        if (random.nextDouble() < 0.3) { // Misalnya, 30% kemungkinan terjadinya serangan beruang
            isBearAttacking = true;
            int bearAttackTimer = BEAR_ATTACK_DURATION;
            int bearAttackRow = random.nextInt(GRID_SIZE);
            int bearAttackCol = random.nextInt(GRID_SIZE);
            System.out.println("Serangan beruang terjadi pada petak (" + bearAttackRow + ", " + bearAttackCol + ")!");
            System.out.println("Waktu tersisa untuk menangkap beruang: " + bearAttackTimer + " detik");
            
            // Mulai thread untuk mengupdate timer serangan beruang
            Thread timerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (bearAttackTimer > 0.0f) {
                        try {
                            Thread.sleep((long)(TIMER_INTERVAL * 1000)); // Konversi TIMER_INTERVAL ke milidetik
                            synchronized (this) {
                                bearAttackTimer -= TIMER_INTERVAL;
                            }
                            System.out.println("Waktu tersisa untuk menangkap beruang: " + bearAttackTimer + " detik");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Waktu untuk menangkap beruang telah habis. Tumbuhan/hewan yang masih berada dalam subgrid akan mati atau hilang!");
                }
            });
            timerThread.start();
        } else {
            System.out.println("Tidak ada serangan beruang saat ini.");
        }
    }

    public void next(Integer GilirPemain) {
        GilirPemain++;
    }

    public String getGulden() {
        return gulden;
    }

    public List<Card> getActiveDeck() {
        // return activeDeck;
    }

}
