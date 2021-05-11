package Dashboard.Components;

import Dashboard.DashboardModel;

public enum ChartConfigurations {

    Positive (0,"Positive Cases","Daily Positive","Total Positive",0,2),
    Tested (1,"Tested","Daily Tested","Total Tested",4,2),
    Admitted (2,"Admitted","Daily Admitted","Total Admitted",6,1),
    Deaths (3,"Deaths","Daily Deaths","Total Deaths",0,1),
    MunicipalityPositive(4,"Positive Cases","Daily Positive","Total Positive",-1,0),
    MunicipalityTested(5,"Tested","Daily Tested","Total Tested",-1,0),
    ByAge (6,"Cases By Age", "N/A", "N/A", 0,1),
    BySex (7,"Cases By Sex", "N/A", "N/A", 0,1);

    private int configurationIndex;
    private String title;
    private String legendOneTitle;
    private String legendTwoTitle;
    private int indexOfData;
    private int numberOfTotalLines;
    private int cumulativeValue;

    ChartConfigurations(int configurationIndex, String title, String legendOneTitle, String legendTwoTitle, int indexOfData, int numberOfTotalLines){
        this.configurationIndex = configurationIndex;
        this.title = title;
        this.legendOneTitle = legendOneTitle;
        this.legendTwoTitle = legendTwoTitle;
        this.indexOfData = indexOfData;
        this.numberOfTotalLines = numberOfTotalLines;
        this.cumulativeValue = 0;
    }

    // Identifier for the specific configuration
    public int getConfigurationIndex(){
        return configurationIndex;
    }

    // Title of the chart, which is displayed to the user
    public String getTitle(){
        return title;
    }

    // Title of the first data series
    public String getLegendOneTitle(){
        return legendOneTitle;
    }

    // Title of the second data series
    public String getLegendTwoTitle(){
        return legendTwoTitle;
    }

    // Index of the data field the specific chart configuration needs to read from in the file
    public int getIndexOfData(){
        return indexOfData;
    }

    // Specifies how many lines (total lines) the chart should exclude from the bottom of the file.
    public int getNumberOfTotalLines(){
        return numberOfTotalLines;
    }

    // Keep a running cumulative value for when initializing the chart.
    public void addToCumulativeValue(int value){
        cumulativeValue += value;
    }

    // Read the cumulative value
    public int getCumulativeValue(){
        return cumulativeValue;
    }

    // Reset the cumulative value
    public void resetCumulativeValue(){ cumulativeValue = 0; }

    // Get the dataFile needed for the specific chart configuration
    public DataFile getDataFile(DashboardModel data){

        if (this == Positive){
            return data.getTestsOverTimeData();
        }
        else if (this == Tested){
            return data.getTestsOverTimeData();
        }
        else if (this == MunicipalityPositive){
            return data.getMunicipalityPositiveOverTime();
        }
        else if (this == MunicipalityTested){
            return data.getMunicipalityTestedOverTime();
        }
        else if (this == Admitted){
            return data.getNewlyAdmittedOverTimeData();
        }
        else if (this == Deaths){
            return data.getDeathsOverTimeData();
        }
        else if (this == ByAge){
            return data.getCasesByAgeData();
        }
        else {
            return data.getCasesBySexData();
        }
    }
}
