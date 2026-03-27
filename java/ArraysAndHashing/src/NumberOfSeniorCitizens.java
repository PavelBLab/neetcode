import java.sql.Array;
import java.util.ArrayList;

public class NumberOfSeniorCitizens {

    public static void main(String[] args) {
        System.out.println(Solution1.countSeniors(new String[]{"7868190130M7522","5303914400F9211","9273338290F4010"}));
    }

    static class Solution1 {
        public static int countSeniors(String[] details) {
            var numberOfPassengersOlderThan60 = new ArrayList<String>();

            for (var passenger : details) {
                var phone = passenger.substring(0,10);
                var gender = passenger.substring(10,11);
                var age = Integer.parseInt(passenger.substring(11,13));
                var seat = passenger.substring(13);

                if (age > 60) {
                    numberOfPassengersOlderThan60.add(passenger);
                }

            }

            System.out.println(numberOfPassengersOlderThan60);
            return numberOfPassengersOlderThan60.size();
        }
    }
}
