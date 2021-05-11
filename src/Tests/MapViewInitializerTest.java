package Tests;

import Dashboard.Components.DataFile;
import Dashboard.Components.MapView;
import Dashboard.DashboardModel;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapViewInitializerTest {

    private static final String regionSummaryFilename = "Covid Data/Region_summary.csv";

    @BeforeAll
    static void loadFiles(){

    }

    @Test
    void initializeMapView() {

        JFXPanel jfxPanel = new JFXPanel();

        DashboardModel model = new DashboardModel();
        model.setTestsOverTimeData(new DataFile().LoadFile(regionSummaryFilename));

        MapView mapView = new MapView().InitializeMapView(model);
        assertEquals(5, mapView.getRegionButtons().size());
        assertEquals(5, mapView.getRegionButtonKeys().size());
        assertEquals(4, mapView.getKPIFields().size());
        assertNotNull(mapView.getImageView().getImage());
        assertNotNull(mapView.getKPIHeaderLabel());

    }
}