package LeetCode.Medium;

import java.util.*;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email
 * that is common to both accounts. Note that even if two accounts have the same name, they may belong to different
 * people as people could have the same name. A person can have any number of accounts initially, but all of their accounts
 * definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name,
 * and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
 *             ["John", "johnnybravo@mail.com"],
 *             ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *             ["Mary", "mary@mail.com"]]
 *
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 *          ["John", "johnnybravo@mail.com"],
 *          ["Mary", "mary@mail.com"]]
 *
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer
 * [['Mary', 'mary@mail.com'],
 *  ['John', 'johnnybravo@mail.com'],
 *  ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']
 * ]
 * would still be accepted.
 *
 * Note:
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].

 * Created by WinnieZhao on 12/29/2017.
 */
public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();

        if (accounts == null || accounts.isEmpty()) {
            return result;
        }

        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();

        /**
         * Construct parent map using each element -> element itself
         * a b c
         * d e f
         * g a d
         *
         * Result as:
         *  a->a, b->b, c->c
         *  d->d, e->e, f->f
         *  g->g, a->a, d->d
         */
        for (List<String> account : accounts) {
            for (int i=0; i<account.size(); i++) {
                parents.put(account.get(i), account.get(i));
                owner.put(account.get(i), account.get(0));
            }
        }

        /**
         * Use the first element as the group's parent
         *
         *(a->a), b->a, c->a
         *(d->d), e->d, f->d
         * g->g, a->g, d->g
         */
        for (List<String> account : accounts) {
            String parent = this.find(account.get(1), parents);

            for (int i=2; i<account.size(); i++) {
                parents.put(this.find(account.get(i), parents), parent);
            }
        }

        /**
         * When construct the union map, always find the most-parent for each element and make it as the map's key
         * so as the result:
         *    "a" parent => a => g
         *    "b" parent => a => g
         *    "c" parent => g
         *    "e" parent => d => g
         *    "f" parent => d => g
         *    "g" parent => g
         *
         * All elements merged into one list
         */
        for (List<String> account : accounts) {
            String parent = this.find(account.get(1), parents);
            if (!unions.containsKey(parent)) {
                unions.put(parent, new TreeSet<>());
            }
            for (int i=1; i<account.size(); i++) {
                unions.get(parent).add(account.get(i));
            }
        }

        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            result.add(emails);
        }

        return result;
    }

    private String find(String email, Map<String, String> parents) {
        return parents.get(email).equals(email) ? email : find(parents.get(email), parents);
    }

    public static void main(String[] args) {
        AccountsMerge solution = new AccountsMerge();

        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("1","a","b","c"));
        accounts.add(Arrays.asList("1","d","e","f"));
        accounts.add(Arrays.asList("1","g","a","d"));

        solution.accountsMerge(accounts).stream().forEach(res -> System.out.println(res.toString()));
    }
}
