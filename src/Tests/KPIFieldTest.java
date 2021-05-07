package Tests;

import Dashboard.Components.KPIField;
import Dashboard.Main;
import com.sun.glass.ui.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KPIFieldTest {

    @Test
    public void checkKPIField() {

        JFXPanel jfxPanel = new JFXPanel();
        KPIField kpiField = new KPIField("Title","Value");
        Assertions.assertEquals(2, kpiField.getLayout().getChildren().size());
        Assertions.assertEquals("Value", kpiField.getValueLabelText());
        kpiField.setValueLabelText("Value2");
        Assertions.assertEquals("Value2", kpiField.getValueLabelText());
    }

}

