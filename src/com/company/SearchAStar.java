package com.company;

import java.util.ArrayList;
import java.util.HashSet;

import static com.util.ArrayUtils.copy;

public class SearchAStar {

    private int n, m;
    private State startState;
    private int[][] startIndexes;

    private int maxIteration = 100000;

    private String goalState;
    // Способы перемещения квадратиков в игре (имеется ввиду изменения положения пустой ячейки)
    int[][] steps = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public ArrayList<int[][]> getSolve(int[][] startMatrix) {
        this.n = startMatrix.length;
        this.m = startMatrix[0].length;

        int[][] t = new int[n * m][2];
        StringBuilder goalState = new StringBuilder("");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Вычисляем параллельно искомое состояние, на основе размерности сетки
                if (i != n - 1 || j != m - 1) {
                    goalState.append(i * m + j + 1);
                    goalState.append(".");
                } else {
                    goalState.append("-1");
                }

                // Запоминаем исходное положение чисел для функции g(x)
                int value = startMatrix[i][j];
                int firstIndex = (value != -1) ? (value) : (0); // // Первую строку (с нулевым индексом) мы оставим для координат пустой клетки в игры
                t[firstIndex][0] = i;
                t[firstIndex][1] = j;
            }
        }

        this.startIndexes = t;
        this.goalState = goalState.toString();

        State startState = new State(startMatrix, t[0][0], t[0][1], null);
        State finish = AStar(startState);

        return getToList(finish);
    }

    // g(x)
    private int g(State state) {
        int sumG = 0;
        int[][] currentState = state.directlyState;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int indexOfValue = (currentState[i][j] == -1) ? (0) : (currentState[i][j]);

                int dI = startIndexes[indexOfValue][0];
                int dJ = startIndexes[indexOfValue][1];

                sumG += Math.abs(i - n - 1 - dI) + Math.abs(j - m - 1 - dJ);
            }
        }

        return sumG;
    }

    // h(x)
    private int h(State state) {
        int sumH = 0;
        int[][] currentState = state.directlyState;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = currentState[i][j];

                if (value == -1 && i != n - 1 && j != m - 1) {
                    sumH += Math.abs(i - n - 1) + Math.abs(j - m - 1);
                } else if (value != i * m + j + 1) {
                    sumH += Math.abs(i - ((value - 1) / m)) + Math.abs(j - (value - ((value - 1) / m) * m - 1));
                }

//                sumH -= ((value != (i * m + j + 1)) || ((value == -1) && (i == n - 1) && (j == m - 1))) ? (1) : (0);

            }
        }

        return sumH;
    }

    // A*
    private State AStar(State startState) {
        PriorityQueueOfBinaryHeap<State> open = new PriorityQueueOfBinaryHeap<>();
        HashSet<String> close = new HashSet<String>();

        open.insert(startState, 0);
        int count = 0;

        while (open.size() != 0 && count < maxIteration) {
            State curr = open.extractMin();

            if (close.contains(stringView(curr.directlyState))) {
                continue;
            }
            if (isGoal(curr)) {
                return curr;
            }
            close.add(stringView(curr.directlyState));

            count += 1;
//            System.out.println("It: " + count);

            for (int i = 0; i < steps.length; i++) {
                int iNew = curr.iEmpty + steps[i][0];
                int jNew = curr.jEmpty + steps[i][1];

                if (itCanBeDone(iNew, jNew)) {
                    State d = new State(makeStep(curr, iNew, jNew), iNew, jNew, curr);
                    int f = g(d) + h(d);
                    open.insert(d, f);
                }
            }
        }

        if (count == maxIteration) {
            System.out.println("Превышено максимальное число итераций, возможно, задача не разрешима!");
        }

        return null;
    }

    private boolean itCanBeDone(int i, int j) {
        return ((0 <= i && i < n) && (0 <= j && j < m));
    }

    private int[][] makeStep(State state, int iNew, int jNew) {
        int[][] iState = copy(state.directlyState);
        int iEmpty = state.iEmpty;
        int jEmpty = state.jEmpty;

        iState[iEmpty][jEmpty] = iState[iNew][jNew];
        iState[iNew][jNew] = -1;

        return iState;
    }

    private boolean isGoal(State candidate) {
        return stringView(candidate.directlyState).equals(goalState);
    }

    private String stringView(int[][] matrix) {
        StringBuilder output = new StringBuilder("");
        int[][] arr = matrix;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                output.append(arr[i][j]);
                if (i != n - 1 || j != m - 1) {
                    output.append(".");
                }
            }
        }

        return output.toString();
    }

    private ArrayList<int[][]> getToList(State finalState) {
        ArrayList<int[][]> list = new ArrayList<>();
        State curr = finalState;

        while(curr != null && curr.prev != null) {
            list.add(0, curr.directlyState);
            curr = curr.prev;
        }

        return list;
    }

}
