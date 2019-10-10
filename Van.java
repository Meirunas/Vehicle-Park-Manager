public class Van extends Vehicle{

    private int cargoVolume;
    
     public Van(String brand,String IDPlate, DateTime dateTime,int cargoVolume, String colour) {
        super(brand, IDPlate,dateTime,colour);
        this.cargoVolume = cargoVolume;
        
     }
    public int getCargoVolume() {
        return cargoVolume;
    }
    public String getVehicle(){
        return "Van";
    }
}
