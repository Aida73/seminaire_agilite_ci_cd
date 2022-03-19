package sn.ept.git.seminaire.cicd.demo;

public class Calculator implements ICalculator {
    @Override
    public double add(double a, double b) {
        return a+b;
    }

    @Override
    public double subtract(double a, double b) {
        return  a-b;
    }

    @Override
    public double multiply(double a, double b) {
        return  a*b;
    }

    @Override
    public double divide(double a, double b) throws ArithmeticException {
        if(b==0){
            throw new ArithmeticException("Can not divide by ero");
        }
        return a/b;
    }
}
