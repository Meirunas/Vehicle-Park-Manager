public class Motorbike extends Vehicle{

    private int engineSize;
    
    public Motorbike(String brand,String IDPlate, DateTime dateTime,int engineSize, String colour) {
        super(brand, IDPlate,dateTime,colour);
        this.engineSize = engineSize;
        
    }
    
     public int getEngineSize() {
        return engineSize;
    }
     public String getVehicle(){
        return "Motorbike";
    }
}
