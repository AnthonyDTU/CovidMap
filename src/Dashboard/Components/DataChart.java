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

    // Initializes a new time chart. This is used from DataView initializer
    //
    public Chart initializeTimeChart(DashboardModel data, ChartConfigurations chartConfiguration){

        // Create the data series
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


        // Get the nesessary DateFile
        DataFile dataFile = chartConfiguration.getDataFile(data);

        // Configure the data series
        for (int i = 0; i < dataFile.getLineKeys().size() - chartConfiguration.getNumberOfTotalLines(); i++){ // Dont read last line

            String lineKey = dataFile.getLineKeys().get(i);
            chartConfiguration.addToCumulativeValue(dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData())));

            dailySeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData())));
            cumulativeSeries.addData(lineKey, chartConfiguration.getCumulativeValue());
        }

        // Create the chart
        MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

        // Add date series to the chart
        multiTypeChart.addSeries(cumulativeSeries);
        multiTypeChart.addSeries(dailySeries);

        // Configure the chart
        multiTypeChart.setTitle(chartConfiguration.getTitle() + " - Danmark");

        multiTypeChart.setSeriesColor(1, 90);
        multiTypeChart.setSeriesColor(2, 50);

        return multiTypeChart;
    }

    // Initializes the Cases By Age chart. This is called from DataViewInitializer
    public Chart initializeCasesByAgePieChart(DashboardModel data, ChartConfigurations chartConfiguration){

        DataFile casesByAge = chartConfiguration.getDataFile(data);

        // PieChart introduction found here:
        // https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Configure the data series
        for (int i = 0; i < casesByAge.getLineKeys().size() - 1; i++){ // Dont read last line

            String lineKey = casesByAge.getLineKeys().get(i);
            String dataKey = casesByAge.getDataFieldKeys().get(0);

            pieChartData.add(new PieChart.Data(lineKey, casesByAge.getData().get(lineKey).get(dataKey)));
        }

        // Create the chart
        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle(chartConfiguration.getTitle() + " - Danmark");
        pieChart.setLegendVisible(false);
        return pieChart;
    }

    public Chart initializeCasesBySexPieChart(DashboardModel data, ChartConfigurations chartConfiguration){

        // Get the nessesary DataFile
        DataFile casesBySexData = chartConfiguration.getDataFile(data);

        // Calculate the percentage value
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

        // Create the final chart
        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle(chartConfiguration.getTitle() + " - Danmark");
        pieChart.setLegendVisible(false);
        return pieChart;
    }

    public Chart createUpdatedTimeChart(DashboardModel data, ChartConfigurations chartConfiguration, String municipality, int startIndex){

        // Create the data sereies
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


        // Get the nesessary DateFile
        DataFile dataFile = chartConfiguration.getDataFile(data);


        // Fingure out what the dataKey should be, based on the the filter selection
        String dataKey;
        if ((chartConfiguration == ChartConfigurations.MunicipalityPositive || chartConfiguration == ChartConfigurations.MunicipalityTested) && !municipality.equals("Danmark")){
            dataKey = municipality;
        }
        else {
            dataKey = dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData());
        }

        // Configure the data series
        for (int i = startIndex; i < dataFile.getLineKeys().size() - chartConfiguration.getNumberOfTotalLines(); i++){ // Dont read last line

            String lineKey = dataFile.getLineKeys().get(i);
            chartConfiguration.addToCumulativeValue(dataFile.getData().get(lineKey).get(dataKey));

            dailySeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataKey));
            cumulativeSeries.addData(lineKey, chartConfiguration.getCumulativeValue());
        }

        // Create the chart
        MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

        // Add the data series
        multiTypeChart.addSeries(cumulativeSeries);
        multiTypeChart.addSeries(dailySeries);

        // Configure the chart
        if (chartConfiguration == ChartConfigurations.MunicipalityPositive || chartConfiguration == ChartConfigurations.MunicipalityTested){
            multiTypeChart.setTitle(chartConfiguration.getTitle() + " - " + municipality);
        }
        else {
            multiTypeChart.setTitle(chartConfiguration.getTitle() + " - Danmark");
        }

        multiTypeChart.setSeriesColor(1, 90);
        multiTypeChart.setSeriesColor(2, 50);

        return multiTypeChart;
    }
}
