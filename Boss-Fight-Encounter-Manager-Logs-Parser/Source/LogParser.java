import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reading data from a csv file and log it to a list
 */
public class LogParser {
    public static void main(String[] args) throws FileNotFoundException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader("encounterLogs.csv"));
        List<PlayerScore> playerScoreList = new ArrayList<>();
        int index = 0;
        while (true) {
            try {
                if ((line = br.readLine()) != null && !(line.isEmpty())) {
                    String[] playerData = line.split(",");
                    if (index > 0) {
                        PlayerScore player = new PlayerScore(playerData[0], playerData[1], Integer.parseInt(playerData[2]), Integer.parseInt(playerData[3]), Integer.parseInt(playerData[4]), Integer.parseInt(playerData[5]), Integer.parseInt(playerData[6]));
                        playerScoreList.add(player);
                    }
                    index++;
                } else {
                    System.out.println("The file encounterLogs.csv has been successfully loaded.");
                    br.close();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nQuery 1: Damage received by chopper is:");
        playerScoreList.stream()
                .filter(q1 -> q1.getPlayerName().equals("Chopper"))
                .forEach(q1 -> System.out.println(q1.getDamageReceived()));

        List<String> query2 = playerScoreList.stream()
                .filter(q2 -> q2.getHealingDone() > 300)
                .filter(q2 -> q2.getRole().equals("Healer"))
                .map(q2 -> q2.getPlayerName())
                .collect(Collectors.toList());
        System.out.println("\nQuery 2: List of healers’ names that did more healing than 300 is:\n" + query2);

        System.out.println("\nQuery 3: Damage dealt per damage received ratio for tanks:");
        playerScoreList.stream()
                .filter(q3 -> q3.getRole().equals("Tank"))
                .forEach(q3 -> System.out.println(q3.getPlayerName() + "    " + q3.getDamageDealt().floatValue() / q3.getDamageReceived().floatValue()));

        List<String> query4 = playerScoreList.stream()
                .filter(q4 -> q4.getRole().equals("DamageDealer"))
                .sorted(Comparator.comparing(x -> (float) x.getDamageDealt() / x.getAttacks()))
                .map(q4 -> q4.getPlayerName())
                .collect(Collectors.toList());
        System.out.println("\nQuery 4: The name of the damage dealer that dealt the least amount of damage per attack (DamageDealt/Attacks):\n" + query4.get(0));

        List<Integer> query5 = playerScoreList.stream()
                .filter(q5 -> q5.getRole().equals("Tank"))
                .sorted(Comparator.comparing(x -> x.getDamageDealt()))
                .map(q5 -> q5.getHealingReceived())
                .collect(Collectors.toList());
        System.out.println("\nQuery 5: How much healing was received by the tank that dealt the most damage:\n" + query5.get(query5.size() - 1));
    }
}