public class Euler5{
    public static void main(String[] args){
        boolean boo = false;
        int x = 0;
        while(!boo){
            x++;
            boo = true;
            for(int i = 1; i <= 20; i++){
                if(x % i != 0){
                    boo = false;
                    break;
                }
            }
        }
        System.out.println(x);    
    }
}
