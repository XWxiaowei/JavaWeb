package com.jay.validator.validateMethod;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author xiang.wei
 * @create 2017/11/29 18:59
 */
public class CarValidTest {
    public static void main(String[] args) throws NoSuchMethodException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        ExecutableValidator executableValidator = factory.getValidator().forExecutables();
        Car object = new Car( "Morris" );
        Method method = Car.class.getMethod( "drive", int.class );
        Object[] parameterValues = { 80 };
        Set<ConstraintViolation<Car>> violations = executableValidator.validateParameters(
                object,
                method,
                parameterValues
        );

        assertEquals( 1, violations.size() );
    }
}
