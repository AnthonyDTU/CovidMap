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


    public DataView(VBox mainLayout, HBox headerBar, ComboBox timePeriodComboBox, ComboBox regionComboBox, VBox chartsArea, List<ChartConfigurations> chartsKeys, HashMap<ChartConfigurations, Chart> charts){
        this.mainLayout = mainLayout;
        this.headerBar = headerBar;
        this.timePeriodComboBox = timePeriodComboBox;
        this.regionComboBox = regionComboBox;
        this.chartsArea = chartsArea;
        this.chartsKeys = chartsKeys;
        this.charts = charts;
    }

    public VBox getMainLayout(){
        return mainLayout;
    }
    public void setMainLayout(VBox mainLayout) { this.mainLayout = mainLayout; }

    public HBox getHeaderBar() { return headerBar; }
    public void setHeaderBar(HBox headerBar) { this.headerBar = headerBar; }

    public String getTimePeriodSelected(){
        return (String) timePeriodComboBox.getValue();
    }

    public String getRegionSelected(){
        return (String) regionComboBox.getValue();
    }

    public List<ChartConfigurations> getChartsKeys(){
        return chartsKeys;
    }

    public HashMap<ChartConfigurations, Chart> getCharts(){
        return charts;
    }

    public VBox getChartsArea(){
        return chartsArea;
    }
    public void setChartsArea(VBox chartsArea) { this.chartsArea = chartsArea; }


    public void addEventHandlerToComboBox(EventHandler<ActionEvent> eventHandler){
        timePeriodComboBox.setOnAction(eventHandler);
        regionComboBox.setOnAction(eventHandler);
    }

    public void ReloadCharts(ScrollPane newChartArea){
        chartsArea.getChildren().clear();
        chartsArea.getChildren().add(newChartArea);
    }

    public void ClearChartsArea(){
        charts.clear();
        chartsKeys.clear();
        chartsArea.getChildren().clear();


    }



}


