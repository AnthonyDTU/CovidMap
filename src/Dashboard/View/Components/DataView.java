package Dashboard.View.Components;

import javafx.scene.chart.Chart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.List;

public class DataView {
    VBox mainLayout;
    ScrollPane KPIArea;
    ScrollPane chartsArea;
    List<String> chartsKeys;
    HashMap<String, Chart> charts;
    List<String> KPIFieldKeys;
    HashMap<String, KPIField> KPIFields;

    public DataView(VBox mainLayout, ScrollPane KPIArea, ScrollPane chartsArea, List<String> chartsKeys, HashMap<String, Chart> charts, List<String> KPIFieldKeys, HashMap<String, KPIField> KPIFields){
        this.mainLayout = mainLayout;
        this.KPIArea = KPIArea;
        this.chartsArea = chartsArea;
        this.chartsKeys = chartsKeys;
        this.charts = charts;
        this.KPIFieldKeys = KPIFieldKeys;
        this.KPIFields = KPIFields;
    }

    public VBox getMainLayout(){
        return mainLayout;
    }

    public List<String> getChartsKeys(){
        return chartsKeys;
    }

    public HashMap<String, Chart> getCharts(){
        return charts;
    }

    public List<String> getKPIFieldKeys(){
        return KPIFieldKeys;
    }

    public HashMap<String, KPIField> getKPIFields(){
        return KPIFields;
    }

    public void updateDataField(String key, String title, String value){

        KPIFields.get(key).setPrimaryTitle(title);
        KPIFields.get(key).setPrimaryValue(value);
    }
}


