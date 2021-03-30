package org.example.tomasBarauskas.factory;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.exception.finance.NotPaidParkingFine;
import org.example.tomasBarauskas.exception.finance.RestoreBalance;
import org.example.tomasBarauskas.exception.finance.TransferZeroLess;
import org.example.tomasBarauskas.exception.parkingTicket.UserDontHaveOpenParkingTicket;
import org.example.tomasBarauskas.exception.parkingTicket.UserHaveOpenParkingTicket;
import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.factory.parkingFineFactory.ParkingFineFactory;
import org.example.tomasBarauskas.factory.parkingTicketFactory.ParkingTicketFactory;
import org.example.tomasBarauskas.factory.parkingZoneFactory.ParkingZoneFactory;
import org.example.tomasBarauskas.factory.userFactory.UserFinanceFactory;
import org.example.tomasBarauskas.factory.userFactory.UserLogInFactory;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.parkingCity.*;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingTime;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.user.UserRole;
import org.example.tomasBarauskas.service.financeManager.FinanceManager;
import org.example.tomasBarauskas.service.financeManager.FinanceManagerImpl;
import org.example.tomasBarauskas.service.parkingStatisticService.ParkingStatisticByCity;
import org.example.tomasBarauskas.service.parkingStatisticService.ParkingStatisticByGroupsService;
import org.example.tomasBarauskas.service.parkingStatisticService.ParkingStatisticByZone;
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
import java.util.stream.Collectors;

public class InputMenu {

    private Scanner sc = new Scanner(System.in);
    private UserLogInFactory userLogInFactory = new UserLogInFactory();
    private UserFinanceFactory userFinanceFactory = new UserFinanceFactory();
    private ParkingZoneFactory parkingZoneFactory = new ParkingZoneFactory();
    private ParkingFineFactory fineFactory = new ParkingFineFactory();
    private ParkingTicketFactory ticketFactory = new ParkingTicketFactory();
    private UserDbManagerImpl userDbManager = new UserDbManagerImpl();


    private ParkingZoneDb zoneDb = new ParkingZoneDbImpl();
    private ParkingTicketDbManager ticketDb = new ParkingTicketDbManagerImpl();
    private CompanyAccount companyAccount = new CompanyAccount();
    private FinanceManager financeMng = new FinanceManagerImpl();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final int ZONE_LIST_SIZE = zoneDb.getParkingZoneList().size() - 1;

    private final String PATH_TICKET_DB = "/Users/Gabi/IdeaProjects/2021-03-10/2021-03-19-ParkingAppDarbas/src/main/java/org/example/tomasBarauskas/file/UserDatabase.ser";
    private final String PATH_USER_DB = "/Users/Gabi/IdeaProjects/2021-03-10/2021-03-19-ParkingAppDarbas/src/main/java/org/example/tomasBarauskas/file/UserDatabase.ser";
    private final String pathParkingZone = "/Users/Gabi/IdeaProjects/2021-03-10/2021-03-19-ParkingAppDarbas/src/main/java/org/example/tomasBarauskas/file/ParkingZoneDatabase.ser";
    private final String SING_OUT_REGULAR_USER = "8";
    private final String SING_OUT_MANAGER_USER = "11";
    private final String EXIT_PROGRAM = "7";
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

//        User tomasRegular = new User("Banis", "Banis", "Tomas", "Barauskas");
//        User manager = new User("Test", "Test", "Manager", "Manager");
//        User andrius = new User("Butas", "Geras", "Andrius", "Pavarde");
//        User andrius1 = new User("Maistas", "Geras", "Andrius", "Pavarde");
//        User andrius2 = new User("Eina", "Blogas", "Andrius", "Pavarde");
//        manager.setRole(UserRole.MANAGER_ROLE);

//        dbManager.addUserToDb(tomasRegular);
//        dbManager.addUserToDb(manager);
//        dbManager.addUserToDb(andrius);
//        dbManager.addUserToDb(andrius1);
//        dbManager.addUserToDb(andrius2);

        ParkingCity vilnius = new ParkingVilnius();
        ParkingCity kaunas = new ParkingKaunas();
        ParkingCity klaipeda = new ParkingKlaipeda();
        LocalDateTime dabar = LocalDateTime.now();
        LocalDateTime pabaiga = dabar.plusHours(1);

//        ParkingTicket ticket9 = new ParkingTicket(vilnius.getName(), ParkingZone.VILNIUS_BLUE_ZONE, tomasRegular);
//        ParkingTicket ticket1 = new ParkingTicket(vilnius.getName(), ParkingZone.VILNIUS_RED_ZONE, tomasRegular);
//        ParkingTicket ticket4 = new ParkingTicket(vilnius.getName(), ParkingZone.VILNIUS_RED_ZONE, andrius);
//        ParkingTicket ticket5 = new ParkingTicket(vilnius.getName(), ParkingZone.VILNIUS_RED_ZONE, andrius1);
//        ParkingTicket ticket2 = new ParkingTicket(kaunas.getName(), ParkingZone.KAUNAS_GREEN_ZONE, andrius2);
//        ParkingTicket ticket6 = new ParkingTicket(kaunas.getName(), ParkingZone.KAUNAS_GREEN_ZONE, andrius1);
//        ParkingTicket ticket7 = new ParkingTicket(kaunas.getName(), ParkingZone.KAUNAS_BLUE_ZONE, andrius);
//        ParkingTicket ticket3 = new ParkingTicket(klaipeda.getName(), ParkingZone.KLAIPEDA_RED_ZONE, andrius2);
//
//        ticket1.setEndParking(dabar.plusHours(10));
//        ticket2.setEndParking(dabar.plusHours(3));
//        ticket3.setEndParking(dabar.plusDays(2));
//        ticket4.setEndParking(dabar.plusMinutes(45));
//        ticket5.setEndParking(dabar.plusHours(7));
//        ticket6.setEndParking(dabar.plusHours(14));
//        ticket9.setEndParking(dabar.plusMinutes(90));
//        FinanceManagerImpl financeManagerTest = new FinanceManagerImpl();
//        BigDecimal ticket1Big = financeManagerTest.getTicketAmount(ticket1.getParkingZone(), ticket1.getBeginParking(), ticket1.getEndParking());
//        ticket1.setTicketAmount(ticket1Big);
//        ticket2.setTicketAmount(financeManagerTest.getTicketAmount(ticket2.getParkingZone(), ticket2.getBeginParking(), ticket2.getEndParking()));
//        ticket3.setTicketAmount(financeManagerTest.getTicketAmount(ticket3.getParkingZone(), ticket3.getBeginParking(), ticket3.getEndParking()));
//        ticket4.setTicketAmount(financeManagerTest.getTicketAmount(ticket4.getParkingZone(), ticket4.getBeginParking(), ticket4.getEndParking()));
//        ticket5.setTicketAmount(financeManagerTest.getTicketAmount(ticket4.getParkingZone(), ticket5.getBeginParking(), ticket5.getEndParking()));
//        ticket6.setTicketAmount(financeManagerTest.getTicketAmount(ticket6.getParkingZone(), ticket6.getBeginParking(), ticket6.getEndParking()));
//        ticket9.setTicketAmount(financeManagerTest.getTicketAmount(ticket9.getParkingZone(), ticket9.getBeginParking(), ticket9.getEndParking()));
//        ticket7.setTicketAmount(financeManagerTest.getTicketAmount(ticket7.getParkingZone(), ticket7.getBeginParking(), ticket7.getEndParking()));
//
//        ticketDb.addTicketToDb(ticket9);
//        ticketDb.addTicketToDb(ticket1);
//        ticketDb.addTicketToDb(ticket2);
//        ticketDb.addTicketToDb(ticket3);
//        ticketDb.addTicketToDb(ticket4);
//        ticketDb.addTicketToDb(ticket5);
//        ticketDb.addTicketToDb(ticket6);
//        ticketDb.addTicketToDb(ticket7);

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
                    // zoneDb.getParkingZoneList().forEach(System.out::println);
                    // userFinanceFactory.getUsersFromFile().forEach(System.out::println);
                    // userDbManager.getAllUsers().forEach(System.out::println);
                    System.out.println("=======");
                    //ticketDb.getParkingTicketsDb().forEach(System.out::println);
                    break;
                case "4":
                    break;
                case "5":
                    User manager = new User("Manager", "Manager", "Manager", "Manager");
                    manager.setRole(UserRole.MANAGER_ROLE);
                    userDbManager.addUserToDb(manager);
                    break;
                case "6":
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Prasome pasirinkti is meniu opciju");
            }
        }
    }

    private void startMenu() {
        System.out.println("[1] Registruotis");
        System.out.println("[2] Prisijungti prie sistemos");
        System.out.println("[3] Test in");
        System.out.println("[4] Test out");
        System.out.println("[5] Test emun");
        System.out.println("[6] Test");
        System.out.println("[7] Iseiti");
    }

    private void customerMenu() {
        System.out.println("[1] Parkomatas");
        System.out.println("[2] Pradeti parkavima");
        System.out.println("[3] Baigti parkavima");
        System.out.println("[4] Isideti pinigu i pinigine");
        System.out.println("[5] Sumoketi bauda");
        System.out.println("[6] Pakeisti masinos numeri");
        System.out.println("[7] Perziureti savo stovejimo talonu istorija");
        System.out.println("[8] Atsijungti");
    }

    private void companyMenu() {
        System.out.println("[1] Viso surinkta pinigu");
        System.out.println("[2] Talonai pagal miestus");
        System.out.println("[3] Talonai pagal zonas");
        System.out.println("[4] Talonai pagal dienas");
        System.out.println("[5] Bendra"); // kiekiai pagal miestus, zonas, suma, vidurkis
        System.out.println("[7] Talonu kiekis ir vidurkis pagal miesta");
        System.out.println("[7] Talonu kiekis ir vidurkis pagal zona");
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
                    // baigti parkavima
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
                    // perziureti savo talonus
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
                    System.out.println("Imones saskaitoje siuo mentu yra:" + companyAccount.getCompanyAccount());
                    break;
                case "2":
                    getCityForStatistic();
                    break;
                case "3":
                    getZoneForStatistic();
                    break;
                case "4":
                    // talonai pagal dienas
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
        userDbManager.changeUserCarNumber(user, carNumberToChange);
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

        } catch (UserDontHaveOpenParkingTicket e) {
            System.out.println("Jus neturi aktyviu parkavimo talonu");
        }
    }

    private ParkingCity chooseCityForParking() {
        String pasirinmimas = "";
        boolean wrongChoose = true;
        String cityName = "";

        while (wrongChoose) {
            printCities();
            pasirinmimas = sc.nextLine();

            if (pasirinmimas.equals("1")) {
                ParkingCity vilnius = new ParkingVilnius();
                wrongChoose = false;
                return vilnius;
            }
            if (pasirinmimas.equals("2")) {
                ParkingCity kaunas = new ParkingKaunas();
                wrongChoose = false;
                return kaunas;
            }
            if (pasirinmimas.equals("3")) {
                ParkingCity klaipeda = new ParkingKlaipeda();
                wrongChoose = false;
                return klaipeda;
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

    private ParkingTime chooseParkingTime() {
        String pasirinkimas = "";
        boolean wrongChoose = true;
        ParkingTime parkingTime = null;
        // LocalDateTime parkingTimePay = LocalDateTime.now();

        while (wrongChoose) {
            System.out.println("[1] Moketi uz 30min");
            System.out.println("[2] Moketi uz 60min");
            System.out.println("[3] Moketi uz 120min");
            pasirinkimas = sc.nextLine();

            switch (pasirinkimas) {
                case "1":
                    parkingTime = ParkingTime.HALF_HOUR;
                    // parkingTimePay = parkingTimePay.plusMinutes(30);
                    wrongChoose = false;
                    break;
                case "2":
                    parkingTime = ParkingTime.ONE_HOUR;
                    // parkingTimePay = parkingTimePay.plusHours(1);
                    wrongChoose = false;
                    break;
                case "3":
                    parkingTime = ParkingTime.TWO_HOUR;
                    // parkingTimePay = parkingTimePay.plusHours(2);
                    wrongChoose = false;
                    break;
                default:
                    System.out.println("Kol kas sistema leidzia pasirinkti tik is siu laiku");
                    break;
            }
        }
        return parkingTime;
    }

    private void parkingMachine(User user) {
        ParkingCity parkingCity = chooseCityForParking();
        ParkingZone parkingZone = chooseZoneForParking(parkingCity);
        ParkingTime parkingTime = chooseParkingTime();

        LocalDateTime now = LocalDateTime.now();
        if (parkingTime.equals(ParkingTime.HALF_HOUR)) now = now.plusMinutes(30);
        if (parkingTime.equals(ParkingTime.ONE_HOUR)) now = now.plusHours(1);
        if (parkingTime.equals(ParkingTime.TWO_HOUR)) now = now.plusHours(2);

        try {
            financeMng.checkIfEnoughMoneyInUserAccountForParkingTicket(parkingZone, LocalDateTime.now(), now, user);
            ParkingTicket tempTicket = new ParkingTicket(parkingCity.getName(), parkingZone, user);
            tempTicket.setEndParking(now);
            tempTicket.setRecordStatus(ParkingRecordStatus.PAID);
            financeMng.chargeMoneyForParking(tempTicket, user, companyAccount);
        } catch (InsufficientFunds e) {
            System.out.println("Nepakanka lesu sumoketi uz stovejima");
        }
    }

    private void getCityForStatistic() {
        LocalDate dateFrom = getDateForStatistic("pradzios");
        LocalDate dateTo = getDateForStatistic("pabaigos");

        String pasirinkimas = "";
        System.out.println("Pagal koki miesta norite gauti parkavimo talonu statistika");
        printCities();
        pasirinkimas = sc.nextLine();

        switch (pasirinkimas) {
            case "1":
                testStatistic(dateFrom, dateTo, VILNIUS);
                // ticketsByCity(dateFrom, dateTo, VILNIUS);
                break;
            case "2":
                ticketsByCity(dateFrom, dateTo, KAUNAS);
                break;
            case "3":
                ticketsByCity(dateFrom, dateTo, KLAIPEDA);
                break;
            default:
                System.out.println("Pasirinkime yra tik isvardinti miestai");
                break;
        }
    }

    private void orderParking(User user) {

    }

    private void ticketsByZone1(String zoneName) {
        ParkingStatisticByGroupsService statisticMgn = new ParkingStatisticByZone();
        List<ParkingTicket> ticketsByZone = statisticMgn.getTicketListByGroup(zoneName);

        for (ParkingTicket ticket : ticketsByZone) {
            System.out.println(ticket);
        }
    }

    private void getZoneForStatistic() {
        String pasirinkimas = "";
        System.out.println("Pagal koke zona norite gauti parkavimo talonu statistika");
        printZones();
        pasirinkimas = sc.nextLine();

        switch (pasirinkimas) {
            case "1":
                ticketsByZone1(GREEN_ZONE);
                break;
            case "2":
                ticketsByZone1(RED_ZONE);
                break;
            case "3":
                ticketsByZone1(BLUE_ZONE);
                break;
            default:
                System.out.println("Galimi zonu pasirininkimai tik is pateiktu");
                break;
        }
    }

    private void ticketsByCity(LocalDate dateFrom, LocalDate dateTo, String city) {
        List<ParkingTicket> filteredTicketList = ticketDb.getParkingTicketsDb().stream()
                .filter(ticket -> ticket.getBeginParking().toLocalDate().isAfter(dateFrom.minusDays(1)))
                .filter(ticket -> ticket.getEndParking().toLocalDate().isBefore(dateTo.plusDays(1)))
                .filter(ticket -> ticket.getParkingCity().equals(city))
                .collect(Collectors.toList());

        if (filteredTicketList.isEmpty()) {
            System.out.println("Pagal uzklausa talonu nerasta");
        } else {
            filteredTicketList.forEach(System.out::println);
        }
    }

    private LocalDate getDateForStatistic(String endOrBegan) {
        System.out.println("Iveskite " + endOrBegan + " laika (Formatas yyyy-MM-dd)");
        String dateForStatisticString = sc.nextLine();
        LocalDate dateForStatistic = null;

        try {
            dateForStatistic = LocalDate.parse(dateForStatisticString, dtf);
        } catch (DateTimeParseException e) {
            System.out.println("Blogai ivesta data, teisingas datos ivedimo pvz 2021-02-14");
        }
        return dateForStatistic;
    }

    private void testStatistic(LocalDate dateFrom, LocalDate dateTo, String city) {
        ParkingStatisticByGroupsService statisticMng = new ParkingStatisticByCity();
        List<ParkingTicket> ticketList = statisticMng.getTicketsListByGroupAndDate(dateFrom, dateTo, city);

        if (ticketList.isEmpty()) {
            System.out.println("Nera talonu");
        } else {
            ticketList.forEach(System.out::println);
        }
    }
}

