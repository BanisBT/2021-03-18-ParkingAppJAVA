package org.example.tomasBarauskas.factory;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.exception.finance.NotPaidParkingFine;
import org.example.tomasBarauskas.exception.finance.RestoreBalance;
import org.example.tomasBarauskas.exception.finance.TransferZeroLess;
import org.example.tomasBarauskas.exception.parkingTicket.TicketError;
import org.example.tomasBarauskas.exception.parkingTicket.UserDontHaveOpenParkingTicket;
import org.example.tomasBarauskas.exception.parkingTicket.UserHaveOpenParkingTicket;
import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.factory.parkingFineFactory.ParkingFineFactory;
import org.example.tomasBarauskas.factory.parkingTicketFactory.ParkingTicketFactory;
import org.example.tomasBarauskas.factory.parkingZoneFactory.ParkingZoneFactory;
import org.example.tomasBarauskas.factory.statisticFactory.StatisticFactory;
import org.example.tomasBarauskas.factory.userFactory.UserFinanceFactory;
import org.example.tomasBarauskas.factory.userFactory.UserLogInFactory;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.parkingCity.*;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.service.parkingFineDbManager.ParkingFineDbManager;
import org.example.tomasBarauskas.service.parkingFineDbManager.ParkingFineDbManagerImpl;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManager;
import org.example.tomasBarauskas.service.parkingTicketDbManager.ParkingTicketDbManagerImpl;
import org.example.tomasBarauskas.service.parkingZoneDbManager.ParkingZoneDb;
import org.example.tomasBarauskas.service.parkingZoneDbManager.ParkingZoneDbImpl;
import org.example.tomasBarauskas.service.userUserDbManager.UserDbManagerImpl;
import org.example.tomasBarauskas.model.user.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class InputMenu {

    private final Scanner sc = new Scanner(System.in);
    private final UserLogInFactory userLogInFactory = new UserLogInFactory();
    private final UserFinanceFactory userFinanceFactory = new UserFinanceFactory();
    private final ParkingZoneFactory parkingZoneFactory = new ParkingZoneFactory();
    private final ParkingFineFactory fineFactory = new ParkingFineFactory();
    private final ParkingTicketFactory ticketFactory = new ParkingTicketFactory();
    private final StatisticFactory statisticFactory = new StatisticFactory();
    private final UserDbManagerImpl userDbManager = new UserDbManagerImpl();
    private final ParkingZoneDb zoneDb = new ParkingZoneDbImpl();
    private final ParkingTicketDbManager ticketDb = new ParkingTicketDbManagerImpl();
    private final ParkingFineDbManager fineDbManager = new ParkingFineDbManagerImpl();
    private final CompanyAccount companyAccount = new CompanyAccount();
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final int ZONE_LIST_SIZE = zoneDb.getParkingZoneList().size();
    private final ParkingCity VILNIUS_CITY = new ParkingVilnius();
    private final ParkingCity KAUNAS_CITY = new ParkingKaunas();
    private final ParkingCity KLAIPEDA_CITY = new ParkingKlaipeda();
    private final String SING_OUT_REGULAR_USER = "8";
    private final String SING_OUT_MANAGER_USER = "11";
    private final String EXIT_PROGRAM = "3";
    private final String KLAIPEDA = "Klaipeda";
    private final String VILNIUS = "Vilnius";
    private final String KAUNAS = "Kaunas";
    private final String RED_ZONE = "Raudona zona";
    private final String BLUE_ZONE = "Melina zona";
    private final String GREEN_ZONE = "Zalia zona";

    public InputMenu() throws IOException {
    }

    public void StartProgram() throws IOException, ClassNotFoundException {
        // Manager ID - Manager Psw - Manager
        // Regular ID - Banis Psw - Banis
        fineDbManager.probabilityGetParkingFineFromController();
        String ivestis = "";

        while (!ivestis.equals(EXIT_PROGRAM)) {
            startMenu();
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    regularUserRegistration();
                    break;
                case "2":
                    try {
                        logInOptions();
                    } catch (NoUserInDbByID e) {
                        System.out.println("Blogi prisijungimo duomenys");
                    }
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Prasome pasirinkti is meniu opciju");
            }
        }
    }

    private void startMenu() {
        System.out.println("[1] Registruotis");
        System.out.println("[2] Prisijungti prie sistemos");
        System.out.println("[3] Iseiti");
    }

    private void customerMenu() {
        System.out.println("[1] Parkomatas");
        System.out.println("[2] Pradeti parkavima");
        System.out.println("[3] Baigti parkavima");
        System.out.println("[4] Isideti pinigu i pinigine");
        System.out.println("[5] Baudos");
        System.out.println("[6] Pakeisti masinos numeri");
        System.out.println("[7] Perziureti savo stovejimo talonu istorija");
        System.out.println("[8] Atsijungti");
    }

    private void companyMenu() {
        System.out.println("[1] Viso surinkta pinigu");
        System.out.println("[2] Talonai pagal miestus");
        System.out.println("[3] Talonai pagal zonas");
        System.out.println("[4] Talonai pagal dienas");
        System.out.println("[5] Talonu kiekis ir vidurkis pagal miesta");
        System.out.println("[6] Talonu kiekis ir vidurkis pagal zona");
        System.out.println("[7] Apmoketu/Neapmoketu baudu ataskaita");
        System.out.println("[8] Pakeisti valandos ikaini");
        System.out.println("[9] Pakeisti baudos dydi");
        System.out.println("[10] Uzregistruoti nauja tarnautoja");
        System.out.println("[11] Atsijungti");
    }

    private void printCities() {
        System.out.println("[1] Vilnius");
        System.out.println("[2] Kaunas");
        System.out.println("[3] Klaipeda");
    }

    private void printZones() {
        System.out.println("[1] Zalia zona");
        System.out.println("[2] Raudona zona");
        System.out.println("[3] Melina zona");
    }

    private void regularUserRegistration() {
        System.out.println("Kokio vartotojo ID noretumet?");
        String userIdToCheck = sc.nextLine();
        try {
            userLogInFactory.registrationCheckIfIdExist(userIdToCheck);
            System.out.println("Koks bus Jusu slaptazodis");
            String userPassword = sc.nextLine();
            System.out.println("Jusu vardas");
            String userName = sc.nextLine();
            System.out.println("Jusu pavarde");
            String userSurname = sc.nextLine();
            System.out.println("Jusu automobilio numeris");
            String userCarNumber = sc.nextLine();

            userLogInFactory.regularUserRegistration(userIdToCheck, userPassword, userName, userSurname, userCarNumber);
            System.out.println("Registracija sekminga");
        } catch (RegistrationIdAlreadyExist e) {
            System.out.println(userIdToCheck + " -  sis vartotojo ID jau uzimtas");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Serverio klaida");
        }
    }

    private void logInOptions() throws NoUserInDbByID, IOException, ClassNotFoundException {
        System.out.println("Iveskite savo ID varda");
        String logInID = sc.nextLine();
        System.out.println("Iveskite savo slaptazodi");
        String loginPassword = sc.nextLine();

        User userToLogIn = userLogInFactory.checkUserLogInDetails(logInID, loginPassword);

        if (userLogInFactory.switchMenuByUserRole(userToLogIn)) {
            regularUserFunctionality(userToLogIn);
        } else {
            companyFunctionality(userToLogIn);
        }
    }

    private void regularUserFunctionality(User user) throws IOException, ClassNotFoundException {
        String ivestis = "";
        checkIfUserHaveOpenParkingFine(user);

        while (!ivestis.equals(SING_OUT_REGULAR_USER)) {
            customerMenu();
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    parkingMachine(user);
                    break;
                case "2":
                    beganParking(user);
                    break;
                case "3":
                    endParking(user);
                    break;
                case "4":
                    putMoneyInToAccount(user);
                    break;
                case "5":
                    // sumoketi bauda
                    break;
                case "6":
                    changeCarNumber(user);
                    break;
                case "7":
                    getUserAllParkingTickets(user);
                    break;
                case "8":
                    break;
                default:
                    System.out.println("Prasau pasirinkti is meniu");
                    break;
            }
        }
    }

    private void companyFunctionality(User userManager) {
        String ivestis = "";
        while (!ivestis.equals(SING_OUT_MANAGER_USER)) {
            companyMenu();
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    System.out.println(companyAccount);
                    break;
                case "2":
                    statisticByDateAndCity();
                    break;
                case "3":
                    statisticByDateAndZone();
                    break;
                case "4":
                    getStatisticByDate();
                    break;
                case "5":
                    // ataskaita pagal miestus
                    break;
                case "6":
                    // ataskaita pagal zonas
                    break;
                case "7":
                    // apmoketos - neapmoketos baudos
                    break;
                case "8":
                    changePricePerHour();
                    break;
                case "9":
                    changeFineAmount();
                    break;
                case "10":
                    singUpManager();
                    break;
                case "11":
                    break;
                default:
                    System.out.println("Prasom pasirinkti is meniu opciju");
                    break;
            }
        }
    }

    private void putMoneyInToAccount(User user) {
        boolean isNumber = true;

        while (isNumber) {
            System.out.println("Kiek pinigu noretumet ideti i saskaita");
            String putAmountString = sc.nextLine();

            try {
                float putAmount = Float.parseFloat(putAmountString);
                userFinanceFactory.userPutMoneyToAccount(user, putAmount);
                isNumber = false;
            } catch (NumberFormatException e) {
                System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, naudokite taska \".\" vietoje kablelio");
            } catch (TransferZeroLess transferZeroLess) {
                System.out.println("Negalima isideti neigiamos sumos i saskaita");
            }
        }
    }

    private void changeCarNumber(User user) {
        System.out.println("Kokie nauji Jusu automobilio numeriai");
        String carNumberToChange = sc.nextLine();
        user.setCarNumber(carNumberToChange);
        userDbManager.rewriteUserDetailsToFile(user);
    }

    private void singUpManager() {
        System.out.println("Koks bus naujo tarnautojo ID?");
        String singUpManagerId = sc.nextLine();

        try {
            userDbManager.registrationCheckIfIdExist(singUpManagerId);
            System.out.println("Koks bus slaptazodis");
            String singUpManagerPassword = sc.nextLine();
            System.out.println("Tarnautojo vardas");
            String singUpManagerName = sc.nextLine();
            System.out.println("Tarnatojo pavarde");
            String singUpManagerSurname = sc.nextLine();

            userLogInFactory.managerUserRegistration(singUpManagerId, singUpManagerPassword, singUpManagerName, singUpManagerSurname);
        } catch (RegistrationIdAlreadyExist e) {
            System.out.println(singUpManagerId + " - sis ID jau uzmintas");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Serverio klaida, nepavyko pasiekti duomenu");
        }
    }

    private void changePricePerHour() {
        System.out.println("Kurios zonos ikaini norite pakeisti, iveskite zonos numeri" + '\n');
        parkingZoneFactory.getZoneListWithPricePerHour().forEach(System.out::println);
        String zoneNumberToChangeString = sc.nextLine();

        try {
            int zoneNumberToChange = Integer.parseInt(zoneNumberToChangeString);
            if (zoneNumberToChange > ZONE_LIST_SIZE) {
                System.out.println("Paskutinis zonos numeris yra " + ZONE_LIST_SIZE);

            } else {
                System.out.println("Kokia bus nauja kaina?");
                String zonePricePerHourNewString = sc.nextLine();

                try {
                    float zonePricePerHourNew = Float.parseFloat(zonePricePerHourNewString);
                    parkingZoneFactory.changeZoneCostPerHour(zoneNumberToChange, zonePricePerHourNew);
                    System.out.println("Kaina sekmingai pakeista");
                } catch (NumberFormatException e) {
                    System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, " +
                            "naudokite taska \".\" vietoje kablelio");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Parkavimo zonos numeris gali buti tik sveikasis skaicius");
        }
    }

    private void changeFineAmount() {
        System.out.println("Kurios zonos baudos dydi norite pakeisti, iveskite numeri");
        parkingZoneFactory.getListWithFineAmount().forEach(System.out::println);
        String zoneNumberToChangeString = sc.nextLine();

        try {
            int zoneNumberToChange = Integer.parseInt(zoneNumberToChangeString);
            if (zoneNumberToChange > ZONE_LIST_SIZE) {
                System.out.println("Paskutinis zonos numeris yra " + ZONE_LIST_SIZE);

            } else {
                System.out.println("Kokia bus nauja baudos suma");
                String zoneNewFineAmountString = sc.nextLine();

                try {
                    float zoneNewFineAmount = Float.parseFloat(zoneNewFineAmountString);
                    parkingZoneFactory.changeZoneFineAmount(zoneNumberToChange, zoneNewFineAmount);
                    System.out.println("Kaina sekmingai pakeista");
                } catch (NumberFormatException e) {
                    System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, " +
                            "naudokite taska \".\" vietoje kablelio");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Parkavimo zonos numeris gali buti tik sveikasis skaicius");
        }
    }

    private void beganParking(User user) throws IOException, ClassNotFoundException {
        try {
            userFinanceFactory.checkIfUserAmountIsNotMinus(user);
            fineFactory.checkForUserUnpaidParkingFine(user);
            ticketFactory.checkBeganUserOpenTicket(user);

            ParkingCity carParkedCity = chooseCityForParking();
            ParkingZone carParkedZone = chooseZoneForParking(carParkedCity);
            ticketFactory.addOpenTicketToDb(user, carParkedCity, carParkedZone);
        } catch (RestoreBalance e) {
            System.out.println("Negalima uzsakyti parkingo kol saskaitos likutis neigiamas");
        } catch (NotPaidParkingFine e) {
            System.out.println("Kad galetumet uzsakyti parkavimo paslauga pirma reiktu apmoketi parkavimo bauda");
        } catch (UserHaveOpenParkingTicket e) {
            System.out.println("Turite aktyvu parkavimo talona, pries pradedant nauja sustabdykitia sena talona");
        }
    }

    private void endParking(User user) {
        try {
            ticketFactory.checkEndUserOpenTicket(user);
            ticketFactory.stopParkingTimeAndTryToPayForTicket(user);
        } catch (UserDontHaveOpenParkingTicket | TicketError e) {
            System.out.println("Jus neturi aktyviu parkavimo talonu");
        }
    }

    private ParkingCity chooseCityForParking() {
        String pasirinmimas = "";
        boolean wrongChoose = true;

        while (wrongChoose) {
            printCities();
            pasirinmimas = sc.nextLine();

            if (pasirinmimas.equals("1")) {
                wrongChoose = false;
                return VILNIUS_CITY;
            }
            if (pasirinmimas.equals("2")) {
                wrongChoose = false;
                return KAUNAS_CITY;
            }
            if (pasirinmimas.equals("3")) {
                wrongChoose = false;
                return KLAIPEDA_CITY;
            }
            System.out.println("Galima pasirinkti tik is isvardintu miestu");
        }
        return null;
    }

    private ParkingZone chooseZoneForParking(ParkingCity parkingCity) {
        String pasirinkimas = "";
        boolean wrongChoose = true;
        ParkingZone chosenZone = null;

        while (wrongChoose) {
            if (parkingCity.getName().equals(KLAIPEDA)) {
                System.out.println("[1] Zalia zona");
                System.out.println("[2] Raudona zona");
            } else {
                printZones();
            }
            pasirinkimas = sc.nextLine();

            switch (pasirinkimas) {
                case "1":
                    chosenZone = parkingCity.getGreenZone();
                    wrongChoose = false;
                    break;
                case "2":
                    chosenZone = parkingCity.getRedZone();
                    wrongChoose = false;
                    break;
                case "3":
                    chosenZone = parkingCity.getBlueZone();
                    wrongChoose = false;
                    break;
                default:
                    System.out.println("Prasome pasirinkti is nurodytu zonu");
                    break;
            }
        }
        return chosenZone;
    }

    private LocalDateTime chooseParkingTime() {
        String pasirinkimas = "";
        boolean wrongChoose = true;
        LocalDateTime ticketParkingTime = LocalDateTime.now();

        while (wrongChoose) {
            System.out.println("[1] Moketi uz 30min");
            System.out.println("[2] Moketi uz 60min");
            System.out.println("[3] Moketi uz 120min");
            pasirinkimas = sc.nextLine();

            switch (pasirinkimas) {
                case "1":
                    ticketParkingTime = ticketParkingTime.plusMinutes(30);
                    wrongChoose = false;
                    break;
                case "2":
                    ticketParkingTime = ticketParkingTime.plusHours(1);
                    wrongChoose = false;
                    break;
                case "3":
                    ticketParkingTime = ticketParkingTime.plusHours(2);
                    wrongChoose = false;
                    break;
                default:
                    System.out.println("Kol kas sistema leidzia pasirinkti tik is siu laiku");
                    break;
            }
        }
        return ticketParkingTime;
    }

    private void parkingMachine(User user) {
        ParkingCity parkingCity = chooseCityForParking();
        ParkingZone parkingZone = chooseZoneForParking(parkingCity);
        LocalDateTime parkingTime = chooseParkingTime();

        try {
            ticketFactory.parkingMachineTryToOrderParkingTicket(user, parkingCity, parkingZone, parkingTime);
        } catch (InsufficientFunds e) {
            System.out.println("Nepakanka lesu sumoketi uz stovejima");
        }
    }

    private void statisticByDateAndCity() {
        LocalDate dateFrom = getDateForStatistic("pradzios", LocalDate.now().minusYears(50));
        LocalDate dateTo = getDateForStatistic("pabaigos", dateFrom);

        String pasirinkimas = "";
        System.out.println("Pagal koki miesta norite gauti parkavimo talonu statistika");
        printCities();
        pasirinkimas = sc.nextLine();

        switch (pasirinkimas) {
            case "1":
                getStatisticByDateAndCity(dateFrom, dateTo, VILNIUS);
                break;
            case "2":
                getStatisticByDateAndCity(dateFrom, dateTo, KAUNAS);
                break;
            case "3":
                getStatisticByDateAndCity(dateFrom, dateTo, KLAIPEDA);
                break;
            default:
                System.out.println("Pasirinkime yra tik isvardinti miestai");
                break;
        }
    }

    private void statisticByDateAndZone() {
        LocalDate dateFrom = getDateForStatistic("pradzios", LocalDate.now().minusYears(50));
        LocalDate dateTo = getDateForStatistic("pabaigos", dateFrom);

        String pasirinkimas = "";
        System.out.println("Pagal koke zona norite gauti parkavimo talonu statistika");
        printZones();
        pasirinkimas = sc.nextLine();

        switch (pasirinkimas) {
            case "1":
                getStatisticByDateAndZone(dateFrom, dateTo, GREEN_ZONE);
                break;
            case "2":
                getStatisticByDateAndZone(dateFrom, dateTo, RED_ZONE);
                break;
            case "3":
                getStatisticByDateAndZone(dateFrom, dateTo, BLUE_ZONE);
                break;
            default:
                System.out.println("Galimi zonu pasirininkimai tik is pateiktu");
                break;
        }
    }

    private void getStatisticByDateAndZone(LocalDate dateFrom, LocalDate dateTo, String zone) {
        List<ParkingTicket> ticketsSortedByDateAndZone = statisticFactory.getParkingTicketsSortByDateAndZone(dateFrom, dateTo, zone);
        printListOfParkingTickets(ticketsSortedByDateAndZone);
    }

    private void getStatisticByDateAndCity(LocalDate dateFrom, LocalDate dateTo, String city) {
        List<ParkingTicket> ticketsSortedByDateAndCity = statisticFactory.getParkingTicketsSortByDateAndCity(dateFrom, dateTo, city);
        printListOfParkingTickets(ticketsSortedByDateAndCity);
    }

    private void getUserAllParkingTickets(User user){
        List<ParkingTicket> userParkingTickets = ticketDb.getAllUsersParkingTickets(user);
        if (userParkingTickets.isEmpty()){
            System.out.println("Pagal uzklausa talonu nerasta");
        } else {
            userParkingTickets.forEach(ticket -> System.out.println(ticket.toStringForUserViewTickets()));
        }
    }

    private void printListOfParkingTickets(List<ParkingTicket> parkingTicketList){
        if (parkingTicketList.isEmpty()){
            System.out.println("Pagal uzklausa talonu nerasta");
        } else {
            parkingTicketList.forEach(ticket -> System.out.println(ticket.toStringForStatistic()));
        }
    }

    private LocalDate getDateForStatistic(String endOrBegan, LocalDate beganDate) {
        boolean goodDate = true;
        LocalDate dateForStatistic = null;

        while (goodDate) {
            System.out.println("Iveskite " + endOrBegan + " laika (Formatas yyyy-MM-dd)");
            String dateForStatisticString = sc.nextLine();

            try {
                dateForStatistic = LocalDate.parse(dateForStatisticString, dtf);
                if (beganDate.minusDays(1).isBefore(dateForStatistic)) {
                    goodDate = false;
                } else {
                    System.out.println("Data negali buti ankstene negu " + beganDate);
                }

            } catch (DateTimeParseException e) {
                System.out.println("Blogai ivesta data, teisingas datos ivedimo pvz 2021-02-14");
            }
        }
        return dateForStatistic;
    }

    private void getStatisticByDate() {
        LocalDate beganDate = getDateForStatistic("pradzios", LocalDate.now().minusYears(10));
        LocalDate endDate = getDateForStatistic("pabaigos", beganDate);

        List<ParkingTicket> testParkingTicketSortByDate = statisticFactory.getParkingTicketsSortByDate(beganDate, endDate);
        testParkingTicketSortByDate.forEach(ticket -> System.out.println(ticket.toStringForStatistic()));
    }

    private void checkIfUserHaveOpenParkingFine(User user){
        try {
            fineFactory.checkForUserUnpaidParkingFine(user);
        } catch (NotPaidParkingFine e) {
            System.out.println("Jus gavote stovejimo nuobauda, ja apmoketi galite pasirinkus skilti \"Baudos\"");
        }
    }
}