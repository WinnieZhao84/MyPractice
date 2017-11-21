package LeetCode.Medium;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note: The solution is guaranteed to be unique.

 * @author WinnieZhao
 *
 */
public class GasStation {

    /**
     * If the total gas is greater than the total cost, i.e., gas(1) + gas(2) + … + gas(n) > cost(1) + cost(2) + … + cost(n),
     * there must be a way to travel around the circuit once.
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0;
        
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];
            
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        if (sumGas < sumCost) {
            return -1;
        }
        return start;
    }
}
