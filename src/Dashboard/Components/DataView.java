package Dashboard.Components;

import Dashboard.ComponentIntializers.DataViewInitializer;
import Dashboard.DashboardModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.Chart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataView {
    private VBox mainLayout;
    private HBox headerBar;
    private ComboBox timePeriodComboBox;
    private ComboBox municipalityComboBox;
    private VBox chartsArea;
    private List<ChartConfigurations> chartsKeys;
    private HashMap<ChartConfigurations, Chart> charts;

    private final int mainLayoutWidth = 700;
    private final int headerBarHeight = 150;
    private final int comboBoxWidth = 250;


    public DataView(){
        this.mainLayout = new VBox();
        this.headerBar = new HBox();
        this.timePeriodComboBox = new ComboBox();
        this.municipalityComboBox = new ComboBox();
        this.chartsArea = new VBox();
        this.chartsKeys = new ArrayList<>();
        this.charts = new HashMap<>();
    }

    // This function needs to be called in order to initializer the DataView for my application
    //
    public DataView InitializeDataView(DashboardModel data){
        DataViewInitializer initializer = new DataViewInitializer();
        return initializer.InitializeDataView(data,this);
    }

    // ***********************************************************************************************************
    // Getters and setters
    // ***********************************************************************************************************

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

    public void setMunicipalityComboBox(ComboBox regionComboBox) {this.municipalityComboBox = regionComboBox; }
    public ComboBox getMunicipalityComboBox() { return municipalityComboBox; }
    public String getMunicipalitySelected(){ return (String) municipalityComboBox.getValue(); }

    public void setChartsKeys(List<ChartConfigurations> chartsKeys) { this.chartsKeys = chartsKeys;    }
    public List<ChartConfigurations> getChartsKeys(){
        return chartsKeys;
    }

    public HashMap<ChartConfigurations, Chart> getCharts(){
        return charts;
    }
    public void setCharts(HashMap<ChartConfigurations, Chart> charts) { this.charts = charts; }

    // Adds event handlers to the filter comboBoxes
    //
    public void addEventHandlerToComboBox(EventHandler<ActionEvent> eventHandler){
        timePeriodComboBox.setOnAction(eventHandler);
        municipalityComboBox.setOnAction(eventHandler);
    }

    // Updates the charts with the newly filtered ones.
    //
    public void reloadCharts(ScrollPane newChartArea){
        chartsArea.getChildren().clear();
        chartsArea.getChildren().add(newChartArea);
    }
}


