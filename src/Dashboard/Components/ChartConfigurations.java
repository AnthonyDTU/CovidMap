package Dashboard.Components;

import Dashboard.DashboardModel;

public enum ChartConfigurations {

    Positive (0,"Positive Cases","Daily Positive","Total Positive",0,2, new DataFile()),
    Tested (1,"Tested","Daily Tested","Total Tested",4,2, new DataFile()),
    Admitted (2,"Admitted","Daily Admitted","Total Admitted",6,1, new DataFile()),
    Deaths (3,"Deaths","Daily Deaths","Total Deaths",0,1, new DataFile()),
    ByAge (4,"Cases By Age", "N/A", "N/A", 0,1,new DataFile()),
    BySex (5,"Cases By Sex", "N/A", "N/A", 0,1,new DataFile());

    private int configurationIndex;
    private String title;
    private String legendOneTitle;
    private String legendTwoTitle;
    private int indexOfData;
    private int numberOfTotalLines;
    private int cumulativeValue;
    private DataFile dataFile;

    ChartConfigurations(int configurationIndex, String title, String legendOneTitle, String legendTwoTitle, int indexOfData, int numberOfTotalLines, DataFile dataFile){
        this.configurationIndex = configurationIndex;
        this.title = title;
        this.legendOneTitle = legendOneTitle;
        this.legendTwoTitle = legendTwoTitle;
        this.indexOfData = indexOfData;
        this.numberOfTotalLines = numberOfTotalLines;
        this.dataFile = dataFile;
        this.cumulativeValue = 0;

    }

    public int getConfigurationIndex(){
        return configurationIndex;
    }

    public String getTitle(){
        return title;
    }

    public String getLegendOneTitle(){
        return legendOneTitle;
    }

    public String getLegendTwoTitle(){
        return legendTwoTitle;
    }

    public int getIndexOfData(){
        return indexOfData;
    }

    public int getNumberOfTotalLines(){
        return numberOfTotalLines;
    }

    public void addToCumulativeValue(int value){
        cumulativeValue += value;
    }

    public int getCumulativeValue(){
        return cumulativeValue;
    }

    public void resetCumulativeValue(){ cumulativeValue = 0; }

    public DataFile getDataFile(){

        DashboardModel data = new DashboardModel();
        if (this == Positive){
            return data.getTestsOverTimeData();
        }
        else if (this == Tested){
            return data.getTestsOverTimeData();
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
