package Tests;

import Dashboard.Components.DataFile;
import Dashboard.Components.DataView;
import Dashboard.DashboardModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataViewInitializerTest {

    private static final String testsOverTimeFilename = "Covid Data/Test_pos_over_time.csv";
    private static final String testsByRegionOverTimeFilename = "Covid Data/Test_regioner.csv";
    private static final String deathsOverTimeFilename = "Covid Data/Deaths_over_time.csv";
    private static final String newlyAdmittedOverTimeFilename = "Covid Data/Newly_admitted_over_time.csv";
    private static final String casesByAgeFilename = "Covid Data/Cases_by_age.csv";
    private static final String casesBySexFilename = "Covid Data/Cases_by_sex.csv";

    private final ObservableList<String> timePeriodOptions =
            FXCollections.observableArrayList(
                    "All Time",
                    "30 Dage",
                    "7 Dage"
            );

    private final ObservableList<String> regionOptions =
            FXCollections.observableArrayList(
                    "Danmark",
                    "Nordjylland",
                    "Midtjylland",
                    "Syddanmark",
                    "Sjælland",
                    "Hovedstaden"
            );


    @Test
    void initializeDataView() {

        JFXPanel jfxPanel = new JFXPanel();

        DashboardModel model = new DashboardModel();
        model.setTestsOverTimeData(new DataFile().LoadFile(testsOverTimeFilename));
        model.setTestsByRegionsOverTimeData(new DataFile().LoadFile(testsByRegionOverTimeFilename));
        model.setDeathsOverTimeData(new DataFile().LoadFile(deathsOverTimeFilename));
        model.setNewlyAdmittedOverTimeData(new DataFile().LoadFile(newlyAdmittedOverTimeFilename));
        model.setCasesByAgeData(new DataFile().LoadFile(casesByAgeFilename));
        model.setCasesBySexData(new DataFile().LoadFile(casesBySexFilename));

        DataView dataView = new DataView().InitializeDataView(model);
        assertEquals(6, dataView.getCharts().size());
        assertEquals(6, dataView.getChartsKeys().size());
        assertEquals(timePeriodOptions, dataView.getTimePeriodComboBox().getItems());
        assertEquals(regionOptions, dataView.getMunicipalityComboBox().getItems());
    }
}