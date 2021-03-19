package org.example.tomasBarauskas;

import org.example.tomasBarauskas.exception.finance.InsufficientFunds;
import org.example.tomasBarauskas.exception.userDataBase.NoUserInDbByID;
import org.example.tomasBarauskas.exception.userDataBase.RegistrationIdAlreadyExist;
import org.example.tomasBarauskas.exception.userDataBase.WrongLogInPassword;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.ParkingTicket;
import org.example.tomasBarauskas.model.parking.ParkingTime;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingCity;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingKaunas;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingKlaipeda;
import org.example.tomasBarauskas.model.parking.parkingCity.ParkingVilnius;
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
import org.example.tomasBarauskas.service.userDbManager.UserDbManagerImpl;
import org.example.tomasBarauskas.model.user.ManagerUser;
import org.example.tomasBarauskas.model.user.RegularUser;
import org.example.tomasBarauskas.model.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static App main = new App();
    private static Scanner sc = new Scanner(System.in);
    private static UserDbManagerImpl dbManager = new UserDbManagerImpl();
    private static ParkingZoneDb zoneDb = new ParkingZoneDbImpl();
    private static ParkingTicketDbManager ticketDb = new ParkingTicketDbManagerImpl();
    private static CompanyAccount companyAccount = new CompanyAccount();
    private static FinanceManager financeMng = new FinanceManagerImpl();
    private static final String SING_OUT_REGULAR_USER = "5";
    private static final String SING_OUT_MANAGER_USER = "11";
    private static final String EXIT_PROGRAM = "4";
    private static final String KLAIPEDA = "Klaipeda";
    private static final String VILNIUS = "Vilnius";
    private static final String KAUNAS = "Kaunas";
    private static final String RED_ZONE = "Raudona zona";
    private static final String BLUE_ZONE = "Melina zona";
    private static final String GREEN_ZONE = "Zalia zona";

    public static void main(String[] args) {

        User tomasRegular = new RegularUser("Banis", "Banis", "Tomas", "Barauskas", "ABC123");
        User manager = new ManagerUser("Test", "Test", "Manager", "Manager");
        User andrius = new RegularUser("Butas", "Geras", "Andrius", "Pavarde", "ASD 999");
        dbManager.addUserToDb(tomasRegular);
        dbManager.addUserToDb(manager);
        dbManager.addUserToDb(andrius);
        ParkingCity vilnius = new ParkingVilnius();
        ParkingCity kaunas = new ParkingKaunas();
        ParkingCity klaipeda = new ParkingKlaipeda();

        ParkingTicket ticket9 = new ParkingTicket(vilnius.getName(), ParkingZone.VILNIUS_BLUE_ZONE, ParkingTime.ONE_HOUR, "AXW 555");
        ParkingTicket ticket1 = new ParkingTicket(vilnius.getName(), ParkingZone.VILNIUS_RED_ZONE, ParkingTime.TWO_HOUR, "XXX 555");
        ParkingTicket ticket4 = new ParkingTicket(vilnius.getName(), ParkingZone.VILNIUS_RED_ZONE, ParkingTime.TWO_HOUR, "XXX 555");
        ParkingTicket ticket5 = new ParkingTicket(vilnius.getName(), ParkingZone.VILNIUS_RED_ZONE, ParkingTime.HALF_HOUR, "GTR 555");
        ParkingTicket ticket2 = new ParkingTicket(kaunas.getName(), ParkingZone.KAUNAS_GREEN_ZONE, ParkingTime.ONE_HOUR, "YYY 666");
        ParkingTicket ticket6 = new ParkingTicket(kaunas.getName(), ParkingZone.KAUNAS_GREEN_ZONE, ParkingTime.ONE_HOUR, "YYY 666");
        ParkingTicket ticket7 = new ParkingTicket(kaunas.getName(), ParkingZone.KAUNAS_BLUE_ZONE, ParkingTime.ONE_HOUR, "YYY 666");
        ParkingTicket ticket3 = new ParkingTicket(klaipeda.getName(), ParkingZone.KLAIPEDA_RED_ZONE, ParkingTime.HALF_HOUR, "555 TTT");

        ticketDb.addTicketToDb(ticket9);
        ticketDb.addTicketToDb(ticket1);
        ticketDb.addTicketToDb(ticket2);
        ticketDb.addTicketToDb(ticket3);
        ticketDb.addTicketToDb(ticket4);
        ticketDb.addTicketToDb(ticket5);
        ticketDb.addTicketToDb(ticket6);
        ticketDb.addTicketToDb(ticket7);

        String ivestis = "";

        while (!ivestis.equals(EXIT_PROGRAM)) {
            main.startMenu();
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    main.regularUserRegistration();
                    break;
                case "2":
                    try {
                        main.logInOptions();
                    } catch (NoUserInDbByID e) {
                        System.out.println("Blogi prisijungimo duomenys");
                    }
                    break;
                case "3":
                    for (ParkingTicket ticket : ticketDb.getParkingTicketsDb()) {
                        System.out.println(ticket);
                    }
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Prasome pasirinkti is meniu opciju");
            }
        }
    }

    private void startMenu() {
        System.out.println("[1] Registruotis");
        System.out.println("[2] Prisijungti prie sistemos");
        System.out.println("[3] Test");
        System.out.println("[4] Iseiti");
    }

    private void customerMenu() {
        System.out.println("[1] Susimoketi uz automobilio stovejima");
        System.out.println("[2] Isideti pinigu i pinigine");
        System.out.println("[3] Sumoketi bauda");
        System.out.println("[4] Pakeisti masinos numeri");
        System.out.println("[5] Atsijungti");
    }

    private void companyMenu() {
        System.out.println("[1] Viso surinkta pinigu");
        System.out.println("[2] Ataskaita pagal miestus");
        System.out.println("[3] Ataskaita pagal zonas");
        System.out.println("[4] Ataskaita pagal miestus ir zonas");
        System.out.println("[5] Ataskaita pagal stovejimo laika");
        System.out.println("[6] Israsyta baudu");
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
    private void printZones(){
        System.out.println("[1] Zalia zona");
        System.out.println("[2] Raudona zona");
        System.out.println("[3] Melina zona");
    }

    private void regularUserRegistration() {
        System.out.println("Kokio vartotojo ID noretumet?");
        String userIdToCheck = sc.nextLine();
        try {
            dbManager.registrationCheckIfIdExist(userIdToCheck);
            System.out.println("Koks bus Jusu slaptazodis");
            String userPassword = sc.nextLine();
            System.out.println("Jusu vardas");
            String userName = sc.nextLine();
            System.out.println("Jusu pavarde");
            String userSurname = sc.nextLine();
            System.out.println("Jusu automobilio numeris");
            String userCarNumber = sc.nextLine();

            dbManager.addUserToDb(new RegularUser(userIdToCheck, userPassword, userName, userSurname, userCarNumber));
            System.out.println("Registracija sekminga");
        } catch (RegistrationIdAlreadyExist e) {
            System.out.println(userIdToCheck + " -  sis vartotojo ID jau uzimtas");
        }
    }

    private User logInCheckUserDetails(String logInID, String loginPassword) throws NoUserInDbByID {
        User userLogIn = null;

        try {
            userLogIn = dbManager.logInCheckDetails(logInID, loginPassword);
            return userLogIn;
        } catch (NoUserInDbByID | WrongLogInPassword e) {
        }
        throw new NoUserInDbByID();
    }

    private void logInOptions() throws NoUserInDbByID {
        System.out.println("Iveskite savo ID varda");
        String logInID = sc.nextLine();
        System.out.println("Iveskite savo slaptazodi");
        String loginPassword = sc.nextLine();

        User userToLogIn = logInCheckUserDetails(logInID, loginPassword);
        if (userToLogIn.getRole().equals(UserRole.REGULAR_ROLE)) {
            regularUserFunctionality(userToLogIn);

        } else if (userToLogIn.getRole().equals(UserRole.MANAGER_ROLE)) {
            companyFunctionality(userToLogIn);
        }
    }

    private void regularUserFunctionality(User regularUser) {
        String ivestis = "";
        while (!ivestis.equals(SING_OUT_REGULAR_USER)) {
            customerMenu();
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    main.payForParking((RegularUser) regularUser);
                    break;
                case "2":
                    main.putMoneyInToAccount((RegularUser) regularUser);
                    break;
                case "3":
                    // sumoketi bauda
                    break;
                case "4":
                    main.changeCarNumber((RegularUser) regularUser);
                    break;
                case "5":
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
                    main.getCityForStatistic();
                    break;
                case "3":
                    // pagal zonas
                    break;
                case "4":
                    // pagal miestus ir zonas
                    break;
                case "5":
                    // pagal laika
                    break;
                case "6":
                    // israsyta baudu
                    break;
                case "7":
                    // apmoketos - neapmoketos baudos
                    break;
                case "8":
                    main.changePricePerHour();
                    break;
                case "9":
                    main.changeFineAmount();
                    break;
                case "10":
                    main.singUpManager();
                    break;
                case "11":
                    break;
                default:
                    System.out.println("Prasom pasirinkti is meniu opciju");
                    break;
            }
        }
    }

    private void putMoneyInToAccount(RegularUser user) {
        boolean isNumber = true;

        while (isNumber) {
            System.out.println("Kiek pinigu noretumet ideti i saskaita");
            String putAmountString = sc.nextLine();

            try {
                float putAmount = Float.parseFloat(putAmountString);
                BigDecimal putAmountBigDecimal = BigDecimal.valueOf(putAmount);
                BigDecimal amountInUserCount = user.getCount();
                user.setCount(putAmountBigDecimal.add(amountInUserCount));
                isNumber = false;
            } catch (NumberFormatException e) {
                System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, naudokite taska \".\" vietoje kablelio");
            }
        }
    }

    private void changeCarNumber(RegularUser user) {
        System.out.println("Kokie nauji Jusu automobilio numeriai");
        String carNumberToChange = sc.nextLine();
        user.setCarNumber(carNumberToChange);
    }

    private void singUpManager() {
        System.out.println("Koks bus naujo tarnautojo ID?");
        String singUpManagerId = sc.nextLine();

        try {
            dbManager.registrationCheckIfIdExist(singUpManagerId);
            System.out.println("Koks bus slaptazodis");
            String singUpManagerPassword = sc.nextLine();
            System.out.println("Tarnautojo vardas");
            String singUpManagerName = sc.nextLine();
            System.out.println("Tarnatojo pavarde");
            String singUpManagerSurname = sc.nextLine();

            dbManager.addUserToDb(new ManagerUser(singUpManagerId, singUpManagerPassword, singUpManagerName, singUpManagerSurname));

        } catch (RegistrationIdAlreadyExist e) {
            System.out.println(singUpManagerId + " - sis ID jau uzmintas");
        }

    }

    private void changePricePerHour() {
        List<ParkingZone> zoneDbList = zoneDb.getParkingZoneList();
        System.out.println("Kurios zonos ikaini norite pakeisti, iveskite zonos numeri");

        for (int i = 0; i < zoneDbList.size(); i++) {
            System.out.println(i + ". " + zoneDbList.get(i));
        }

        int zoneNumberToChange = sc.nextInt();
        sc.nextLine();
        System.out.println("Dabartine " + zoneDbList.get(zoneNumberToChange) + " kaina yra " +
                zoneDbList.get(zoneNumberToChange).getCostPerHour() + " kokia bus nauja kaina?");
        String zonePricePerHourNewString = sc.nextLine();

        try {
            float zonePricePerHourNew = Float.parseFloat(zonePricePerHourNewString);
            BigDecimal zonePricePerHourNewBidDecimal = BigDecimal.valueOf(zonePricePerHourNew);
            zoneDbList.get(zoneNumberToChange).setCostPerHour(zonePricePerHourNewBidDecimal);
            System.out.println("Kaina sekmingai pakeista");
        } catch (NumberFormatException e) {
            System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, " +
                    "naudokite taska \".\" vietoje kablelio");
        }
    }

    private void changeFineAmount() {
        List<ParkingZone> zoneDbList = zoneDb.getParkingZoneList();
        System.out.println("Kurios zonos baudos dydi norite pakeisti, iveskite numeri");

        for (int i = 0; i < zoneDbList.size(); i++) {
            System.out.println(i + ". " + zoneDbList.get(i));
        }

        int zoneNumberToChange = sc.nextInt();
        sc.nextLine();
        System.out.println(zoneDbList.get(zoneNumberToChange) + " baudos dydis yra " +
                zoneDbList.get(zoneNumberToChange).getFine() + " koks naujas baudos dydis?");
        String zonePricePerHourNewString = sc.nextLine();

        try {
            float zonePricePerHourNew = Float.parseFloat(zonePricePerHourNewString);
            BigDecimal zonePricePerHourNewBigD = BigDecimal.valueOf(zonePricePerHourNew);
            zoneDbList.get(zoneNumberToChange).setCostPerHour(zonePricePerHourNewBigD);
            System.out.println("Kaina sekmingai pakeista");
        } catch (NumberFormatException e) {
            System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, " +
                    "naudokite taska \".\" vietoje kablelio");
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
                main.printZones();
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

        while (wrongChoose) {
            System.out.println("[1] Moketi uz 30min");
            System.out.println("[2] Moketi uz 60min");
            System.out.println("[3] Moketi uz 120min");
            pasirinkimas = sc.nextLine();

            switch (pasirinkimas) {
                case "1":
                    parkingTime = ParkingTime.HALF_HOUR;
                    wrongChoose = false;
                    break;
                case "2":
                    parkingTime = ParkingTime.ONE_HOUR;
                    wrongChoose = false;
                    break;
                case "3":
                    parkingTime = ParkingTime.TWO_HOUR;
                    wrongChoose = false;
                    break;
                default:
                    System.out.println("Kol kas sistema leidzia pasirinkti tik is siu laiku");
                    break;
            }
        }
        return parkingTime;
    }

    private void payForParking(RegularUser user) {
        ParkingCity parkingCity = chooseCityForParking();
        ParkingZone parkingZone = chooseZoneForParking(parkingCity);
        ParkingTime parkingTime = chooseParkingTime();

        try {
            financeMng.checkIfEnoughMoneyInUserAccountForParkingTicket(parkingZone, parkingTime, user);
            ParkingTicket tempTicket = new ParkingTicket(parkingCity.getName(), parkingZone, parkingTime, user.getCarNumber());
            financeMng.chargeMoneyForParking(tempTicket, user, companyAccount);
        } catch (InsufficientFunds e) {
            System.out.println("Nepakanka lesu sumoketi uz stovejima");
        }
    }

    private void ticketsByCity(String cityName) {
        ParkingStatisticByGroupsService statisticMgn = new ParkingStatisticByCity();
        List<ParkingTicket> ticketsByCity = statisticMgn.getTicketListByGroup(cityName);

        for (ParkingTicket ticket : ticketsByCity) {
            System.out.println(ticket);
        }
    }
    private void getCityForStatistic(){
        String pasirinkimas = "";
        System.out.println("Pagal koki miesta norite gauti parkavimo talonu statistika");
        printCities();
        pasirinkimas = sc.nextLine();

        switch (pasirinkimas){
            case "1":
                main.ticketsByCity(VILNIUS);
                break;
            case "2":
                main.ticketsByCity(KAUNAS);
                break;
            case "3":
                main.ticketsByCity(KLAIPEDA);
                break;
            default:
                System.out.println("Pasirinkime yra tik isvardinti miestai");
                break;
        }
    }
    private void ticketsByZone(String zoneName){
        ParkingStatisticByGroupsService statisticMgn = new ParkingStatisticByZone();
        List<ParkingTicket> ticketsByZone = statisticMgn.getTicketListByGroup(zoneName);

        for (ParkingTicket ticket : ticketsByZone){
            System.out.println(ticket);
        }
    }
    private void getZoneForStatistic(){
        String pasirinkimas = "";
        System.out.println("Pagal koke zona norite gauti parkavimo talonu statistika");
        printZones();
        pasirinkimas = sc.nextLine();

        switch (pasirinkimas){
            case "1":
                ticketsByZone(GREEN_ZONE);
                break;
            case "2":
                ticketsByZone(RED_ZONE);
                break;
            case "3":
                ticketsByZone(BLUE_ZONE);
                break;
            default:
                System.out.println("Galimi zonu pasirininkimai tik is pateiktu");
                break;
        }
    }
}
