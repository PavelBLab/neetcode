public class test {

    public static void main(String[] args) {
        var arr = new int[]{1,3,2};

        System.out.println(mystoryMethod(arr));
    }

    public static int mystoryMethod(int[] arr) {
        var x = 0;

        for (var val : arr) {
            if (val > x) {
                x = val;
            }
        }

        return x;
    }



}
