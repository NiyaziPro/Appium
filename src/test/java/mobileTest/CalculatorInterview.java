package mobileTest;


import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AppFunctions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;


public class CalculatorInterview {

    @Test
    public void testCalculatorApp() throws MalformedURLException {

        AppFunctions appFunctions = new AppFunctions();

        appFunctions.clickDigits("23").plus().clickDigits("95").equals();
        String sumResult = appFunctions.result.getText();
        appFunctions.squareRoot().clickDigits(sumResult).equals().multiply().minus().clickDigits("1").equals();

        appFunctions.screenOrientationLandscape();
        String sqrtResult = appFunctions.result.getText();
        appFunctions.screenOrientationPortrait();

        sqrtResult = sqrtResult.replace("−", "-");
        BigDecimal result = new BigDecimal(sqrtResult);

        BigDecimal roundedResult = result.setScale(42, RoundingMode.UP);

        BigDecimal expectedResult = new BigDecimal("-10.862780491200215723891493374737411201226662");

        Assert.assertEquals(roundedResult, expectedResult);

        BigDecimal roundedToFour = roundedResult.setScale(4, RoundingMode.UP);

        double calculatedResult = roundedToFour.doubleValue();

        Assert.assertEquals(calculatedResult, -10.8628);

    }

    @Test
    public void testCalculatorApp01() throws MalformedURLException {
        AppFunctions appFunctions = new AppFunctions();
        String sumResult = appFunctions.performOperation(appFunctions, "23", "+", "95");
        String sqrtResult = appFunctions.performSquareRootOperation(appFunctions, sumResult, "1");
        appFunctions.validateResult(sqrtResult, "-10.862780491200215723891493374737411201226662", 4);
    }



}