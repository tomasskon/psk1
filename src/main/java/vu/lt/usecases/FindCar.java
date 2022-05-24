package vu.lt.usecases;

import vu.lt.entities.Car;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.services.Interfaces.IRentalService;
import vu.lt.services.RentalService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class FindCar implements Serializable {
    @Inject
    private RentalService rentalService;

    private CompletableFuture<Car> freeCarFinderTask = null;

    @LoggedInvocation
    public String findFreeCar(List<Car> allCars) {
        freeCarFinderTask = CompletableFuture.supplyAsync(() -> rentalService.findFreeCar(allCars));

        return "/rentals.xhtml";
    }

    public boolean isCarFindingRunning() {
        return freeCarFinderTask != null && !freeCarFinderTask.isDone();
    }

    public String getFindCarStatus() throws ExecutionException, InterruptedException {
        if (freeCarFinderTask == null) {
            return null;
        }

        if (isCarFindingRunning()) {
            return "Still finding free car...";
        }

        var freeCar = freeCarFinderTask.get();

        return String.format("%s %s", freeCar.getCarBrand().getBrand(), freeCar.getModel());
    }
}