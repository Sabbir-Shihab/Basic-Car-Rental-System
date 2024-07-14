class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvaillable;


    public Car(String carId,String brand,String model,double basePricePerDay){
        this.carId= carId;
        this.brand=brand;
        this.model=model;
        this.basePricePerDay=basePricePerDay;
        this.isAvaillable=true;
    }
    public String getCarId(){
        return carId;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }

    public double calculatePrice(int rentalDay){
        return basePricePerDay+rentalDay;
    }

    public boolean isAvaillable(){
        return isAvaillable;
    }
    public void retuncar(){
        isAvaillable=true;
    }
    public void rent(){
        isAvaillable=false;
    }

}