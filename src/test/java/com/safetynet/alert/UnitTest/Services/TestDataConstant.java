package com.safetynet.alert.UnitTest.Services;

import com.safetynet.alert.model.AllData;
import com.safetynet.alert.model.DTO.AddressDTO.MedicalInfos;
import com.safetynet.alert.model.DTO.AddressDTO.Resident;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDataConstant {

    public static final Person person1 = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512", "jaboyd@email.com");
    public static final Person person2 = new Person("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513", "drk@email.com");
    public static final Person person3 = new Person("Tenley","Boyd","1509 Culver St","Culver","97451","841-874-6512", "tenz@email.com");
    public static final Person person4 = new Person("Roger","Boyd","1509 Culver St","Culver","97451","841-874-6512", "jaboyd@email.com");
    public static final Person person5 = new Person("Felicia","Boyd","1509 Culver St","Culver","97451","841-874-6544", "jaboyd@email.com");
    public static final Person person6 = new Person("Jonanathan","Marrack","29 15th St","Culver","97451","841-874-6513", "drk@email.com");
    public static final ArrayList<Person> testPersonsList = new ArrayList<>(Arrays.asList(person1, person2, person3, person4, person5, person6));

    public static String medications1[] = {"aznol:350mg", "hydrapermazol:100mg"};
    public static String allergies1[] = {"nillacilan"};
    public static String medications2[] = {"pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"};
    public static String allergies2[] = {};
    public static String medications3[] = {};
    public static String allergies3[] = {"peanut"};
    public static String medications4[] = {};
    public static String allergies4[] = {};
    public static String medications5[] = {"tetracyclaz:650mg"};
    public static String allergies5[] = {"xilliathal"};
    public static String medications6[] = {};
    public static String allergies6[] = {};
    public static final MedicalRecord medicalRecord1 = new MedicalRecord("John","Boyd","03/06/1984", medications1, allergies1);
    public static final MedicalRecord medicalRecord2 = new MedicalRecord("Jacob","Boyd","03/06/1989", medications2, allergies2);
    public static final MedicalRecord medicalRecord3 = new MedicalRecord("Tenley","Boyd","02/18/2012", medications3, allergies3);
    public static final MedicalRecord medicalRecord4 = new MedicalRecord("Roger","Boyd","09/06/2017", medications4, allergies4);
    public static final MedicalRecord medicalRecord5 = new MedicalRecord("Felicia","Boyd","01/08/1986", medications5, allergies5);
    public static final MedicalRecord medicalRecord6 = new MedicalRecord("Jonanathan","Marrack","01/03/1989", medications6, allergies6);
    public static final ArrayList<MedicalRecord> testMedicalRecordsList = new ArrayList<>(Arrays.asList(medicalRecord1, medicalRecord2, medicalRecord3, medicalRecord4, medicalRecord5,  medicalRecord6));

    public static final FireStation fireStation1 = new FireStation("1509 Culver St", "3");
    public static final FireStation fireStation2 = new FireStation("29 15th St", "2");
    public static final FireStation fireStation3 = new FireStation("834 Binoc Ave", "3");
    public static final ArrayList<FireStation> testFireStationsList = new ArrayList<>(Arrays.asList(fireStation1, fireStation2, fireStation3));
    public static final ArrayList<FireStation> fireStationsListWithNumber3 = new ArrayList<>(Arrays.asList(fireStation1, fireStation3));
    public static final ArrayList<FireStation> fireStationsListWithNumber2 = new ArrayList<>(Arrays.asList(fireStation2));

    public static final AllData testData = new AllData(testPersonsList, testFireStationsList, testMedicalRecordsList);

    public static MedicalInfos medicalInfos1 = new MedicalInfos(medications1, allergies1);
    public static MedicalInfos medicalInfos2 = new MedicalInfos(medications2, allergies2);
    public static MedicalInfos medicalInfos3 = new MedicalInfos(medications3, allergies3);
    public static MedicalInfos medicalInfos4 = new MedicalInfos(medications4, allergies4);
    public static MedicalInfos medicalInfos5 = new MedicalInfos(medications5, allergies5);
    public static MedicalInfos medicalInfos6 = new MedicalInfos(medications6, allergies6);
    public static Resident resident1 = new Resident("John","Boyd","841-874-6512", 39, medicalInfos1 );
    public static Resident resident2 = new Resident("Jacob","Boyd","841-874-6513", 34, medicalInfos2 );
    public static Resident resident3 = new Resident("Tenley","Boyd","841-874-6512", 11, medicalInfos3 );
    public static Resident resident4 = new Resident("Roger","Boyd","841-874-6512", 6, medicalInfos4 );
    public static Resident resident5 = new Resident("Felicia","Boyd","841-874-6544", 36, medicalInfos5 );
    public static Resident resident6 = new Resident("Jonanathan","Marrack","841-874-6513", 39, medicalInfos6 );
    public static ArrayList<Resident> residents1 = new ArrayList<>(Arrays.asList(resident1, resident2, resident3, resident4, resident5));
    public static ArrayList<Resident> residents2 = new ArrayList<>(Arrays.asList(resident6));
    public static ArrayList<Resident> residents3 = new ArrayList<>();
}
