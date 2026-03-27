import java.util.HashSet;

public class UniqueEmailAddresses {

    public static void main(String[] args) {
//        System.out.println(Solution1.numUniqueEmails(new String[]{"test.email+alex@neetcode.com","test.e.mail+bob.cathy@neetcode.com","testemail+david@nee.tcode.com"}));
        System.out.println(Solution1.numUniqueEmails(new String[]{"a@neetcode.com","b@neetcode.com","c@neetcode.com"}));

    }

    static class Solution1 {
        public static int numUniqueEmails(String[] emails) {
            var set = new HashSet<String>();

            for (var email : emails) {
                if (!email.contains("@")) {
                    continue;
                }

                var emailArr = email.split("@");

                if (emailArr.length != 2) {
                    continue;
                }

                var plusIndex = emailArr[0].indexOf("+");
                var localName = plusIndex > -1 ? emailArr[0].substring(0, plusIndex).replaceAll("[^a-zA-Z0-9]", "") : emailArr[0].replaceAll("[^a-zA-Z0-9]", "");

                var domainName = emailArr[1];

                set.add(String.format("%s@%s", localName, domainName));
            }

            System.out.println(set);

            return set.size();
        }

    }
}
