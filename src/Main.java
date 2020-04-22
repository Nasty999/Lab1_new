import java.util.Arrays;

public class Main {

    private static void printArray(int[] a){

        for(int i=0;i < a.length; i++){

            System.out.format("%d", a[i]);
        }
        System.out.format("\n");
    }

    public static int[] mul(int[] a, int[] b){

        int[] c = new int[a.length+b.length-1];

        Arrays.fill(c, 0);

        for(int k = 0; k < b.length; k++){

            if(b[b.length-k-1]==0) continue;

            for(int i = 0; i < a.length; i++){

                int x = a[a.length-i-1] ^ c[c.length-k-1-i];

                c[c.length-k-i-1] = x;
            }
        }

        return c;
    }

    public static void cyclicShiftRight(int[] a){

        int x = a[a.length-1];

        for(int i = a.length-1; i>0; i--){

            a[i] = a[i-1];
        }

        a[0] = x;
    }

    public static int[][] div(int[] a, int[] b){

        int[] c = Arrays.copyOf(a, a.length);

        int[] d = new int[a.length-b.length+1];
        Arrays.fill(d, 0);

        for(int i = 0; i < d.length; i++){

            if(c[i] == 1){

                //printArray(c);
                //printArray(b);



                d[i] = 1;

                for(int j = 0; j < b.length; j++){

                    c[i+j] ^= b[j];
                }
            }
        }
        //printArray(c);
        //printArray(b);


        int[] rem = Arrays.copyOfRange(c, c.length-b.length+1, c.length);

        int[][] result = new int[2][];

        result[0] = d;
        result[1] = rem;

        return result;
    }

    public static int[] xor(int[] a, int[] b){

        if(a.length != b.length){

            System.out.format("Длины векторов не совпадают!\n");
            return null;
        }

        int[] c = Arrays.copyOf(a, a.length);

        for(int i = 0; i < a.length; i++){

            c[i] = a[i] ^ b[i];
        }

        return c;
    }



    public static void divdemo(int[] a, int[] b){

        int[][] result = div(a, b);

        printArray(a);
        System.out.format(" : ");
        printArray(b);
        System.out.format(" = ");
        printArray(result[0]);
        System.out.format(" + ");
        printArray(result[1]);
        System.out.format("\n");
    }

    public static boolean checkAllZeroes(int[] a){

        for(int i = 0; i < a.length; i++){

            if(a[i]!=0){

                return  false;
            }
        }

        return  true;
    }

    public static void dopZadanie(int L, int[] g, int d){

        int n = g.length+L-1;

        // генерируем вектора ошибок

        int[] e = new int[n];

        for(int i = 1; i < Math.pow(2, n); i++){

            int k = i;
            int cnt = 0;
            for(int j = 0; j < n; j++){

                e[j] = k % 2;
                if(e[j]==1){
                    cnt++;
                }
                k /= 2;
            }
            if(cnt >= d){

                continue;
            }

            // генерируем сообщения
            int[] m = new int[L];

            for(int j = (int)Math.pow(2, L)/2; j < Math.pow(2, L); j++){

                k = j;
                for(int t = 0; t < L; t++){

                    m[m.length-1-t] = k%2;
                    k /= 2;
                }

                // формируем a и накладываем ошибку
                int[] a = mul(m, g);

                int[] b = xor(a, e);

                int[][] res = div(b,g);

                int[] s = res[1];

                if(checkAllZeroes(s)){

                    System.out.format("Message:\n");
                    printArray(m);
                    System.out.format("Error vector:\n");
                    printArray(e);
                    System.out.format("a:\n");
                    printArray(a);
                    System.out.format("b:\n");
                    printArray(b);
                    System.out.format("b/g = \n");
                    printArray(res[0]);
                    return;
                }
            }

        }

    }


    public static void main(String...args){

        int[] g = {1, 1, 1, 0};
        //int[] m = {1, 1, 1};
        int d = 3;
        int L = 5;
        dopZadanie(L, g, d);

        //divdemo(new int[]{0,1,1,1,0,0}, new int[]{1, 1, 1, 0});






    }
}
