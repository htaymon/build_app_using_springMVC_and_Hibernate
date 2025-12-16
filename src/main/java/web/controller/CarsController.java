    package web.controller;

    import web.service.CarService;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    @Controller
    public class CarsController {

        private final CarService carService;

        public CarsController(CarService carService) {
            this.carService = carService;
        }

        @GetMapping("/cars")
        public String showCars(
                @RequestParam(value = "count", required = false) Integer count,
                Model model) {

            if (count == null) {
                model.addAttribute("cars", carService.getAllCars());
            } else {
                model.addAttribute("cars", carService.getCars(count));
            }

            return "cars";
        }
    }
