package Dashboard.View.Components;

import javafx.scene.chart.Chart;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;

public class DataView {
    VBox mainLayout;
    List<String> chartsKeys;
    HashMap<String, Chart> charts;
    List<String> dataFieldsKeys;
    HashMap<String, DataField> dataFields;

    public DataView(VBox mainLayout, List<String> chartsKeys, HashMap<String, Chart> charts, List<String> dataFieldKeys, HashMap<String, DataField> dataFields){
        this.mainLayout = mainLayout;
        this.chartsKeys = chartsKeys;
        this.charts = charts;
        this.dataFieldsKeys = dataFieldKeys;
        this.dataFields = dataFields;
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

    public List<String> getDataFieldsKeys(){
        return dataFieldsKeys;
    }

    public HashMap<String, DataField> getDataFields(){
        return dataFields;
    }
}



class DataField{



}