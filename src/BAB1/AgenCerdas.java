package BAB1;

public class AgenCerdas {
    private String lingkungan[][];
    AgenCerdas(int location){
        lingkungan = new String[location][3];
    }
    public void setKondisi(int i, String Nama,String location,String status){
        lingkungan[i-1][0]=Nama;
        lingkungan[i-1][1]=location;
        lingkungan[i-1][2]=status;
    }

public void vacumm (int i,String location, String status){
        if ("kotor".equals(status.toLowerCase())){
            System.out.println("----bersih-bersih----");
            status = "bersih";
            lingkungan[i][2]=status;
            vacumm(i,location,status);
        }else if(i+1 == lingkungan.length){
            System.out.println("\n----selesai----");
        }else if(i > 0 && i%2!=0 ){
            System.out.println("\n----geser bawah----");
        }else if ("kiri".equals(location.toLowerCase())){
            System.out.println("\ngeser kanan-->");
        }else if ("kanan".equals(location.toLowerCase())){
            System.out.println("\n<--geser kiri");
        }
    }
    public void bersihkan(){
        for (int i = 0; i<lingkungan.length; i++){
            System.out.println("Lokasi : "+lingkungan[i][0]);
            vacumm(i,lingkungan[i][1],lingkungan[i][2]);
            System.out.println("");
        }
        cek(); 
    }
    public void cek (){
        for (int i = 0; i<lingkungan.length; i++){
            if(!"bersih".equals(lingkungan[i][2])){
                bersihkan();
                break;	
            }
        }
        System.out.println("SEMUA SUDAH BERSIH");
    }
    public static void main(String[] args) {
        AgenCerdas a = new AgenCerdas(6);
        a.setKondisi(1, "Lokasi 1", "kanan", "bersih");
        a.setKondisi(2, "Lokasi 2", "kiri", "kotor");
        a.setKondisi(3, "Lokasi 3", "kiri", "kotor");
        a.setKondisi(4, "Lokasi 4", "kanan", "kotor");
        a.setKondisi(5, "Lokasi 5", "kanan", "kotor");
        a.setKondisi(6, "Lokasi 6", "kiri", "bersih");
        a.bersihkan();
    }
}
