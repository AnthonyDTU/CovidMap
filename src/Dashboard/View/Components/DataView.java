package Dashboard.View.Components;

import javafx.scene.chart.Chart;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;

public class DataView {
    VBox mainLayout;
    List<String> chartsKeys;
    HashMap<String, Chart> charts;
    List<String> kpiFieldKeys;
    HashMap<String, KPIField> kpiFields;

    public DataView(VBox mainLayout, List<String> chartsKeys, HashMap<String, Chart> charts, List<String> kpiFieldKeys, HashMap<String, KPIField> kpiFields){
        this.mainLayout = mainLayout;
        this.chartsKeys = chartsKeys;
        this.charts = charts;
        this.kpiFieldKeys = kpiFieldKeys;
        this.kpiFields = kpiFields;
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

    public List<String> getKpiFieldKeys(){
        return kpiFieldKeys;
    }

    public HashMap<String, KPIField> getKpiFields(){
        return kpiFields;
    }

    public void updateDataField(String key, String title, String value){

        kpiFields.get(key).setTitle(title);
        kpiFields.get(key).setValue(value);
    }
}


