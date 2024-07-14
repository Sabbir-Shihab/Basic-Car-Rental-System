import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car>cars;
    private List<Customer>customers;
    private List<Rental>rentals;

    public CarRentalSystem(){
        cars = new ArrayList<>();
        customers=new ArrayList<>();
        rentals= new ArrayList<>();
    }
    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustimer (Customer customer){
        customers.add(customer);
    }
    public void rentCar(Car car, Customer customer,int days){
        if(car.isAvaillable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }
        else {
            System.out.println("Car is not available for Rent");
        }
    }
    public void returnCar(Car car){
        car.retuncar();
        Rental rentalToRemov = null;
        for(Rental rental : rentals){
            if(rental.getCar()==car){
                rentalToRemov=rental;
                break;
            }
        }
        if(rentalToRemov !=null){
            rentals.remove(rentalToRemov);
            System.out.println("Car return successfully");
        }
        else{
            System.out.println("Car was not rented");
        }
    }
    public void menu(){
        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("*** Car Rental System***");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("Enter Your Choice: ");

            int choice= sc.nextInt();
            sc.nextLine();

            if(choice==1) {
                System.out.println("\n ** Rent a Car **\n");
                System.out.println("Enter Your Name: ");
                String customername = sc.nextLine();

                System.out.println("\nAvillable Cars: ");
                for (Car car : cars) {
                    if (car.isAvaillable()) {
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " - " + car.getModel());

                    }
                }
                System.out.println("\n Enter the Car Id you Want Rent: ");
                String carId = sc.nextLine();

                System.out.println("\nEnter the Nmber of Day for Rent");
                int rentalDays = sc.nextInt();
                sc.nextLine();

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customername);
                addCustimer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && car.isAvaillable()) {
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n *** Rental Information ***\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f",totalPrice);

                    System.out.println("\nConfirm rental (y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar Rented successfully");
                    } else {
                        System.out.println("\nRental canceled..");
                    }
                } else {
                    System.out.println("\nInvalid Car selection or Car not Avilavle for Rent");
                }

            }
            else if (choice==2) {
                System.out.println("\n** Return a Car **\n");
                System.out.println("Enter the Car Id you Want to Return: ");
                String carId = sc.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && !car.isAvaillable()) {
                        carToReturn = car;
                        break;
                    }
                }
                Customer customer = null;
                if (carToReturn != null) {
                    customer = null;
                    for (Rental rental : rentals) {
                        customer = rental.getCustomer();
                        break;
                    }
                }
                if (customer !=null){
                    returnCar(carToReturn);
                    System.out.println("Car Returned Successfully by "+customer.getName());

                }
                else {
                    System.out.println("Car was Not Rented or rentel information is missing");

//                } else {
//                    System.out.println("Invalid car Id or Car is not rentad");

            }
           
            } else if (choice==3) {
                break;
            }
            else {
                System.out.println("Invalid Choice . Please enter a valid option");
            }
        }
        System.out.println("\nThank you for using the Car Rental System!");
        
        }
    }

