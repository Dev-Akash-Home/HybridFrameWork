package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String [][] getData() throws IOException {

        String path = System.getProperty("user.dir")+"\\testData\\LoginData.xlsx"; // get the xls file

        UtilityPage up = new UtilityPage(path);

        // get the row & col values from excel
        int totalRow = up.getRowCount("Sheet1");
        int totalCol = up.getCellCount("Sheet1",1);

        // create 2 dimensional array
        String logindata [][] = new String [totalRow][totalCol];

        // read the data from excel
        for (int i=1;i<=totalRow;i++) {
            for (int j=0;j<totalCol;j++) {
                logindata [i-1][j] = up.getCellData("Sheet1", i, j);
            }
        }
        return logindata; // returns the array value
    }
}
