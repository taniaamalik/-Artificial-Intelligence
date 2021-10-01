package BAB8;

import java.util.Scanner;

public class Bayes {

    public static float bayes(float x, float y, float z) {
        float num = x * y;
        float denum = num + (z * (1 - y));
        return num / denum;
    }

    public static float truePos(float pop, float tru, float pos, float tp) {
        float ba = tp / tru;
        float a = tru / pop;
        float bna = (pos - tp) / (pop - tru);
        return bayes(ba, a, bna) * 100;
    }

    public static float trueNeg(float pop, float tru, float pos, float tp) {
        float ba = 1 - (tp / tru);
        float a = tru / pop;
        float bna = 1 - ((pos - tp) / (pop - tru));
        return bayes(ba, a, bna) * 100;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Populasi sample: ");
        int pop = in.nextInt();
        System.out.print("Jumlah penderita kanker: ");
        int tru = in.nextInt();
        System.out.print("Jumlah tes hasil positif: ");
        int pos = in.nextInt();
        System.out.print("Jumlah tes yang tepat: ");
        int tp = in.nextInt();
        System.out.println("\nJika hasil tes POSITIF kanker, kemungkinan Anda: ");
        System.out.printf("Kanker: %.2f%%\n", truePos(pop, tru, pos, tp));
        System.out.printf("Tidak kanker: %.2f%%\n", (100 - truePos(pop, tru, pos, tp)));

        System.out.println("\nJika hasil tes NEGATIF kanker, kemungkinan Anda: ");
        System.out.printf("Kanker: %.2f%%\n", trueNeg(pop, tru, pos, tp));
        System.out.printf("Tidak kanker: %.2f%%\n", (100 - trueNeg(pop, tru, pos, tp)));
    }
}
