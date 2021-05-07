package Dashboard.Components;

import com.sun.glass.ui.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.Chart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.List;

public class DataView {
    VBox mainLayout;
    HBox headerBar;
    ComboBox timePeriodComboBox;
    ComboBox regionComboBox;
    VBox chartsArea;
    List<ChartConfigurations> chartsKeys;
    HashMap<ChartConfigurations, Chart> charts;

    private final int mainLayoutWidth = 700;
    private final int headerBarHeight = 150;
    private final int comboBoxWidth = 250;


    public DataView(VBox mainLayout, HBox headerBar, ComboBox timePeriodComboBox, ComboBox regionComboBox, VBox chartsArea, List<ChartConfigurations> chartsKeys, HashMap<ChartConfigurations, Chart> charts){
        this.mainLayout = mainLayout;
        this.headerBar = headerBar;
        this.timePeriodComboBox = timePeriodComboBox;
        this.regionComboBox = regionComboBox;
        this.chartsArea = chartsArea;
        this.chartsKeys = chartsKeys;
        this.charts = charts;
    }

    public int getMainLayoutWidth() { return mainLayoutWidth; }
    public int getHeaderBarHeight() { return headerBarHeight; }
    public int getComboBoxWidth() { return comboBoxWidth; }

    public void setMainLayout(VBox mainLayout) { this.mainLayout = mainLayout; }
    public VBox getMainLayout(){
        return mainLayout;
    }

    public void setHeaderBar(HBox headerBar) { this.headerBar = headerBar; }
    public HBox getHeaderBar() { return headerBar; }

    public void setChartsArea(VBox chartsArea) { this.chartsArea = chartsArea; }
    public VBox getChartsArea(){
        return chartsArea;
    }

    public void setTimePeriodComboBox(ComboBox timePeriodComboBox) { this.timePeriodComboBox = timePeriodComboBox; }
    public ComboBox getTimePeriodComboBox() { return timePeriodComboBox; }
    public String getTimePeriodSelected(){
        return (String) timePeriodComboBox.getValue();
    }

    public void setRegionComboBox(ComboBox regionComboBox) {this.regionComboBox = regionComboBox; }
    public ComboBox getRegionComboBox() { return regionComboBox; }
    public String getRegionSelected(){
        return (String) regionComboBox.getValue();
    }

    public void setChartsKeys(List<ChartConfigurations> chartsKeys) { this.chartsKeys = chartsKeys;    }
    public List<ChartConfigurations> getChartsKeys(){
        return chartsKeys;
    }

    public HashMap<ChartConfigurations, Chart> getCharts(){
        return charts;
    }
    public void setCharts(HashMap<ChartConfigurations, Chart> charts) { this.charts = charts; }

    public void addEventHandlerToComboBox(EventHandler<ActionEvent> eventHandler){
        timePeriodComboBox.setOnAction(eventHandler);
        regionComboBox.setOnAction(eventHandler);
    }

    public void reloadCharts(ScrollPane newChartArea){
        chartsArea.getChildren().clear();
        chartsArea.getChildren().add(newChartArea);
    }
}


