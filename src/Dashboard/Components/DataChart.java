package Dashboard.Components;

import Dashboard.Components.ChartConfigurations;
import Dashboard.Components.DataFile;
import Dashboard.DashboardModel;
import com.github.kilianB.MultiTypeChart;
import com.github.kilianB.TypedSeries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import org.junit.platform.commons.util.AnnotationUtils;

// MultiTypeChart is a component found on GitHub:
// https://github.com/KilianB/JavaFXMultiChart

public class DataChart {

    public DataChart(){
    }

    public Chart initializeTimeChart(ChartConfigurations chartConfiguration){

        TypedSeries<String,Number> cumulativeSeries = TypedSeries.<String,Number>
                builder(chartConfiguration.getLegendTwoTitle()).area()
                .withYAxisIndex(0)
                .withYAxisSide(Side.LEFT)
                .build();

        TypedSeries<String,Number> dailySeries = TypedSeries.<String,Number>
                builder(chartConfiguration.getLegendOneTitle()).area()
                .withYAxisIndex(1)
                .withYAxisSide(Side.RIGHT)
                .build();


        DataFile dataFile = chartConfiguration.getDataFile();

        for (int i = 0; i < dataFile.getLineKeys().size() - chartConfiguration.getNumberOfTotalLines(); i++){ // Dont read last line

            String lineKey = dataFile.getLineKeys().get(i);
            chartConfiguration.addToCumulativeValue(dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData())));

            dailySeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData())));
            cumulativeSeries.addData(lineKey, chartConfiguration.getCumulativeValue());
        }

        MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

        multiTypeChart.setTitle(chartConfiguration.getTitle() + " - Danmark");
        multiTypeChart.setId(String.valueOf(chartConfiguration.getConfigurationIndex()));

        multiTypeChart.addSeries(cumulativeSeries);
        multiTypeChart.addSeries(dailySeries);

        multiTypeChart.setSeriesColor(1, 90);
        multiTypeChart.setSeriesColor(2, 50);

        return multiTypeChart;
    }

    public Chart initializeCasesByAgePieChart(ChartConfigurations chartConfiguration){

        DashboardModel data = new DashboardModel();
        DataFile casesByAge = data.getCasesByAgeData();

        // PieChart introduction found here:
        // https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < casesByAge.getLineKeys().size() - 1; i++){ // Dont read last line

            String lineKey = casesByAge.getLineKeys().get(i);
            String dataKey = casesByAge.getDataFieldKeys().get(0);

            pieChartData.add(new PieChart.Data(lineKey, casesByAge.getData().get(lineKey).get(dataKey)));
        }

        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle(chartConfiguration.getTitle() + " - Danmark");
        pieChart.setLegendVisible(false);
        return pieChart;
    }

    public Chart initializeCasesBySexPieChart(ChartConfigurations chartConfiguration){

        DataFile casesBySexData = chartConfiguration.getDataFile();

        int indexOfLast = casesBySexData.getLineKeys().size() - 1;
        String keyOfLast = casesBySexData.getLineKeys().get(indexOfLast);

        int casesWomen = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(0));
        int casesMen = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(1));
        int totalCases = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(2));

        float percentageMen = (float) casesMen / totalCases * 100;
        float percentWomen = (float) casesWomen / totalCases * 100;


        // PieChart introduction found here:
        // https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.add(new PieChart.Data("Men", percentageMen));
        pieChartData.add(new PieChart.Data("Women", percentWomen));

        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle(chartConfiguration.getTitle() + " - Danmark");
        pieChart.setLegendVisible(false);
        return pieChart;
    }

    public Chart createUpdatedTimeChart(ChartConfigurations chartConfiguration, String municipality, int startIndex){

        TypedSeries<String,Number> cumulativeSeries = TypedSeries.<String,Number>
                builder(chartConfiguration.getLegendTwoTitle()).area()
                .withYAxisIndex(0)
                .withYAxisSide(Side.LEFT)
                .build();

        TypedSeries<String,Number> dailySeries = TypedSeries.<String,Number>
                builder(chartConfiguration.getLegendOneTitle()).area()
                .withYAxisIndex(1)
                .withYAxisSide(Side.RIGHT)
                .build();


        DataFile dataFile = chartConfiguration.getDataFile();

        String dataKey = "";
        if ((chartConfiguration == ChartConfigurations.MunicipalityPositive || chartConfiguration == ChartConfigurations.MunicipalityTested) && municipality != "Danmark"){
            dataKey = municipality;
        }
        else {
            dataKey = dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData());
        }

        for (int i = startIndex; i < dataFile.getLineKeys().size() - chartConfiguration.getNumberOfTotalLines(); i++){ // Dont read last line

            String lineKey = dataFile.getLineKeys().get(i);
            chartConfiguration.addToCumulativeValue(dataFile.getData().get(lineKey).get(dataKey));

            dailySeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataKey));
            cumulativeSeries.addData(lineKey, chartConfiguration.getCumulativeValue());
        }

        MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

        if (chartConfiguration == ChartConfigurations.MunicipalityPositive || chartConfiguration == ChartConfigurations.MunicipalityTested){
            multiTypeChart.setTitle(chartConfiguration.getTitle() + " - " + municipality);
        }
        else {
            multiTypeChart.setTitle(chartConfiguration.getTitle() + " - Danmark");
        }

        multiTypeChart.setId(String.valueOf(chartConfiguration.getConfigurationIndex()));

        multiTypeChart.addSeries(cumulativeSeries);
        multiTypeChart.addSeries(dailySeries);

        multiTypeChart.setSeriesColor(1, 90);
        multiTypeChart.setSeriesColor(2, 50);

        return multiTypeChart;
    }
}
