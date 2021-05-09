package Tests;

import Dashboard.DashboardController;
import Dashboard.DashboardModel;
import Dashboard.DashboardView;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DashboardControllerTest {

    @Test
    public void testLoadFile(){

        JFXPanel jfxPanel = new JFXPanel();
        DashboardModel model = new DashboardModel();
        DashboardView view = new DashboardView();
        DashboardController controller = new DashboardController(model, view);

        Assertions.assertNotNull(model.getTestsOverTimeData());
        Assertions.assertNotNull(model.getTestsByRegionsOverTimeData());
        Assertions.assertNotNull(model.getDeathsOverTimeData());
        Assertions.assertNotNull(model.getNewlyAdmittedOverTimeData());
        Assertions.assertNotNull(model.getRegionSummary());
        Assertions.assertNotNull(model.getCasesByAgeData());
        Assertions.assertNotNull(model.getCasesBySexData());











    }

}